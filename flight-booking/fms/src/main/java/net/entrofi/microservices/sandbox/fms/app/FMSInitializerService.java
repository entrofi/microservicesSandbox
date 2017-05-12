/*
 * Copyright 2003-2016 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */
package net.entrofi.microservices.sandbox.fms.app;


import net.entrofi.microservices.sandbox.fms.domain.entity.Aircraft;
import net.entrofi.microservices.sandbox.fms.domain.entity.Crew;
import net.entrofi.microservices.sandbox.fms.domain.entity.Flight;
import net.entrofi.microservices.sandbox.fms.domain.entity.FlightId;
import net.entrofi.microservices.sandbox.fms.env.model.Airport;
import net.entrofi.microservices.sandbox.fms.domain.repository.AircraftRepository;
import net.entrofi.microservices.sandbox.fms.domain.repository.CrewRepository;
import net.entrofi.microservices.sandbox.fms.domain.repository.FlightRepository;
import net.entrofi.microservices.sandbox.fms.service.FlightQueuePublisher;
import net.entrofi.microservices.sandbox.fms.service.KBMSConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class FMSInitializerService {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CrewRepository crewRepository;

    @Autowired
    private KBMSConsumerService kbmsConsumerService;

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
        String[] cabinCrewRoles = { "Purser", "Flight Attendant"};
        List<Crew> crews = new ArrayList<>();

        for(int i = 0; i < 12; i++) {
            Crew pilot = new Crew();
            int nameSuffix = ThreadLocalRandom.current().nextInt(12);
            pilot.setName("Pilot " + nameSuffix);
            pilot.setSurname("Pilot_surname " + nameSuffix );
            pilot.setRole(pilotRoles[i % 2]);
            crews.add(pilot);
        }

        for(int j= 0; j < 60; j++) {
            Crew cabinCrew = new Crew();
            int nameSuffix = ThreadLocalRandom.current().nextInt(30);
            String role = cabinCrewRoles[j % 2];
            cabinCrew.setRole(role);
            cabinCrew.setName("Crew " + role + nameSuffix );
            cabinCrew.setSurname("Crew_surname " + role + nameSuffix);
            crews.add(cabinCrew);
        }

        crews.forEach(crew -> crewRepository.save(crew));

        return crews;
    }

    public void createFlights(final int count) {
        List<Aircraft> aircrafts = aircraftRepository.findAll();
        if(aircrafts.isEmpty()){
            aircrafts = createAircrafts();
        }

        for(int i = 0; i <= count; i++) {
            Flight flight = new Flight();
            Date[] datePair = calculateDatePair();
            FlightId flightId = fetchRandomBasicFlightIdBuilder()
                    .originDate(datePair[0]).operationalSuffix("PUB")
                    .repeatNumber(0).build();
            flight.setId(flightId);
            flight.setScheduledTimeDeparture(datePair[0]);
            flight.setScheduledTimeArrival(datePair[1]);
            flight.setCrews(fetchCrewsForFlight());
            flight.setAircraft(aircrafts.get(ThreadLocalRandom.current().nextInt(aircrafts.size())));
            flightRepository.save(flight);
            flightQueuePublisher.send(flight);
        }
    }


    private Set<Crew> fetchCrewsForFlight() {
        if(crewRepository.count() <= 0) {
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
        for(int i = 0; i < attendants.size() && i < 5; i++) {
            crewSet.add(attendants.get(ThreadLocalRandom.current().nextInt(attendants.size())));
        }
        return crewSet;
    }

    /**
     * Generates a flight id builder instance with core fields departure, arrival and flight number.
     * @return core flight id builder.
     */
    private FlightId.FlightIdBuilder fetchRandomBasicFlightIdBuilder() {
        if(airports == null || airports.isEmpty()) {
            airports = new ArrayList<>();
            airports = kbmsConsumerService.getAirports();
        }
        List<Airport> airportTuple = new LinkedList<>();
        while(airportTuple.size() < 2) {
            int index = ThreadLocalRandom.current().nextInt(0, 2);
            if(!airportTuple.contains(airports.get(index))) {
                airportTuple.add(airports.get(index));
            }
        }
        final String flighNumber = "TK" + ThreadLocalRandom.current().nextInt(100, 1000);

        FlightId.FlightIdBuilder flightIdBuilder = FlightId.FlightIdBuilder.newInstance(airportTuple.get(0).getCodeContextPointer(),
                airportTuple.get(1).getCodeContextPointer(), flighNumber);

        return flightIdBuilder;
    }

    /**
     *
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
