package de.malteee.citysystem.core;

import de.malteee.citysystem.utilities.Area;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Plot {

    private final String id;
    private final City city;
    private List<Area> areas;

    protected Plot(String id, City city) {
        this.id = id;
        this.city = city;
    }

    public void addArea() {

    }

    public void removeArea() {

    }

    public boolean isInside(Player player) {
        return false;
    }

    public City getCity() {
        return city;
    }

    public List<Area> getAreas() {
        return areas;
    }
}
