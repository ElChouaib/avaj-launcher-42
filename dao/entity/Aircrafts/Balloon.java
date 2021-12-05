package dao.entity.Aircrafts;

import dao.entity.Aircraft;
import dao.entity.Coordinates;
import dao.entity.WeatherTower;
import dao.model.Flyable;

import java.util.HashMap;

/**
 * @Author Chouaib
 * @Date 26-11-2021
 * @Project : Avaj-launcher-42
 */
public class Balloon extends Aircraft implements Flyable {
    private final HashMap<String, String> updateStatus = new HashMap<String, String>() {
        /**
        *
        */
        private static final long serialVersionUID = 1L;

        {
        put("SUN", "Keep your face to the sun and you will never see the shadows.");
        put("RAIN", "Do not be angry with the rain; it simply does not know how to fall upwards.");
        put("FOG", "Sometimes when you lose your way in the fog, you end up in a beautiful place!");
        put("SNOW", "Let it snow! Let it snow! Let it snow!");
        put("GROUNDED", "No Songs !!! There's no landing here but I have to ground now!");
    }};
    private final HashMap<String, int[]> changes = new HashMap<String, int[]>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put("SUN", new int[]{2, 0, 4});
            put("RAIN", new int[]{0, 0, -5});
            put("FOG", new int[]{0, 0, -3});
            put("SNOW", new int[]{0, 0, -15});
        }
    };

    private WeatherTower tower;

    // constructor
    public Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    // overriding methods of interface flyable
    /**
     * This method allows to update conditions based on coordinates of flyable and changes happening for each aircraft
     */
    @Override
    public void updateConditions() {
        String weather = tower.getWeather(this.coordinates);
        this.updatePosition(weather, changes);
        System.out.println("-Balloon: " + this.name + "[" + this.id + "] sings !" + updateStatus.get(weather));
        if (this.coordinates.getHeight() <= 0) {
            this.unregisterTower();
        }
    }

    // register the aircraft to the given tower
    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        System.out.println("UPDATE -> : -Balloon: " + this.name + "[" + this.id + "]" + " registered to weather tower.");
    }

    // unregister the aircraft from the given tower
    @Override
    public void unregisterTower() {
        System.out.println("-Balloon: " + this.name + "[" + this.id + "] sings !" + updateStatus.get("GROUNDED")+
        "\n***TO EVERYONE : -Balloon: " + this.name + "[" + this.id + "]" + ": landing."+
        "\nPosition status ->"+
        "\n-Longitude:" + this.coordinates.getLongitude()+
        "\n-Latitude:" + this.coordinates.getLatitude());
        this.tower.unregister(this);
        System.out.println("UPDATE -> : -Balloon: " + this.name + "[" + this.id + "]" + " unregistered from weather tower.");
    }
}
