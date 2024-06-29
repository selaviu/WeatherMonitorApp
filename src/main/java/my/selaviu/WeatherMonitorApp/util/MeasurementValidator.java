package my.selaviu.WeatherMonitorApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import my.selaviu.WeatherMonitorApp.models.Measurement;
import my.selaviu.WeatherMonitorApp.services.SensorsService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if (!sensorsService.existsByName(measurement.getSensor().getName())) {
            errors.rejectValue("name", "", "This sensor does not exist");
        }
    }
}
