package de.malteee.citysystem.world_managing;

import org.bukkit.Location;
import org.bukkit.WorldBorder;

public class Border {

    public Border(Location middle, int size) {
        WorldBorder border = middle.getWorld().getWorldBorder();
        border.setCenter(middle);
        border.setDamageAmount(2);
        border.setSize(size);
        border.setDamageBuffer(1);
    }
}
