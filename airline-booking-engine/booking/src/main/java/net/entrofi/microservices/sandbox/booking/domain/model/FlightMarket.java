package net.entrofi.microservices.sandbox.booking.domain.model;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(FlightMarket.FlightMarketId.class)
public class FlightMarket {


    @Id
    private String airportI;

    @Id
    private String airportII;

    @Id
    private boolean directional = false;

    private Double baseFare;


    public String getAirportI() {
        return airportI;
    }

    public void setAirportI(String airportI) {
        this.airportI = airportI;
    }

    public String getAirportII() {
        return airportII;
    }

    public void setAirportII(String airportII) {
        this.airportII = airportII;
    }

    public Double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
    }

    public boolean isDirectional() {
        return directional;
    }

    public void setDirectional(boolean directional) {
        this.directional = directional;
    }


    public static class FlightMarketId implements Serializable{
        private String airportI;

        private String airportII;

        private boolean directional = false;

        public FlightMarketId() {

        }

        public FlightMarketId(String airportI, String airportII, boolean directional) {
            this.airportI = airportI;
            this.airportII = airportII;
            this.directional = directional;
        }

        public FlightMarketId(String airportI, String airportII) {
            this.airportI = airportI;
            this.airportII = airportII;
        }

        public String getAirportI() {
            return airportI;
        }

        public void setAirportI(String airportI) {
            this.airportI = airportI;
        }

        public String getAirportII() {
            return airportII;
        }

        public void setAirportII(String airportII) {
            this.airportII = airportII;
        }


        public boolean isDirectional() {
            return directional;
        }

        public void setDirectional(boolean directional) {
            this.directional = directional;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(23, 5).append(airportI).append(airportII).append(directional).build();
        }

        @Override
        public boolean equals(Object o) {
            FlightMarketId other = (FlightMarketId) o;
            EqualsBuilder equalsBuilder = new EqualsBuilder();
            boolean equals = false;
           if(other != null && !this.directional) {
                if(this.airportI != null && this.airportII != null) {
                    if((this.airportI.equals(other.getAirportI()) && this.airportII.equals(other.getAirportII())) ||
                            (this.airportI.equals(other.getAirportII()) && this.airportII.equals(other.getAirportI()))){
                        equals = true;
                    }
                }
            } else {
               equals = isParalelEquals(other, equalsBuilder);
           }
            return equals;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

        private boolean isParalelEquals(FlightMarketId other, EqualsBuilder equalsBuilder) {
            boolean equals;
            equalsBuilder.reset();
            equalsBuilder.append(this.airportI, other.airportI)
                         .append(this.directional, other.directional)
                         .append(this.airportII, other.airportII);
            equals = equalsBuilder.build();
            return equals;
        }

    }
}
