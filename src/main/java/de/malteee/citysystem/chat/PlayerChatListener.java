package de.malteee.citysystem.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void handlePlayerChat(AsyncPlayerChatEvent event) {
        String format = "§3<player> | §r<message>";

        format = format.replace("<player>", event.getPlayer().getName());
        format = format.replace("<message>", event.getMessage());
        event.setFormat(format);

    }

}
