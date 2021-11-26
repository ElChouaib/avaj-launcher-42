package dao.service;

import dao.entity.Coordinates;

/**
 * This method allows to get the appropriate weather index based on coordinates.
 * index are :
 * + 0 -> SUN
 * + 1 -> RAIN
 * + 2 -> SNOW
 * + 3 -> FOG
 * Rules are simple : - If height > 75  : use this function to determine weather index :
 * weather(height) = 3 * (-1/(1.001)^height  + 1),
 * basically the weather turns into fogs as we increase height
 * - if the latitude is superior than 90, we approach to the pole , it getting snowy
 */
public class WeatherService {


    public static int getWeatherByCoordinates(Coordinates coordinates) {
        double weatherCoef = 1.001;
        if (coordinates.getHeight() < 75 && coordinates.getHeight() > 0) {
            if (coordinates.getLatitude() > 90)
                return 2;
            else
                return ((coordinates.getLatitude() + coordinates.getLongitude())/ coordinates.getHeight() ) % 4;
        } else {
            return (int) Math.round(3 * (-1 / Math.pow(weatherCoef, coordinates.getHeight()) + 1));
        }
    }
}
