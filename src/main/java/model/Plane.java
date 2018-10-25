package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import deserializer.TransportDeserializer;

/**
 * @author Maria.Guseva
 */

@JsonDeserialize(using = TransportDeserializer.class)
public class Plane extends Transport{

    @JsonProperty("b-passenger-capacity")
    private Long bPassengerCapacity;

    @JsonProperty("e-passenger-capacity")
    private Long ePassengerCapacity;

    @Override
    public Long getCapacity() {
        return bPassengerCapacity + ePassengerCapacity;
    }

    public Long getBPassengerCapacity() {
        return bPassengerCapacity;
    }

    public void setBPassengerCapacity(Long bPassengerCapacity) {
        this.bPassengerCapacity = bPassengerCapacity;
    }

    public Long getEPassengerCapacity() {
        return ePassengerCapacity;
    }

    public void setEPassengerCapacity(Long ePassengerCapacity) {
        this.ePassengerCapacity = ePassengerCapacity;
    }
}
