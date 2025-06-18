package de.malteee.citysystem.commands_general;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (CitySystem.isRegistered(player)) {
            CityPlayer cPlayer = CitySystem.getCityPlayer(player);
            player.sendMessage("§aYour current balance is: §l" + cPlayer.getKonto().getMoney());
        }else {
            //TODO: error message
        }
        return false;
    }
}
