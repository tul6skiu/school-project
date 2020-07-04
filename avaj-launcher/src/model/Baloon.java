package model;

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

    }
}
