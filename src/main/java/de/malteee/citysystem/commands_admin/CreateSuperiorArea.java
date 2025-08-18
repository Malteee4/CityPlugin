package de.malteee.citysystem.commands_admin;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.Area;
import de.malteee.citysystem.area.AreaChecker;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.area.SuperiorArea;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Deprecated
public class CreateSuperiorArea implements CommandExecutor {

    private Location loc1, loc2;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length != 1) return false;
        switch (args[0].toLowerCase()) {
            case "set1" -> {
                if(player.getTargetBlockExact(10) == null) return false;
                loc1 = player.getTargetBlockExact(10).getLocation();
                player.sendMessage("§aLocation 1 set!");
            }
            case "set2" -> {
                if(player.getTargetBlockExact(10) == null) return false;
                loc2 = player.getTargetBlockExact(10).getLocation();
                player.sendMessage("§aLocation 2 set!");
            }
            case "create" -> {
                if (loc1 != null && loc2 != null) {
                    SuperiorArea area = new SuperiorArea(loc1, loc2, Area.AreaType.SUPERIOR);
                    for (Location location : area.getLocations()) {
                        for (Area a : AreaChecker.superiorAreas) {
                            if (a.partOf(location)) {
                                player.sendMessage("§4Area part of another area!");
                                return false;
                            }
                        }
                    }
                    player.sendMessage("§aArea created!");
                }
            }
            case "delete" -> {
                CityPlayer cPlayer = CitySystem.getCityPlayer(player);
                if (cPlayer.getSuperiorArea() == null) {
                    player.sendMessage("§4You're not standing inside an area!");
                    return false;
                }
                cPlayer.getSuperiorArea().delete();
                player.sendMessage("§aArea removed!");
            }
        }

        return false;
    }
}
