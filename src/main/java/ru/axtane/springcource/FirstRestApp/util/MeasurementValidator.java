package ru.axtane.springcource.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.axtane.springcource.FirstRestApp.models.Measurement;
import ru.axtane.springcource.FirstRestApp.services.MeasurementService;

@Component
public class MeasurementValidator implements Validator {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementValidator(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurementService.findSensorByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Cant find sensor with this name");
        }
    }
}
