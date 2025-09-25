package de.malteee.citysystem.commands_farming;

import de.malteee.citysystem.CitySystem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FarmworldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if ((CitySystem.farmWorld == null)) {return false;}
        double x = Math.random() * 1000;
        double z = Math.random() * 1000;
        double y = 300;
        Location loc = new Location(CitySystem.farmWorld, x, y, z);
        while (loc.getBlock().getType().equals(Material.AIR))
            loc.setY(loc.getY() - 1);
        loc.setY(loc.getY() + 1);
        player.teleport(loc);
        return false;
    }
}
