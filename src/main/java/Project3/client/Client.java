package Project3.client;

import Project3.dto.MeasurementDTO;
import Project3.dto.SensorDTO;
import Project3.models.Measurement;
import Project3.models.Sensor;
import Project3.servces.MeasurementService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class Client {

    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();

        //добавляем новый сенсор
        Map<String, String> sensorMap = new HashMap<>();
        sensorMap.put("name", "Sensor2");
        HttpEntity<Map<String, String>> sensorEntity = new HttpEntity<>(sensorMap);
        template.postForObject("http://localhost:8080/sensors/registration", sensorEntity, String.class);

        //добавляем в список 100 измерений от указанного сенсора
        for (int i = 0; i < 100; i++) {
            Map<String, Object> measurementMap = new HashMap<>();
            measurementMap.put("value", new Random().nextInt(101));
            measurementMap.put("raining", new Random().nextBoolean());
            measurementMap.put("sensor", sensorMap);
            template.postForObject("http://localhost:8080/measurements/add", measurementMap, String.class);
        }

        //выводим список измерений
        ResponseEntity<List<Measurement>> measurementResponse = template.exchange(
                "http://localhost:8080/measurements",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<Measurement> measurements = measurementResponse.getBody();
        for (int i = 0; i < measurements.size(); i++) {
            System.out.println(measurements.get(i));
        }
    }
}
