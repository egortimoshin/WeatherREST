package com.etim.servces;

import com.etim.models.Sensor;
import com.etim.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public List<Sensor> show() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findFirstByName(name);
    }
}
