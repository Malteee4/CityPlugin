package de.malteee.citysystem.chat;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.money_system.Konto;
import org.bukkit.Bukkit;

public class MessageBroadcaster {


    public MessageBroadcaster() {
        try {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(CitySystem.getPlugin(), () -> {
                for (CityPlayer player : CitySystem.getCityPlayers()) {
                    player.toPlayer().sendMessage("§6You've got " + player.getKonto().getMot() + "/" + Konto.MOT_MAX + " Shards today for playing!");
                }
            }, 0, 20 * 60 * 10);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
