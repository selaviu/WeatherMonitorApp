package my.selaviu.WeatherMonitorApp.util;

public class MeasurementNotAddedException extends RuntimeException {

    public MeasurementNotAddedException(String msg){
        super(msg);
    }
}
