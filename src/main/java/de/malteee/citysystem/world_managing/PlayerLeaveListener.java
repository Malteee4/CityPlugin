package de.malteee.citysystem.world_managing;

import de.malteee.citysystem.CitySystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {


    @EventHandler
    public void handlePlayerLeave(PlayerQuitEvent event) {
        CitySystem.removePlayer(CitySystem.getCityPlayer(event.getPlayer()));

    }

}
