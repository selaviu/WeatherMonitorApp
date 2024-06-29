package my.selaviu.WeatherMonitorApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Value should not be null!")
    @Min(value = -100, message = "Value should be greater than or equal to -100")
    @Max(value = 100, message = "Value should be less than or equal to 100")
    private double value;

    @NotNull(message = "isRaining should not be null!")
    private boolean isRaining;

    @NotNull(message = "Sensor should not be null!")
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(double value, boolean isRaining, SensorDTO sensor) {
        this.value = value;
        this.isRaining = isRaining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean isRaining) {
        this.isRaining = isRaining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
