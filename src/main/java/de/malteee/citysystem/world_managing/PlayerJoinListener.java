package de.malteee.citysystem.world_managing;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.commands_general.WorldSpawnCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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
        if (player.getName().equals("JanikObenaus")) {
            player.teleport(new Location(Bukkit.getWorld("janiksWorld"), -438, 70, 206));
            return;
        }
        if (!CitySystem.isRegistered(player)) {
            CitySystem.registerPlayer(player);
            player.teleport(WorldSpawnCommand.worldSpawn.get(CitySystem.spawnWorld));
            //TODO: new Player
        }else
            CitySystem.loadPlayer(player);
    }
}
