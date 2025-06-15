package de.malteee.citysystem.world_managing;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("§o" + player.getName() + " just appeared!");
        player.setPlayerListName("  §6§l" + player.getName() + "  ");
    }
}
