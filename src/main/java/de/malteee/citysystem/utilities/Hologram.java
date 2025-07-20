package de.malteee.citysystem.utilities;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Hologram {

    public Hologram(String text, Player p, double hoehe) {
        Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + hoehe, p.getLocation().getZ());
        ArmorStand armor = (ArmorStand) p.getLocation().getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
        armor.setGravity(false);
        armor.setCustomName(text);
        armor.setCustomNameVisible(true);
        armor.setVisible(false);
        armor.setBasePlate(false);
    }
}
