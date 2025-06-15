package de.malteee.citysystem.commands;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWorldSpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if(!(player.hasPermission("citysystem.setspawn"))) return false;
        String location = Tools.locationToString(player.getLocation());
        try {
            if (WorldSpawnCommand.worldSpawn == null) {
                CitySystem.getDatabase().getCon().prepareStatement("INSERT INTO tbl_properties(CODE, VALUE) VALUES('worldspawn', '" + location + "')").execute();
            }else {
                CitySystem.getDatabase().getCon().prepareStatement("UPDATE tbl_properties SET VALUE='" + location + "'").execute();
            }
            WorldSpawnCommand.worldSpawn = player.getLocation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendMessage("§aWorldspawn has been set!");
        return false;
    }
}
