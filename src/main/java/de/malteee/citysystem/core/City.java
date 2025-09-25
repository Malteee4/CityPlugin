package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.Area;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class City implements Listener {

    private String name, welcome;
    private Player owner;
    private ArrayList<Player> buildingRight = new ArrayList<>();

    private List<Area> areas;
    private List<Residential> plots;
    private List<Shop> shops;

    private double totalIncome, experience;
    private int daysActive;
    private boolean active = true, publicSpawn = false;
    private Location spawnpoint;

    public City(String name, Player owner, Area area, Location position) {
        try {
            CitySystem.getDatabase().execute("INSERT INTO tbl_city(CITY_ID, WELCOME, SPAWN, PLAYER_ID, DAYS_ACTIVE, PUBLIC_SPAWN) VALUES('" + name + "', 'You've entered a city!', '" +
                    Tools.locationToString(position) + "', '" + owner.getUniqueId().toString() + "', 1, 'FALSE')");
            CitySystem.getDatabase().execute("INSERT INTO tbl_city_areas(CITY_ID, AREA_ID) VALUES('" + name + "', '" + area.getId() + "')");
            daysActive = 1;
            areas.add(area);
            this.owner = owner;
            this.name = name;
            this.welcome = "You've entered a city!";
            this.spawnpoint = position;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public City(String id) {
        try {
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_city WHERE CITY_ID = '" + id + "'").executeQuery();
            while (rs.next()) {
                this.owner = Bukkit.getPlayer(rs.getString("PLAYER_ID"));
                this.welcome = rs.getString("WELCOME");
                this.spawnpoint = Tools.getLocFromString(rs.getString("SPAWN"), CitySystem.getPlugin());
                this.daysActive = rs.getInt("DAYS_ACTIVE");
                this.publicSpawn = rs.getBoolean("PUBLIC_SPAWN");
            }rs.close();
            this.name = id;
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void setSpawnAccess(boolean b) {
        this.publicSpawn = b;
        try {
            CitySystem.getDatabase().execute("UPDATE tbl_city SET PUBLIC_SPAWN = '" + b + "' WHERE CITY_ID = '" + this.name + "'");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isSpawnPublic() {
        return publicSpawn;
    }

    public String getName() {
        return name;
    }

    public String getWelcomeMessage() {
        return welcome;
    }

    public Location getSpawn() {
        return spawnpoint;
    }

    public Player getOwner() {
        return owner;
    }

    public ArrayList<Player> getBuilding_right() {
        return buildingRight;
    }

    public List<Area> getAreas() {
        return areas;
    }
}
