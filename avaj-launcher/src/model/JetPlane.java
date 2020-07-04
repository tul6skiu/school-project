package model;

public class JetPlane extends AirCraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(String name, Coordinate coordinate) {
        super(name, coordinate);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower(WeatherTower tower) {

    }
}
