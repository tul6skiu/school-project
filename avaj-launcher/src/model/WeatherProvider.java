package model;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    protected static String[] weather;
    private static int size;

    private WeatherProvider() {
        settingData();
        changeWeather();

    }

    private static void changeWeather() {
        int count = size - 1;
        while (count != 0) {
            int random = (int)(Math.random() * 4);
            weather[count] = TypeWeather.values()[random].name();
            count--;
        }
    }

    public static WeatherProvider getProvider() {
        if (weatherProvider == null) {
            settingData();
            changeWeather();
        }
            return weatherProvider;
    }

    public static void setSize(int size) {
        WeatherProvider.size = size;
    }


    public String getCurrentWeather(Coordinate coordinate) {
        //todo: логика возвращает текущию погоду относительно кординат
        return weather[coordinate.getLongitude() - 1
                + coordinate.getLatitude() * (Point.MAX_LATITUDE.getPoint())
                + coordinate.getHeight() * (Point.MAX_HEIGHT.getPoint())];
    }

    public static void settingData() {
        setSize(Point.MAX_LONGITUDE.getPoint()
                * Point.MAX_LATITUDE.getPoint()
                * Point.MAX_HEIGHT.getPoint());
    }
}
