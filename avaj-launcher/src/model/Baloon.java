package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;

public class Baloon extends AirCraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(String name, Coordinate coordinate) {
        super(name, coordinate);
    }

    @Override
    public void updateConditions() {

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
            System.out.println("10 error");
            e.printStackTrace();
        }
    }
}
