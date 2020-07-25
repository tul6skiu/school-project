package model;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static int size = Point.MAX_LONGITUDE.getPoint() * Point.MAX_LATITUDE.getPoint() * Point.MAX_HEIGHT.getPoint();
    protected static String[] weather = new String[size];

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
           setWeatherProvider(new WeatherProvider());
           return weatherProvider;
        }else {
            return weatherProvider;
        }

    }

    public static void setSize(int size) {
        WeatherProvider.size = size;
    }


    public static String getCurrentWeather(Coordinate coordinate) {
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

    public static void setWeatherProvider(WeatherProvider weatherProvider) {
        WeatherProvider.weatherProvider = weatherProvider;
    }
}
