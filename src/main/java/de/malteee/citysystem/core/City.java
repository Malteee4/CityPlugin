package de.malteee.citysystem.core;

import de.malteee.citysystem.utilities.Area;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;

public class City implements Listener {

    private String name;
    private Player owner;

    private List<Area> areas;
    private List<Residential> plots;
    private List<Shop> shops;

    public City() {

    }
}
