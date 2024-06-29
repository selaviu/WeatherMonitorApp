package my.selaviu.WeatherMonitorApp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import my.selaviu.WeatherMonitorApp.models.Sensor;
import my.selaviu.WeatherMonitorApp.services.SensorsService;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        if (sensorsService.findSensorByName(sensor.getName()) != null) {
            errors.rejectValue("name", "", "This sensor name is already in use");
        }

        if (!Character.isUpperCase(sensor.getName().codePointAt(0))) {
            errors.rejectValue("name", "", "Name should start with a capital letter");
        }
    }
}
