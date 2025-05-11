package de.malteee.citysystem.utilities;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Area {

    private final Location loc1, loc2;
    private final int xMax, xMin, yMax, yMin, zMax, zMin;
    private final String id;

    public Area(Location loc1, Location loc2) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        id = "AREA-" + loc1.getBlockX() + "-" + loc1.getBlockY() + "-" + loc1.getBlockZ();

        xMax = Math.max(loc1.getBlockX(), loc2.getBlockX()); xMin = Math.min(loc1.getBlockX(), loc2.getBlockX());
        yMax = Math.max(loc1.getBlockY(), loc2.getBlockY()); yMin = Math.min(loc1.getBlockY(), loc2.getBlockY());
        zMax = Math.max(loc1.getBlockZ(), loc2.getBlockZ()); zMin = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
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

    public boolean isPlayerIn(Player player) {
        Location plLoc = player.getLocation();
        return partOf(plLoc);
    }
}
