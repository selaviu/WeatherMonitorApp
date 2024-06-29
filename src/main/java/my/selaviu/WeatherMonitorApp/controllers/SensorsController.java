package my.selaviu.WeatherMonitorApp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import my.selaviu.WeatherMonitorApp.dto.SensorDTO;
import my.selaviu.WeatherMonitorApp.models.Sensor;
import my.selaviu.WeatherMonitorApp.services.SensorsService;
import my.selaviu.WeatherMonitorApp.util.ErrorResponse;
import my.selaviu.WeatherMonitorApp.util.SensorNotCreatedException;
import my.selaviu.WeatherMonitorApp.util.SensorValidator;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper,
                                SensorValidator sensorValidator){
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                                BindingResult bindingResult) {

        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError e : errors){
                errorMsg.append(e.getField()).append(" - ")
                    .append(e.getDefaultMessage()).append(";");
            }

            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorsService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e){
        ErrorResponse response = new ErrorResponse(e.getMessage(),
                 System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
    
}
