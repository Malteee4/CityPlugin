package de.malteee.citysystem.world_managing;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.Area;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerManipulateWorldListener implements Listener {


    public boolean isAllowedToManipulate(Player player) {
        if (player.hasPermission("citysystem.unrestricted_building")) return true;
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (player.getWorld().equals(CitySystem.spawnWorld) || cPlayer.getCurrentArea().getType().equals(Area.AreaType.SPAWN)) {
            return false;
        }
        return true;
    }

    @EventHandler
    public void handlePlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (player.hasPermission("citysystem.unrestricted_building")) return;
        event.setCancelled(!isAllowedToManipulate(player));
        if (cPlayer.getBlocksInWilderness() == CityPlayer.BLOCKS_MAX) {
            //TODO: message -> building limit in wilderness
            event.setCancelled(true);
        }else {
            cPlayer.setBlocksWilderness(cPlayer.getBlocksInWilderness() + 1);
        }
    }

    @EventHandler
    public void handlePlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (player.hasPermission("citysystem.unrestricted_building")) return;
        event.setCancelled(!isAllowedToManipulate(player));
        if (cPlayer.getBlocksInWilderness() == CityPlayer.BLOCKS_MAX) {
            //TODO: message -> building limit in wilderness
            event.setCancelled(true);
        }else {
            cPlayer.setBlocksWilderness(cPlayer.getBlocksInWilderness() + 1);
        }
    }

    @EventHandler
    public void onPlayerInteractAtEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("citysystem.unrestricted_building")) return;
        event.setCancelled(!isAllowedToManipulate(player));
    }

    @EventHandler
    public void onPlayerDestroyFarmland(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("citysystem.unrestricted_building")) return;
        event.setCancelled(!isAllowedToManipulate(player));
    }

}
