package model;

import utils.CommonResponse;
import utils.CustomException;
import utils.ErrorInterceptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;

public class Baloon extends AirCraft implements Flyable {
    public Boolean currentAirCraftIsDown = false;
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinate coordinate) {
        super(name, coordinate);
    }

    @Override
    public void updateConditions() {
        StringBuilder builder = new StringBuilder();
        builder.append("Balloon#")
                .append(this.name).append("(")
                .append(this.id).append(")").append(" The weather has changer.");
        String weather = WeatherProvider.getCurrentWeather(this.coordinate);
        String extra;
        if (weather.equals(TypeWeather.RAIN.name())) {
            change(0, 0, -5);
            extra = " This rain. I slowly decrease speed\n";
        } else if (weather.equals(TypeWeather.FOG.name())) {
            change(0,0,-3);
            extra = " This fog. I can't see anything, I decrease speed\n";
        } else if (weather.equals(TypeWeather.SUN.name())) {
            change(2,0,4);
            extra = " This sun. I increase speed, and increase height\n";
        } else {
            change(0, 0, -15);
            extra = " This snow? I'm going down\n";
        }
        try {
            Files.write(Paths.get("simulation.txt"), (builder.toString() + extra).getBytes(), APPEND);
            if (currentAirCraftIsDown) {
                unregisterCurrentAirCraft();
            }
        } catch (IOException e) {
            ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_WRITE_FILE));
        }
    }

    @Override
    public void registerTower(WeatherTower tower) {
        StringBuilder str = new StringBuilder();
        weatherTower = tower;
        weatherTower.register(this);

        str.append("Tower say: Balloon#")
                .append(this.name).append("(").append(this.id).append(")")
                .append(" registered to weather tower\n");

        try {
            Files.write(Paths.get("simulation.txt"), str.toString().getBytes(), APPEND);
        } catch (IOException e) {
            ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_WRITE_FILE));
        }
    }

    private void change(int longitude, int latitude, int height) {

        if (this.coordinate.getLongitude() + longitude < Point.MAX_LONGITUDE.getPoint()) {
            longitude += this.coordinate.getLongitude();
        } else {
            longitude = Point.MAX_LONGITUDE.getPoint();
            currentAirCraftIsDown = true;
            weatherTower.unregister(this);
        }

        if (this.coordinate.getLatitude() + latitude < Point.MAX_LATITUDE.getPoint())
            latitude += this.coordinate.getLatitude();
        else {
            latitude = Point.MAX_LATITUDE.getPoint();
            currentAirCraftIsDown = true;
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
            currentAirCraftIsDown = true;
            weatherTower.unregister(this);
        }
        this.coordinate = new Coordinate(longitude, latitude, height);
    }

    private void unregisterCurrentAirCraft() {
        StringBuilder builder = new StringBuilder();
        builder.append("Balloon#")
                .append(this.name).append("(")
                .append(this.id).append(")").append(" unregistration weather tower ");

        try {
            Files.write(Paths.get("simulation.txt"), builder.toString().getBytes(), APPEND);
        } catch (IOException e) {
            ErrorInterceptor.intercept(new CustomException(CommonResponse.BAD_WRITE_FILE));
        }
    }
}
