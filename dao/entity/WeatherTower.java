package dao.entity;

/**
 * @Author Chouaib
 * @Date 26-11-2021
 * @Project : Avaj-launcher-42
 */
public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        System.out.println("---------- WEATHER IS CHANGING !!! ALERT !!! ALERT -----------");
        this.conditionsChanged();
    }

}

