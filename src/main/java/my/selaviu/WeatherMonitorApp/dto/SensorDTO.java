package my.selaviu.WeatherMonitorApp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Name should not to be empty!")
    @Size(min=3, max=30, message = "Name size should be between 3 to 30 characters")
    private String name;

    public SensorDTO(){

    }

    public SensorDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
