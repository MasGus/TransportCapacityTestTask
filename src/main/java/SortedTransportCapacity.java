import com.fasterxml.jackson.databind.ObjectMapper;
import model.Car;
import model.Plane;
import model.Train;
import model.Transport;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Maria.Guseva
 */
public class SortedTransportCapacity {

    private static final Map<String, Long> capacityMap = Stream.of(
            new SimpleEntry<>("planes", 0L),
            new SimpleEntry<>("trains", 0L),
            new SimpleEntry<>("cars", 0L)
    ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    private static final Map<String, Long> transportMap = Stream.of(
            new SimpleEntry<>("distinct-planes", 0L),
            new SimpleEntry<>("distinct-trains", 0L),
            new SimpleEntry<>("distinct-cars", 0L)
    ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            Object obj = parser.parse(new FileReader(args[0]));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray transportList = (JSONArray) jsonObject.get("transports");

            Iterator<JSONObject> iterator = transportList.iterator();
            while (iterator.hasNext()) {
                count(objectMapper.readValue(iterator.next().toJSONString(), Transport.class));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        printMapSortedByValue(capacityMap);
        printMapSortedByValue(transportMap);

    }

    private static void count(Transport transport){
        if (transport instanceof Car){
            capacityMap.put("cars", capacityMap.get("cars") + ((Car) transport).getCapacity());
            transportMap.put("distinct-cars", transportMap.get("distinct-cars") + 1);
        } else if (transport instanceof Train){
            capacityMap.put("trains", capacityMap.get("trains") + ((Train) transport).getCapacity());
            transportMap.put("distinct-trains", transportMap.get("distinct-trains") + 1);
        } else if (transport instanceof Plane){
            capacityMap.put("planes", capacityMap.get("planes") + ((Plane) transport).getCapacity());
            transportMap.put("distinct-planes", transportMap.get("distinct-planes") + 1);
        }
    }

    private static void printMapSortedByValue(@NotNull Map<String, Long> unsortedMap){
        List<Map.Entry<String, Long>> entries = new ArrayList<>(unsortedMap.size());
        entries.addAll(unsortedMap.entrySet());

        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for (Map.Entry<String, Long> entry : entries) {
            System.out.println("\"" + entry.getKey() + "\" : " + entry.getValue());
        }
    }
}
