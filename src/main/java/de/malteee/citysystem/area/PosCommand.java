package de.malteee.citysystem.area;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class PosCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (label.equalsIgnoreCase("pos1")) {
            CityPlayer cPlayer = CitySystem.getCityPlayer(player);
            cPlayer.setMarked(player.getLocation(), 0);
            player.sendMessage("§ePosition 1 has been marked!");
        }else if (label.equalsIgnoreCase("pos2")){
            CityPlayer cPlayer = CitySystem.getCityPlayer(player);
            cPlayer.setMarked(player.getLocation(), 1);
            player.sendMessage("§ePosition 2 has been marked!");
        }
        return false;
    }
}
