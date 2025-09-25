package de.malteee.citysystem.commands_general;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length < 2) return false;
        String target = args[0];
        if (Bukkit.getPlayer(target) != null) {
            if (Bukkit.getPlayer(target).equals(player)) {
                player.sendMessage("§cYou can't write a private message to yourself!");
                return false;
            }
            Player receiver = Bukkit.getPlayer(target);
            if (!receiver.isOnline()) {
                player.sendMessage("§cThis Player is offline!");
                return false;
            }
            String message = "";
            for (int i = 1; i < args.length; i++) {
                if (i == (args.length - 1))
                    message = message + args[i];
                else
                    message = message + args[i] + " ";
            }
            receiver.sendMessage("§3§o" + player.getName() + " -> You - §7§o" + message);
            player.sendMessage("§3§oYou -> " + receiver.getName() + " - §7§o" + message);
        }else {
            player.sendMessage("§cPlayer couldn't be found!");
        }
        return false;
    }
}
