package de.malteee.citysystem.area;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.City;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

public class SuperiorArea extends Area {

    private ArrayList<Area> areas = new ArrayList<>();

    public SuperiorArea(Location loc1, Location loc2) {
        super(loc1, loc2, AreaType.SUPERIOR);
        try {
            CitySystem.getDatabase().execute("INSERT INTO tbl_superior_areas(AREA_ID, LOC1, LOC2) VALUES('" + super.id + "', '"
                    + Tools.locationToString(loc1) + "', '" + Tools.locationToString(loc2) + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SuperiorArea(Location loc1, Location loc2, String id) {
        super(loc1, loc2, AreaType.SUPERIOR);
    }

    public void addArea(Area area) {
        if (areas.contains(area)) return;
        if (area.getMaxX() > super.getMaxX() && area.getMinX() < super.getMinX() && area.getMaxZ() > super.getMaxZ() && area.getMinZ() < super.getMinZ()) return;
        areas.add(area);
    }

    public void removeArea(Area area) {
        areas.remove(area);
    }

    @Override
    public void delete() {
        for (Area area : areas)
            area.delete();
        try {
            CitySystem.getDatabase().execute("DELETE * FROM tbl_superior_areas WHERE AREA_ID='" + this.id + "'");
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<Area> getAreas() {
        return areas;
    }

}
