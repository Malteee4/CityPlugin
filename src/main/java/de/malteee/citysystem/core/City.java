package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.Area;
import de.malteee.citysystem.area.AreaChecker;
import de.malteee.citysystem.plots.Residential;
import de.malteee.citysystem.plots.Shop;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class City implements Listener {

    private String name, welcome, goodbye;
    private Player owner;
    private ArrayList<Player> buildingRight = new ArrayList<>();
    private List<Expansion> expansions = new ArrayList<>();

    private List<Area> areas = new ArrayList<>();
    private List<Residential> plots = new ArrayList<>();
    private List<Shop> shops = new ArrayList<>();

    private double totalIncome, experience;
    private int daysActive;
    private boolean active = true, publicSpawn = false;
    private Location spawnpoint;

    private Stage stage;

    public City(String name, Player owner, Area area, Location position) {
        try {
            CitySystem.getDatabase().execute("INSERT INTO tbl_city(CITY_ID, WELCOME_MSG, GOODBYE_MSG, SPAWN, PLAYER_ID, DAYS_ACTIVE, PUBLIC_SPAWN, BUILD_RIGHT, EXPANSION) VALUES(" +
                    "'" + name + "', 'You've entered a city!', '', '" + Tools.locationToString(position) + "', '" + owner.getUniqueId().toString() + "', 1, 'FALSE', '', '')");
            CitySystem.getDatabase().execute("INSERT INTO tbl_city_areas(CITY_ID, AREA_ID) VALUES('" + name + "', '" + area.getId() + "')");
            daysActive = 1;
            areas.add(area);
            area.setCity(this);
            this.owner = owner;
            this.name = name;
            this.welcome = "§7You've entered a city!";
            this.spawnpoint = position;
            this.stage = Stage.SETTLEMENT;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public City(String id) {
        try {
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_city WHERE CITY_ID = '" + id + "'").executeQuery();
            while (rs.next()) {
                this.owner = Bukkit.getPlayer(rs.getString("PLAYER_ID"));
                this.welcome = rs.getString("WELCOME_MSG");
                this.goodbye = rs.getString("GOODBYE_MSG");
                this.spawnpoint = Tools.getLocFromString(rs.getString("SPAWN"), CitySystem.getPlugin());
                this.daysActive = rs.getInt("DAYS_ACTIVE");
                this.publicSpawn = rs.getBoolean("PUBLIC_SPAWN");
            }rs.close();
            rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_city_areas WHERE CITY_ID = '" + id + "'").executeQuery();
            while (rs.next()) {
                Area area = AreaChecker.getAreaByID(rs.getString("AREA_ID"));
                if (area != null)
                    this.areas.add(area);
            }
            rs.close();

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

    enum Expansion {

        WELCOME_MSG(1, "Welcome message"),
        GOODBYE_MSG(2, "Goodbye message");

        Expansion(int id, String name) {

        }

    }

    enum Stage {

        SETTLEMENT(2, 0, 0),
        VILLAGE(4, 1, 0),
        SMALL_TOWN(8, 4, 1),
        CITY(15, 8, 2),
        BIG_CITY(20, 10, 2),
        METROPOLIS(28, 14, 4);

        Stage(int residential, int shops, int farms) {

        }
    }
}
