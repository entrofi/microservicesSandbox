package net.entrofi.microservices.sandbox.fms.app;


import net.entrofi.microservices.sandbox.fms.app.helpers.FlightIdHelper;
import net.entrofi.microservices.sandbox.fms.domain.entity.Aircraft;
import net.entrofi.microservices.sandbox.fms.domain.entity.CodeContextPointer;
import net.entrofi.microservices.sandbox.fms.domain.entity.Crew;
import net.entrofi.microservices.sandbox.fms.domain.entity.Flight;
import net.entrofi.microservices.sandbox.fms.domain.entity.FlightId;
import net.entrofi.microservices.sandbox.fms.domain.repository.AircraftRepository;
import net.entrofi.microservices.sandbox.fms.domain.repository.CrewRepository;
import net.entrofi.microservices.sandbox.fms.domain.repository.FlightRepository;
import net.entrofi.microservices.sandbox.fms.env.model.Airport;
import net.entrofi.microservices.sandbox.fms.env.service.FMSKBMSConsumerService;
import net.entrofi.microservices.sandbox.fms.env.service.FlightQueuePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FMSInitializerService {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private FMSKBMSConsumerService kbmsConsumerService;

    @Autowired
    private FlightQueuePublisher flightQueuePublisher;

    private List<Airport> airports;


    public List<Aircraft> createAircrafts() {
        Aircraft[] aircrafts = {
                new Aircraft("Boeing", "747-8F", 152),
                new Aircraft("Boeing", "777", 344),
                new Aircraft("Boeing", "748-i", 405),
                new Aircraft("Airbus", "A380", 535),
                new Aircraft("Airbus", "A380", 592),
                new Aircraft("Airbus", "A330-300", 277)
        };

        List<Aircraft> aircraftList = Arrays.asList(aircrafts);
        aircraftList.forEach(aircraft -> aircraftRepository.save(aircraft));
        return aircraftList;
    }

    public List<Crew> createCrews() {
        String[] pilotRoles = {"Captain", "Officer"};
        String[] cabinCrewRoles = {"Purser", "Flight Attendant"};
        List<Crew> crews = new ArrayList<>();

        crews.addAll(createCrewGroupList(pilotRoles, 12, "Pilot "));
        crews.addAll(createCrewGroupList(cabinCrewRoles, 60, "Crew "));

        crews.forEach(crew -> crewRepository.save(crew));

        return crews;
    }

    private List<Crew> createCrewGroupList(String[] roles, int count, String prefix) {
        List<Crew> crews = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Crew pilot = new Crew();
            int nameSuffix = ThreadLocalRandom.current().nextInt(count);
            pilot.setName(prefix + nameSuffix);
            pilot.setSurname(prefix + nameSuffix);
            pilot.setRole(roles[i % 2]);
            crews.add(pilot);
        }
        return crews;
    }

    public List<Flight> createFlights(final int count) {
        List<Aircraft> aircrafts = aircraftRepository.findAll();
        List<Flight> flights = new ArrayList<>();
        if (aircrafts.isEmpty()) {
            aircrafts = createAircrafts();
        }
        Date[] datePair = null;
        for (int i = 0; i < count; i++) {
            Flight flight = new Flight();
            if(datePair == null || i % 2 == 0) {
                datePair = calculateDatePair();
            }
            FlightId flightId = fetchRandomBasicFlightIdBuilder()
                    .originDate(datePair[0]).operationalSuffix("PUB")
                    .repeatNumber(0).build();
            flight.setId(flightId);
            flight.setScheduledTimeDeparture(datePair[0]);
            flight.setScheduledTimeArrival(datePair[1]);
            flight.setCrews(fetchCrewsForFlight());
            flight.setAircraft(aircrafts.get(ThreadLocalRandom.current().nextInt(aircrafts.size())));
            flights.add(flightRepository.save(flight));
            flightQueuePublisher.send(flight);
        }
        return flights;
    }


    private Set<Crew> fetchCrewsForFlight() {
        if (crewRepository.count() <= 0) {
            createCrews();
        }

        List<Crew> captains = crewRepository.findByRole("Captain");
        List<Crew> pilots = crewRepository.findByRole("Officer");
        List<Crew> pursers = crewRepository.findByRole("Purser");
        List<Crew> attendants = crewRepository.findByRole("Flight Attendant");

        Set<Crew> crewSet = new HashSet<>();
        crewSet.add(captains.get(ThreadLocalRandom.current().nextInt(captains.size())));
        crewSet.add(pilots.get(ThreadLocalRandom.current().nextInt(pilots.size())));
        crewSet.add(pursers.get(ThreadLocalRandom.current().nextInt(pursers.size())));
        for (int i = 0; i < attendants.size() && i < 5; i++) {
            crewSet.add(attendants.get(ThreadLocalRandom.current().nextInt(attendants.size())));
        }
        return crewSet;
    }

    /**
     * Generates a flight id builder instance with core fields departure, arrival and flight number.
     *
     * @return core flight id builder.
     */
    private FlightIdHelper.FlightIdBuilder fetchRandomBasicFlightIdBuilder() {
        if (airports == null || airports.isEmpty()) {
            airports = new ArrayList<>();
            airports = kbmsConsumerService.getAirports();
        }
        List<Airport> airportTuple = new LinkedList<>();
        while (airportTuple.size() < 2) {
            int index = ThreadLocalRandom.current().nextInt(0, airports.size() - 1);
            if (!airportTuple.contains(airports.get(index))) {
                airportTuple.add(airports.get(index));
            }
        }
        final String flightNumber = "TK" + ThreadLocalRandom.current().nextInt(100, 1000);

        FlightIdHelper.FlightIdBuilder flightIdBuilder = FlightIdHelper.FlightIdBuilder.newInstance(airportTuple.get(0).getCodeContextPointer(),
                airportTuple.get(1).getCodeContextPointer(), flightNumber, new CodeContextPointer("TK", "IATA"));

        return flightIdBuilder;
    }

    /**
     * @return randomly calculated departure arrival dates
     */
    private Date[] calculateDatePair() {
        final Date[] datePair = new Date[2];
        final Date currentTime = Calendar.getInstance().getTime();
        final long twoHoursAfterCurrentTime = currentTime.getTime() + 2 * 60 * 60 * 1000l;
        final long sixMonthsAfterCurrentTime = currentTime.getTime() + 6 * 30 * 24 * 60 * 60 * 1000l;

        long originLong = ThreadLocalRandom.current().nextLong(twoHoursAfterCurrentTime, sixMonthsAfterCurrentTime);
        long arrivalLong = ThreadLocalRandom.current().nextLong(originLong + 40 * 60 * 1000, originLong + 15 * 60 * 60 * 1000);
        datePair[0] = new Date(originLong);
        datePair[1] = new Date(arrivalLong);
        return datePair;
    }


}
