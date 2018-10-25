package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import deserializer.TransportDeserializer;

/**
 * @author Maria.Guseva
 */

@JsonDeserialize(using = TransportDeserializer.class)
public class Car extends Transport {

    private String manufacturer;

    @JsonProperty("passenger-capacity")
    private Long capacity;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
