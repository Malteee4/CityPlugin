package de.malteee.citysystem.area;

import org.bukkit.Location;

public class TeleportArea extends Area {

    public TeleportArea(Location loc1, Location loc2, AreaType type, SuperiorArea superior, boolean create) {
        super(loc1, loc2, type, superior, create);
    }

}
