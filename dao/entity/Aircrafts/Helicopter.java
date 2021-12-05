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
public class Helicopter extends Aircraft implements Flyable {
    private final HashMap<String, String> updateStatus = new HashMap<String, String>() {
        /**
        *
        */
        private static final long serialVersionUID = 1L;

        {
        put("SUN", "HELI HELI HELI ... TA7ya Lchemch");
        put("RAIN", "HELI HELI HELI ... Lah y3tena 3la 9ed nfa3");
        put("FOG", "MAKIN LA HELI LA WALO ... DBAABAA");
        put("SNOW", "HELI HELI ... TA7ya ltelj");
        put("GROUNDED", "No Songs !!! There's no landing here but I have to ground now!");
    }};

    private final HashMap<String, int[]> changes = new HashMap<String, int[]>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            put("SUN", new int[]{10, 0, 2});
            put("RAIN", new int[]{5, 0, 0});
            put("FOG", new int[]{1, 0, 0});
            put("SNOW", new int[]{0, 0, -12});
        }
    };

    private WeatherTower tower;

    // constructors
    public Helicopter(String name, Coordinates coordinates) {
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
        System.out.println("-Helicopter: " + this.name + "[" + this.id + "] sings !" + updateStatus.get(weather));
        if (this.coordinates.getHeight() <= 0) {
            this.unregisterTower();
        }
    }

    // register the aircraft to the given tower
    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        System.out.println("UPDATE -> : -Helicopter: " + this.name + "[" + this.id + "]" + " registered to weather tower.");
    }

    // unregister the aircraft from the given tower
    @Override
    public void unregisterTower() {
        System.out.println("-Helicopter: " + this.name + "[" + this.id + "] sings !" + updateStatus.get("GROUNDED")+
                "\n***TO EVERYONE : -Helicopter: " + this.name + "[" + this.id + "]" + ": landing."+
                "\nPosition status ->"+
                "\n-Longitude:" + this.coordinates.getLongitude()+
                "\n-Latitude:" + this.coordinates.getLatitude());
        this.tower.unregister(this);
        System.out.println("UPDATE -> : -Helicopter: " + this.name + "[" + this.id + "]" + " unregistered from weather tower.");
    }

}
