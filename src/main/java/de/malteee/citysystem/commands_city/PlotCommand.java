package de.malteee.citysystem.commands_city;

import de.malteee.citysystem.area.Area;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlotCommand implements CommandExecutor {

    private HashMap<UUID, ArrayList<Area>> creatingPlot = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) return false;
        switch (args[0].toLowerCase()) {
            case "info" -> {

            }
            case "create" -> {
                if (args.length > 1) {
                    switch (args[1].toLowerCase()) {
                        case "addArea" -> {

                        }
                        case "cancel" -> {

                        }
                        case "confirm" -> {

                        }
                        case "start" -> {

                        }
                    }
                }
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
