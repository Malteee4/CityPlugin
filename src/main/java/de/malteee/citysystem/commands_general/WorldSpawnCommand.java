package de.malteee.citysystem.commands_general;

import de.malteee.citysystem.CitySystem;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WorldSpawnCommand implements CommandExecutor {

    public static HashMap<World, Location> worldSpawn = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (!worldSpawn.containsKey(CitySystem.spawnWorld)) return false;
        player.teleport(worldSpawn.get(CitySystem.spawnWorld));
        return false;
    }
}
