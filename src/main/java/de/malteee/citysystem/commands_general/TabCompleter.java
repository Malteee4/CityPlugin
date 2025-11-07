package de.malteee.citysystem.commands_general;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        switch (args.length) {
            case 0 -> {
                switch (label.toLowerCase()) {
                    case "city" -> {

                    }
                }
            }
            case 1 -> {
                switch (label.toLowerCase()) {
                    case "city" -> {

                    }
                }
            }
            case 2 -> {
                switch (label.toLowerCase()) {
                    case "city" -> {

                    }
                }
            }
        }
        return List.of();
    }
}
