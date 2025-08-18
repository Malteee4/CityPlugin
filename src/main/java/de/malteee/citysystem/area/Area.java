package de.malteee.citysystem.area;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.City;
import de.malteee.citysystem.core.Plot;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class Area implements Listener {

    private final Location loc1, loc2;
    private final int xMax, xMin, yMax, yMin, zMax, zMin;
    protected final String id;
    private final ArrayList<SuperiorArea> superiorAreas = new ArrayList<>();
    private City city;
    private Plot plot;
    private final AreaType type;

    public Area(Location loc1, Location loc2, AreaType type) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.type = type;
        id = "AREA-" + loc1.getBlockX() + "-" + loc1.getBlockY() + "-" + loc1.getBlockZ();

        xMax = Math.max(loc1.getBlockX(), loc2.getBlockX()); xMin = Math.min(loc1.getBlockX(), loc2.getBlockX());
        yMax = Math.max(loc1.getBlockY(), loc2.getBlockY()); yMin = Math.min(loc1.getBlockY(), loc2.getBlockY());
        zMax = Math.max(loc1.getBlockZ(), loc2.getBlockZ()); zMin = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
    }

    public Area(Location loc1, Location loc2, AreaType type, SuperiorArea superior) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.type = type;
        id = "AREA-" + loc1.getBlockX() + "-" + loc1.getBlockY() + "-" + loc1.getBlockZ();

        xMax = Math.max(loc1.getBlockX(), loc2.getBlockX()); xMin = Math.min(loc1.getBlockX(), loc2.getBlockX());
        yMax = Math.max(loc1.getBlockY(), loc2.getBlockY()); yMin = Math.min(loc1.getBlockY(), loc2.getBlockY());
        zMax = Math.max(loc1.getBlockZ(), loc2.getBlockZ()); zMin = Math.min(loc1.getBlockZ(), loc2.getBlockZ());

        if (type != AreaType.SUPERIOR) {
            try {
                CitySystem.getDatabase().getCon().prepareStatement("INSERT INTO tbl_areas(AREA_ID, TYPE, LOC1, LOC2, SUPERIOR) VALUES('" + id + "', '" + type.toString() +
                        "', '" + Tools.locationToString(loc1) + "', '" + Tools.locationToString(loc2) + "', '" + superior.getId() + "')").execute();
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public Area(Location loc1, Location loc2, AreaType type, Plot plot) {
        this(loc1, loc2, type);
        this.plot = plot;
    }
    public Area(Location loc1, Location loc2, AreaType type, City city) {
        this(loc1, loc2, type);
        this.city = city;
    }


    public boolean partOf(Location loc) {
        return ((loc.getBlockX() <= xMax && loc.getBlockX() >= xMin)
                && (loc.getBlockY() <= yMax && loc.getBlockY() >= yMin)
                && (loc.getBlockZ() <= zMax && loc.getBlockZ() >= zMin));
    }

    public ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList();
        World world = loc1.getWorld();
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    locations.add(new Location(world, x, y, z));
                }
            }
        }
        return locations;
    }

    public int getMaxX() {return xMax;}
    public int getMaxY() {return yMax;}
    public int getMaxZ() {return zMax;}

    public int getMinX() {return xMin;}
    public int getMinY() {return yMin;}
    public int getMinZ() {return zMin;}

    public Location getLocOne() {
        return loc1;
    }

    public Location getLocTwo() {
        return loc2;
    }

    public String getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public Plot getPlot() {
        return plot;
    }

    public AreaType getType() {
        return type;
    }

    public boolean isPlayerIn(Player player) {
        Location plLoc = player.getLocation();
        return partOf(plLoc);
    }

    public void delete() {
        try {
            CitySystem.getDatabase().getCon().prepareStatement("DELETE * FROM tbl_areas WHERE AREA_ID='" + this.id + "'").execute();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @EventHandler
    public void handlePlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

    }
    public enum AreaType {
        SUPERIOR,
        CITY,
        PLOT,
        SPAWN
    }
}