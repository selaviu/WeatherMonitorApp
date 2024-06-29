package my.selaviu.WeatherMonitorApp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.selaviu.WeatherMonitorApp.models.Measurement;
import my.selaviu.WeatherMonitorApp.repositories.MeasurementsRepository;

@Service
@Transactional(readOnly =  true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    public MeasurementsService(MeasurementsRepository measurementsRepository,
                                    SensorsService sensorsService){
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    @Transactional
    public void save(Measurement measurement){
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement){
        measurement.setSensor(sensorsService.findSensorByName(measurement.getSensor().getName()));
        measurement.setMeasurementDateTime(LocalDateTime.now());
    }

    public List<Measurement> findAll(){
        return measurementsRepository.findAll();
    }

    public int countRainyDays(){
        return measurementsRepository.countByIsRainingTrue();
    }
}
