package de.malteee.citysystem.chat;

import de.malteee.citysystem.CitySystem;
import org.bukkit.Bukkit;

public class MessageBroadcaster {


    public MessageBroadcaster() {
        try {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(CitySystem.getPlugin(), () -> {

            }, 0, 20 * 60 * 10);
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
