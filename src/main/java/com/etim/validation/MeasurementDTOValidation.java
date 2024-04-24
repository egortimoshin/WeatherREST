package com.etim.validation;

import com.etim.dto.MeasurementDTO;
import com.etim.servces.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementDTOValidation implements Validator {
    private final SensorService sensorService;

    public MeasurementDTOValidation(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if (sensorService.findByName(measurementDTO.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "This sensor doesn't exist");
        }
    }
}
