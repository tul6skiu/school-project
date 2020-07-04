package model;

public class Helicopter extends AirCraft implements Flyable{
    private WeatherTower weatherTower;


    public Helicopter(String name, Coordinate coordinate) {
        super(name, coordinate);
    }

    @Override
    public void updateConditions() {

    }

    @Override
    public void registerTower(WeatherTower tower) {

    }
}
