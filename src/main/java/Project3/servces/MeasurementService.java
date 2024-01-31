package Project3.servces;

import Project3.models.Measurement;
import Project3.repositories.MeasurementRepository;
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
