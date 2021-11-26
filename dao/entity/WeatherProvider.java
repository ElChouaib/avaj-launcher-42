package dao.entity;

import dao.service.WeatherService;

/**
 * @Author Chouaib
 * @Date 24-11-2021
 * @Project : Avaj-launcher-42
 */
public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private final String weather[] = {"SUN", "RAIN", "SNOW", "FOG"};

    // constructor
    private WeatherProvider() {
    }

    // methods
    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[WeatherService.getWeatherByCoordinates(coordinates)];
    }
}
