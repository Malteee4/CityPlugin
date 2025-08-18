package de.malteee.citysystem.world_managing;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.commands_admin.SetWorldSpawnCommand;
import de.malteee.citysystem.commands_general.WorldSpawnCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage("");
    }

    @EventHandler
    public void handlePlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(WorldSpawnCommand.worldSpawn.get(CitySystem.spawnWorld));
    }
}
