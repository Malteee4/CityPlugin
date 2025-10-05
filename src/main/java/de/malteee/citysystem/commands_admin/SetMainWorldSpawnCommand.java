package de.malteee.citysystem.commands_admin;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.Area;
import de.malteee.citysystem.area.SuperiorArea;
import de.malteee.citysystem.commands_general.WorldSpawnCommand;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMainWorldSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (!player.hasPermission("CitySystem.setMainSpawn")) return false;
        Location loc = player.getLocation();
        Location min = new Location(loc.getWorld(), loc.getBlockX() - 10, loc.getY(), loc.getBlockZ() - 10);
        Location max = new Location(loc.getWorld(), loc.getBlockX() + 10, loc.getY(), loc.getBlockZ() + 10);
        try {
            CitySystem.getDatabase().execute("DELETE FROM tbl_areas WHERE TYPE='" + Area.AreaType.SPAWN + "'");
        } catch (Exception ignored) {}
        if (!WorldSpawnCommand.worldSpawn.containsKey(loc.getWorld())) {
            CitySystem.getDatabase().execute("INSERT INTO tbl_properties(CODE, VALUE) VALUES('mainspawn', '" + Tools.locationToString(loc) + "')");
        }else {
            CitySystem.getDatabase().execute("UPDATE tbl_properties SET VALUE='" + Tools.locationToString(loc) + "' WHERE CODE='mainspawn'");
        }
        new Area(min, max, Area.AreaType.SPAWN, CitySystem.getCityPlayer(player).getSuperiorArea(), true);
        player.sendMessage("Spawn area has been created!");
        WorldSpawnCommand.worldSpawn.put(loc.getWorld(), loc);
        return false;
    }
}
