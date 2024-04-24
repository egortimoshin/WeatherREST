package com.etim.controllers;

import com.etim.dto.MeasurementDTO;
import com.etim.models.Measurement;
import com.etim.models.Sensor;
import com.etim.servces.MeasurementService;
import com.etim.servces.SensorService;
import com.etim.validation.MeasurementDTOValidation;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Register measurement")
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

    @Operation(summary = "Get all measurements")
    @GetMapping()
    public List<Measurement> show() {
        return measurementService.show();
    }

    @Operation(summary = "Get the number of rainy days")
    @GetMapping("/rainyDaysCount")
    public ResponseEntity rainyDaysCount() {
        int count = 0;
        for (Measurement measurement : measurementService.show()) {
            if (measurement.getRaining()) count++;
        }
        return ResponseEntity.ok(count);
    }
}
