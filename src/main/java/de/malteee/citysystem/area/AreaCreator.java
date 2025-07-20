package de.malteee.citysystem.area;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.City;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AreaCreator implements Listener {

    @EventHandler
    public void handlePlayerUseGoldHoe(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_HOE)) return;
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            CityPlayer cPlayer = CitySystem.getCityPlayer(player);
            cPlayer.setMarked(event.getClickedBlock().getLocation(), event.getAction().equals(Action.LEFT_CLICK_BLOCK) ? 0:1);
            player.sendMessage("§ePosition " + (event.getAction().equals(Action.LEFT_CLICK_BLOCK) ? "1":"2") + " has been marked!");
        }
    }

}
