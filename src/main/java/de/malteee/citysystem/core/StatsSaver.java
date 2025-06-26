package de.malteee.citysystem.core;

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

    }

    @EventHandler
    public void handlePlayerPlaceBlock(BlockPlaceEvent event) {

    }

    @EventHandler
    public void handlePlayerKillEntity(EntityDeathEvent event) {

    }

    public static void PlayerLeft() {
        //TODO: safe data
    }

}
