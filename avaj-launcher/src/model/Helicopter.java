package model;

import utils.CommonResponse;
import utils.CustomException;
import utils.ErrorInterceptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;

public class Helicopter extends AirCraft implements Flyable{
    private WeatherTower weatherTower;


    public Helicopter(String name, Coordinate coordinate) {
        super(name, coordinate);
    }

    @Override
    public void updateConditions() {
        StringBuilder builder = new StringBuilder();
        builder.append("Helicopter# ")
                .append(this.name).append("(")
                .append(this.id).append(")").append(" The weather has changer.");
        String weather = WeatherProvider.getCurrentWeather(this.coordinate);
        String extra;
        if (weather.equals(TypeWeather.RAIN.name())) {
            change(5, 0, 0);
            extra = " This rain. I slowly decrease speed\n";
        } else if (weather.equals(TypeWeather.FOG.name())) {
            change(1,0,0);
            extra = " This fog. I can't see anything, I decrease speed\n";
        } else if (weather.equals(TypeWeather.SUN.name())) {
            change(10,0,2);
            extra = " This sun. I increase speed, and increase height\n";
        } else {
            change(0, 0, -12);
            extra = " This snow? I'm going down\n";
        }
        try {
            Files.write(Paths.get("simulation.txt"), (builder.toString() + extra).getBytes(), APPEND);
        } catch (IOException e) {
            ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_WRITE_FILE));
        }
    }

    @Override
    public void registerTower(WeatherTower tower) {
        StringBuilder str = new StringBuilder();
        weatherTower = tower;
        weatherTower.register(this);

        str.append("Tower say: Helicopter#")
                .append(this.name).append("(").append(this.id).append(")")
                .append(" registered to weather tower\n");
        try {
            Files.write(Paths.get("simulation.txt"), str.toString().getBytes(), APPEND);
        } catch (IOException e) {
            System.out.println("10 error");
            e.printStackTrace();
        }
    }

    private void change(int longitude, int latitude, int height) {
        if (this.coordinate.getLongitude() + longitude < Point.MAX_LONGITUDE.getPoint()) {
            longitude += this.coordinate.getLongitude();
        } else {
            longitude = Point.MAX_LONGITUDE.getPoint();
            weatherTower.unregister(this);
        }

        if (this.coordinate.getLatitude() + latitude < Point.MAX_LATITUDE.getPoint())
            latitude += this.coordinate.getLatitude();
        else {
            latitude = Point.MAX_LATITUDE.getPoint();
            weatherTower.unregister(this);
        }

        if (this.coordinate.getHeight() + height < Point.MAX_HEIGHT.getPoint()
                && this.coordinate.getHeight() + height > 0)
            height += this.coordinate.getHeight();
        else if (this.coordinate.getHeight() + height > 0 ){
            height = Point.MAX_HEIGHT.getPoint();
        }
        else {
            height = 0;
            weatherTower.unregister(this);
        }
        this.coordinate = new Coordinate(longitude, latitude, height);
    }
}
