package de.malteee.citysystem.utilities;

import de.malteee.citysystem.area.Area;
import org.bukkit.Location;

import java.util.ArrayList;

public class SuperiorArea extends Area {

    private ArrayList<Area> areas = new ArrayList<>();

    public SuperiorArea(Location loc1, Location loc2, AreaType type) {
        super(loc1, loc2, type);
    }

    public void addArea(Area area) {
        if (areas.contains(area)) return;
        if (area.getMaxX() > super.getMaxX() && area.getMinX() < super.getMinX() && area.getMaxZ() > super.getMaxZ() && area.getMinZ() < super.getMinZ()) return;
        areas.add(area);
    }

    public void removeArea(Area area) {
        areas.remove(area);
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

}
