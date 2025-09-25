package de.malteee.citysystem.commands_general;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("set")) {
                Location loc = player.getLocation();
                if (!loc.getWorld().equals(CitySystem.mainWorld)) {
                    player.sendMessage("§cYour home point has to be in the main world!");
                    return false;
                }
                CitySystem.getCityPlayer(player).setHomePoint(loc);
                player.sendMessage("§aHome point has been set!");
                return false;
            }
        }
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (cPlayer.hasHomePoint())
            player.teleport(cPlayer.getHomePoint());
        else
            player.sendMessage("§cYour home point hasn't been set yet!");
        return false;
    }
}
