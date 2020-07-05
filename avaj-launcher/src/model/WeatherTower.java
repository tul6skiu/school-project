package model;

public class WeatherTower extends Tower{
    public String getWeather(Coordinate coordinate) {
        return "";
    }

    public void changeWeather() {
        WeatherProvider.getProvider();
        super.conditionsChanger();
    }
}
