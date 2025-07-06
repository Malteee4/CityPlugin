package de.malteee.citysystem.world_managing;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerManipulateWorldListener implements Listener {


    @EventHandler
    public void handlePlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (player.hasPermission("citysystem.unrestricted_building")) return;
        if (player.getWorld().equals(CitySystem.spawnWorld))
            event.setCancelled(true);
        if (cPlayer.getBlocksInWilderness() == CityPlayer.BLOCKS_MAX) {
            //TODO: message -> building limit in wilderness
            return;
        }else {
            cPlayer.setBlocksWilderness(cPlayer.getBlocksInWilderness() + 1);
        }
    }

    @EventHandler
    public void handlePlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (player.hasPermission("citysystem.unrestricted_building")) return;
        if (player.getWorld().equals(CitySystem.spawnWorld))
            event.setCancelled(true);
        if (cPlayer.getBlocksInWilderness() == CityPlayer.BLOCKS_MAX) {
            //TODO: message -> building limit in wilderness
            return;
        }else {
            cPlayer.setBlocksWilderness(cPlayer.getBlocksInWilderness() + 1);
        }
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equals("Malteee___")) return;
        if (player.getWorld().equals(CitySystem.spawnWorld))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDestroyFarmland(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getName().equals("Malteee___")) return;
        if (player.getWorld().equals(CitySystem.spawnWorld))
            event.setCancelled(true);
    }

}
