package de.malteee.citysystem.commands;

import java.util.HashMap;

import de.malteee.citysystem.CitySystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BreakShopCommand implements CommandExecutor{


    private static HashMap<Player, Boolean> breakShop = new HashMap<>();
    private static HashMap<Player, Integer> ids = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("ShopPlugin.breakshop")) {
                breakShop.put(p, true); p.sendMessage("§2Du kannst nun einen Shop zerstören der dir nicht gehört!");
                ids.put(p, Bukkit.getScheduler().scheduleSyncDelayedTask(CitySystem.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        if(breakShop.containsKey(p)) {
                            breakShop.remove(p);
                            ids.remove(p);
                        }
                    }
                }, 20*16));
            }
        }

        return false;
    }
    public static void stopId(Player p) {
        if(ids.containsKey(p)) {
            Bukkit.getScheduler().cancelTask(ids.get(p));
            if(breakShop.containsKey(p)) {
                breakShop.remove(p);
            }
        }
    }
    public static Boolean canBreakShop(Player p) {
        if(breakShop.containsKey(p)) {
            return breakShop.get(p);
        }else {
            return false;
        }
    }
}

