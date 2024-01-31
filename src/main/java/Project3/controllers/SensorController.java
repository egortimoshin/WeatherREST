package Project3.controllers;

import Project3.dto.SensorDTO;
import Project3.models.Sensor;
import Project3.servces.SensorService;
import Project3.validation.SensorDTOValidation;
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

    @GetMapping()
    public List<Sensor> show() {
        return sensorService.show();
    }
}
