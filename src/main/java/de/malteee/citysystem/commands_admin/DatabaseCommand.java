package de.malteee.citysystem.commands_admin;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class DatabaseCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (!(player.hasPermission("CitySystem.database"))) return false;
        if (args.length < 2) return false;
        try {
            Database db = CitySystem.getDatabase();
            String stmt = args[1];
            for (int i = 2; i < args.length; i++)
                stmt = stmt + " " + args[i];
            if (args[0].toLowerCase().equalsIgnoreCase("sel")) {
                ResultSet rs = db.getResult(stmt);
                int colCount = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    String col = "";
                    for (int i = 1; i <= colCount; i++) {
                        col = col + "   " + rs.getObject(i).toString();
                    }player.sendMessage(col);
                }
            }else if (args[0].equalsIgnoreCase("exe")) {
                db.execute(stmt);
                player.sendMessage("Statement executed!");
            }
        }catch (Exception exception) {
            player.sendMessage("An error occurred!");
        }
        return false;
    }
}
