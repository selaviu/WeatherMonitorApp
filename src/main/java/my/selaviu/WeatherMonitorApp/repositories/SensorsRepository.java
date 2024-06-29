package my.selaviu.WeatherMonitorApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.selaviu.WeatherMonitorApp.models.Sensor;


@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    public Sensor findByName(String name);
}
