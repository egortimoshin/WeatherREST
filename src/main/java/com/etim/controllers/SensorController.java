package com.etim.controllers;

import com.etim.dto.SensorDTO;
import com.etim.models.Sensor;
import com.etim.servces.SensorService;
import com.etim.validation.SensorDTOValidation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorDTOValidation sensorDTOValidation;

    public SensorController(SensorService sensorService, SensorDTOValidation sensorDTOValidation) {
        this.sensorService = sensorService;
        this.sensorDTOValidation = sensorDTOValidation;
    }

    @Operation(summary = "Register sensor")
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult br) {
        sensorDTOValidation.validate(sensorDTO, br);
        if (br.hasErrors()) {
            return ResponseEntity.ok(br.getFieldError().getDefaultMessage());
        } else {
            ModelMapper mapper = new ModelMapper();
            Sensor sensor = mapper.map(sensorDTO, Sensor.class);
            sensorService.save(sensor);
            return ResponseEntity.ok(sensor.getName() + " is successfully registrated");
        }
    }

    @Operation(summary = "Show all sensors")
    @GetMapping()
    public List<Sensor> show() {
        return sensorService.show();
    }
}
