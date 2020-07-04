package model;

public interface Flyable {
    //todo обновляен условие
    void updateConditions();
    void registerTower(WeatherTower tower);
}
