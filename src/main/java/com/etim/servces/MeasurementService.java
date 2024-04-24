package com.etim.servces;

import com.etim.models.Measurement;
import com.etim.repositories.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

    public List<Measurement> show() {
        return measurementRepository.findAll();
    }
}
