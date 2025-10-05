package de.malteee.citysystem.commands_city;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.money_system.Konto;
import de.malteee.citysystem.utilities.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CityCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) return false;
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (cPlayer == null) return false;
        switch (args[0].toLowerCase()) {
            case "buy" -> {
                Konto konto = CitySystem.getMm().getKonto(cPlayer);
                if (konto.getMoney() < 1000) {
                    cPlayer.toPlayer().sendMessage("§cYou don't have enough money to buy a city! A city costs 1000 Shards!");
                    return false;
                }
                konto.removeMoney(1000);
                player.getInventory().addItem(new ItemBuilder(Material.BEDROCK, 1).setName("§6§l____").setLore().build());
                player.sendMessage("§aYou've successfully bought a ____! Place it on the ground to found a city!");
            }
            case "info" -> {

            }
            case "create" -> {

            }
            case "expand" -> {

            }
            case "extensions" -> {

            }
            case "list" -> {

            }
            case "spawn" -> {

            }
            case "tp" -> {

            }
            case "overview" -> {
                //TODO: gui with general information, button to change name, button to set spawn, button to plot overview
            }
        }
        return false;
    }
}
