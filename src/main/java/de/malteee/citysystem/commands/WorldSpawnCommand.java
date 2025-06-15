package de.malteee.citysystem.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldSpawnCommand implements CommandExecutor {

    public static Location worldSpawn;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (worldSpawn == null) return false;
        player.teleport(worldSpawn);
        return false;
    }
}
