package my.selaviu.WeatherMonitorApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.selaviu.WeatherMonitorApp.models.Sensor;
import my.selaviu.WeatherMonitorApp.repositories.SensorsRepository;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    public SensorsService(SensorsRepository sensorsRepository){
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorsRepository.save(sensor);
    }

    public Sensor findSensorByName(String name){
        return sensorsRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        return sensorsRepository.findByName(name) != null;
    }
}
