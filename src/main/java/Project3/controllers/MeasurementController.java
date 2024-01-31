package Project3.controllers;

import Project3.dto.MeasurementDTO;
import Project3.models.Measurement;
import Project3.models.Sensor;
import Project3.servces.MeasurementService;
import Project3.servces.SensorService;
import Project3.validation.MeasurementDTOValidation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final MeasurementDTOValidation validation;

    public MeasurementController(MeasurementService measurementService, SensorService sensorService, MeasurementDTOValidation validation) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.validation = validation;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult br) {
        validation.validate(measurementDTO, br);
        if (br.hasErrors()) {
            return ResponseEntity.ok(br.getFieldError().getDefaultMessage());
        } else {
            ModelMapper mapper = new ModelMapper();
            Measurement measurement = mapper.map(measurementDTO, Measurement.class);
            Sensor sensor = sensorService.findByName(measurementDTO.getSensor().getName()).orElse(null);
            measurement.setSensor(sensor);
            measurement.setTimestamp(new Date());
            measurementService.save(measurement);
            return ResponseEntity.ok("Measurement" + measurement.getId() + " is successfully registrated!");
        }
    }

    @GetMapping()
    public List<Measurement> show() {
        return measurementService.show();
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity rainyDaysCount() {
        int count = 0;
        for (Measurement measurement : measurementService.show()) {
            if (measurement.getRaining()) count++;
        }
        return ResponseEntity.ok(count);
    }
}
