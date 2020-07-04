package model;

public class WeatherTower extends Tower{


    public WeatherTower(Flyable flyable) {
        super(flyable);
    }

    public String getWeather(Coordinate coordinate) {
        return "";
    }

    public void changeWeather() {

    }
}
