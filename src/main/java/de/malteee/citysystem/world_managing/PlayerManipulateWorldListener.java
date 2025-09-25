package de.malteee.citysystem.world_managing;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.Area;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerManipulateWorldListener implements Listener {

    private final List<Material> mainNotDropable = Arrays.asList(Material.COAL_ORE, Material.COPPER_ORE, Material.COPPER_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.GOLD_ORE,
            Material.DEEPSLATE_COAL_ORE, Material.LAPIS_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.IRON_ORE, Material.DEEPSLATE_COPPER_ORE, Material.DEEPSLATE_EMERALD_ORE,
            Material.DEEPSLATE_GOLD_ORE, Material.DEEPSLATE_IRON_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.DEEPSLATE_REDSTONE_ORE, Material.REDSTONE_ORE);

    public boolean isAllowedToManipulate(Player player) {
        if (player.hasPermission("citysystem.unrestricted_building")) return true;
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (player.getWorld().equals(CitySystem.spawnWorld) || cPlayer.getCurrentArea().getType().equals(Area.AreaType.SPAWN)) {
            return false;
        }
        return true;
    }

    public boolean jobCheck(CityPlayer player, Material target) {
        if (player.getJob().getInvertedBlocks().contains(target))
            return true;
        return false;
    }

    //TODO: check fishing and mob killing

    @EventHandler
    public void handlePlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        if (player.hasPermission("citysystem.unrestricted_building")) return;
        event.setCancelled(!isAllowedToManipulate(player));
        if (player.getWorld().equals(CitySystem.mainWorld)) {
            if (cPlayer.isInWilderness()) {
                if (cPlayer.getBlocksInWilderness() == CityPlayer.BLOCKS_MAX) {
                    //TODO: message -> building limit in wilderness
                    event.setCancelled(true);
                }else {
                    cPlayer.setBlocksWilderness(cPlayer.getBlocksInWilderness() + 1);
                }
            }
            if (mainNotDropable.contains(event.getBlock().getType()))
                event.setDropItems(false);
        }
        event.setCancelled(jobCheck(cPlayer, event.getBlock().getType()));

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

    @EventHandler
    public void onPlayerEnterPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerCreatePortal(PortalCreateEvent event) {
        event.setCancelled(true);
    }
}
