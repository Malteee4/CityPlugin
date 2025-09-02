package de.malteee.citysystem.area;

import de.malteee.citysystem.core.City;
import de.malteee.citysystem.core.Plot;
import org.bukkit.Location;

public class TeleportArea extends Area {

    public TeleportArea(Location loc1, Location loc2, AreaType type, SuperiorArea superior) {
        super(loc1, loc2, type, superior);
    }

}
