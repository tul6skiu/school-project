package model;

public class WeatherProvider {
    private WeatherProvider weatherProvider;
    private String[] weather;

    public WeatherProvider() {}

    public WeatherProvider getProvider() {
        //todo: возвращает текущий провайдер
        return this.weatherProvider;
    }

    public String getCurrentWeather(Coordinate coordinate) {
        //todo: логика возвращает текущию погоду относительно кординат
        return "";
    }

}
