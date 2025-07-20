package de.malteee.citysystem.commands_city;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlotCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) return false;
        switch (args[0].toLowerCase()) {
            case "info" -> {

            }
            case "create" -> {

            }
            case "rent" -> {

            }
            case "rentable" -> {

            }
            case "edit" -> {

            }
        }
        return false;
    }
}
