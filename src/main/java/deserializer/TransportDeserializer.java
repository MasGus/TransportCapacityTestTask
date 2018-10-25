package deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import model.Car;
import model.Plane;
import model.Train;
import model.Transport;

import java.io.IOException;

/**
 * @author Maria.Guseva
 */
public class TransportDeserializer extends JsonDeserializer<Transport> {
    public Transport deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        Transport transport = null;
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);

        final JsonNode capacity = node.get("passenger-capacity");
        final JsonNode bCapacity = node.get("b-passenger-capacity");
        final JsonNode wCapacity = node.get("w-passenger-capacity");

        if (capacity != null){
            Car car = new Car();
            car.setCapacity(capacity.asLong());
            car.setManufacturer(node.get("manufacturer").asText());
            transport = car;
        } else if (bCapacity != null){
            Plane plane = new Plane();
            plane.setBPassengerCapacity(bCapacity.asLong());
            plane.setEPassengerCapacity(node.get("e-passenger-capacity").asLong());
            transport = plane;
        } else if (wCapacity != null){
            Train train = new Train();
            train.setWPassengerCapacity(wCapacity.asLong());
            train.setNumberWagons(node.get("number-wagons").asLong());
            transport = train;
        }

        if (transport != null) {
            transport.setModel(node.get("model").asText());
        }

        return transport;
    }
}
