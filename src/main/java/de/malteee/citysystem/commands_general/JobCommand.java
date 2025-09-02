package de.malteee.citysystem.commands_general;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class JobCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        FileConfiguration config = CitySystem.getPlugin().getConfig();
        switch (args[0].toLowerCase()) {
            case "info" -> {
                if (!cPlayer.hasJob()) {
                    player.sendMessage("§cYou currently have no job!");
                    return false;
                }
            }
            case "take" -> {
                if (cPlayer.getJobCooldown() > 0) {
                    player.sendMessage("§cYou have to wait " + cPlayer.getJobCooldown() + " day" + (cPlayer.getJobCooldown() > 1 ? "s" : "") + " until you can take a new job!");
                    return false;
                }

            }
            case "leave" -> {

            }
        }

        return false;
    }
}
