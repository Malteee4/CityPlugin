package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;

public class StatsSaver implements Listener {

    private HashMap<Player, Integer> temp_block_breaks = new HashMap<>();
    private HashMap<Player, Integer> temp_block_places = new HashMap<>();
    private HashMap<Player, Integer> temp_entity_kills = new HashMap<>();
    private HashMap<Player, Integer> temp_player_kills = new HashMap<>();

    @EventHandler
    public void handlePlayerBreakBlock(BlockBreakEvent event) {
        if (event.getPlayer().getWorld().equals(CitySystem.spawnWorld)) return;
        if (!temp_block_breaks.containsKey(event.getPlayer())) temp_block_breaks.put(event.getPlayer(), 1);
        else temp_block_breaks.put(event.getPlayer(), temp_block_breaks.get(event.getPlayer()) + 1);
    }

    @EventHandler
    public void handlePlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getWorld().equals(CitySystem.spawnWorld)) return;
        if (!temp_block_places.containsKey(event.getPlayer())) temp_block_places.put(event.getPlayer(), 1);
        else temp_block_places.put(event.getPlayer(), temp_block_places.get(event.getPlayer()) + 1);
    }

    @EventHandler
    public void handlePlayerKillEntity(EntityDeathEvent event) {

    }

    public static void PlayerLeft(Player player) {
        try {

        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
