package com.etim.validation;

import com.etim.dto.SensorDTO;
import com.etim.models.Sensor;
import com.etim.servces.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorDTOValidation implements Validator {
    private final SensorService sensorService;

    public SensorDTOValidation(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if (sensorService.findByName(sensorDTO.getName()).isPresent()) {
            errors.rejectValue("name", "", "This sensor name is already used");
        }
    }
}
