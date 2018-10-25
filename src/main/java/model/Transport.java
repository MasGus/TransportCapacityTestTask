package model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import deserializer.TransportDeserializer;

/**
 * @author Maria.Guseva
 */

@JsonDeserialize(using = TransportDeserializer.class)
public abstract class Transport {
    private String model;

    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    abstract Long getCapacity();
}
