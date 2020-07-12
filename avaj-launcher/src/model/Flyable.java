package model;

import java.io.IOException;

public interface Flyable {
    //todo обновляен условие
    void updateConditions();
    void registerTower(WeatherTower tower) throws IOException;
}
