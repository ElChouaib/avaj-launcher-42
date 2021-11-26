package dao.model;

import dao.entity.WeatherTower;

public interface Flyable {
    void updateConditions();

    void registerTower(WeatherTower weatherTower);

    void unregisterTower();
}
