package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.Area;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.ResultSet;
import java.util.List;

public class City implements Listener {

    private String name, welcome;
    private Player owner;

    private List<Area> areas;
    private List<Residential> plots;
    private List<Shop> shops;

    private double totalIncome, experience;
    private int days_active;
    private boolean active = true;
    private Location spawnpoint;

    public City(String name, Player owner, Area area, Location position) {
        try {
            CitySystem.getDatabase().getCon().prepareStatement("INSERT INTO tbl_cities(CITY_ID, WELCOME, SPAWN, PLAYER_ID, DAYS_ACTIVE) VALUES('" + name + "', 'You've entered a city!', '" +
                    Tools.locationToString(position) + "', '" + owner.getUniqueId().toString() + "', 1)").execute();
            CitySystem.getDatabase().getCon().prepareStatement("INSERT INTO tbl_city_areas(CITY_ID, AREA_ID) VALUES('" + name + "', '" + area.getId() + "')").execute();
            days_active = 1;
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
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_cities WHERE CITY_ID = '" + id + "'").executeQuery();
            while (rs.next()) {
                this.owner = Bukkit.getPlayer(rs.getString("PLAYER_ID"));
                this.welcome = rs.getString("WELCOME");
                this.spawnpoint = Tools.getLocFromString(rs.getString("SPAWN"), CitySystem.getPlugin());
                this.days_active = rs.getInt("DAYS_ACTIVE");
            }
            this.name = id;
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
