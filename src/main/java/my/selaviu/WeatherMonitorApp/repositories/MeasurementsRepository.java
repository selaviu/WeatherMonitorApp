package my.selaviu.WeatherMonitorApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.selaviu.WeatherMonitorApp.models.Measurement;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    public int countByIsRainingTrue();
}
