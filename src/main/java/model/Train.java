package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import deserializer.TransportDeserializer;

/**
 * @author Maria.Guseva
 */
@JsonDeserialize(using = TransportDeserializer.class)
public class Train extends Transport{

    @JsonProperty("number-wagons")
    private Long numberWagons;

    @JsonProperty("w-passenger-capacity")
    private Long wPassengerCapacity;

    @Override
    public Long getCapacity() {
        return numberWagons * wPassengerCapacity;
    }

    public Long getNumberWagons() {
        return numberWagons;
    }

    public void setNumberWagons(Long numberWagons) {
        this.numberWagons = numberWagons;
    }

    public Long getWPassengerCapacity() {
        return wPassengerCapacity;
    }

    public void setWPassengerCapacity(Long wPassengerCapacity) {
        this.wPassengerCapacity = wPassengerCapacity;
    }
}
