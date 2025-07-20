package de.malteee.citysystem.jobs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length < 1) return false;
        switch (args[0].toLowerCase()) {
            case "set" -> {

            }
            case "info" -> {

            }
        }
        return false;
    }

}
