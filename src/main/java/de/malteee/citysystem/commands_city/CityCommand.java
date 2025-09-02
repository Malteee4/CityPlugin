package de.malteee.citysystem.commands_city;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CityCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) return false;
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        switch (args[0].toLowerCase()) {
            case "info" -> {

            }
            case "create" -> {

            }
            case "expand" -> {

            }
            case "extensions" -> {

            }
            case "list" -> {

            }
            case "spawn" -> {

            }
            case "tp" -> {

            }
        }
        return false;
    }
}
