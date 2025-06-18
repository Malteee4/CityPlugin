package de.malteee.citysystem.commands_general;

import de.malteee.citysystem.CitySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainWorldCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (!WorldSpawnCommand.worldSpawn.containsKey(CitySystem.mainWorld)) return false;
        player.teleport(WorldSpawnCommand.worldSpawn.get(CitySystem.mainWorld));
        return false;
    }
}
