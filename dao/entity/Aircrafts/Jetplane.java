package dao.entity.Aircrafts;

import dao.entity.Aircraft;
import dao.entity.Coordinates;
import dao.entity.WeatherTower;
import dao.model.Flyable;

import java.util.HashMap;

/**
 * @Author Chouaib
 * @Date 24-11-2021
 * @Project : Avaj-launcher-42
 */
public class Jetplane extends Aircraft implements Flyable {
    private final HashMap<String, String> updateStatus = new HashMap<>() {{
        put("SUN", "WAA CHMIIIICHAAA HIA");
        put("RAIN", "ALLAAAH 3LA CHTIWA");
        put("FOG", "WA DBAAABAA MAKNCHUF WALOOOO");
        put("SNOW", "TELJ TELJ TELJ");
        put("GROUNDED", "No Songs !!! There's no landing here but I have to ground now!");
    }};

    private final HashMap<String, int[]> changes = new HashMap<>() {
        {
            put("SUN", new int[]{0, 10, 2});
            put("RAIN", new int[]{0, 5, 0});
            put("FOG", new int[]{0, 1, 0});
            put("SNOW", new int[]{0, 0, -7});
        }
    };
    private WeatherTower tower;

    public Jetplane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    // overriding method of interface flyable

    /**
     * This method allows to update conditions based on coordinates of flyable and changes happening for each aircraft
     */
    @Override
    public void updateConditions() {
        String weather = tower.getWeather(this.coordinates);
        this.updatePosition(weather, changes);
        System.out.println("-Jetplane: " + this.name + "[" + this.id + "] sings !" + updateStatus.get(weather));
        if (this.coordinates.getHeight() <= 0) {
            this.unregisterTower();
        }
    }

    // register the aircraft to the given tower and put a msg abt it
    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.tower = weatherTower;
        this.tower.register(this);
        System.out.println("UPDATE -> : -Jetplane: " + this.name + "[" + this.id + "]" + " registered to weather tower.");
    }

    // unregister the aircraft from the given tower
    @Override
    public void unregisterTower() {
        System.out.println("-Jetplane: " + this.name + "[" + this.id + "] sings !" + updateStatus.get("GROUNDED")+
                "\n***TO EVERYONE : -Jetplane: " + this.name + "[" + this.id + "]" + ": landing."+
                "\nPosition status ->"+
                "\n-Longitude:" + this.coordinates.getLongitude()+
                "\n-Latitude:" + this.coordinates.getLatitude());
        this.tower.unregister(this);
        System.out.println("UPDATE -> : -Jetplane: " + this.name + "[" + this.id + "]" + " unregistered from weather tower.");
    }
}
