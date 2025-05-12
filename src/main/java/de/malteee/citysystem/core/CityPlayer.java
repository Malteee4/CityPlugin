package de.malteee.citysystem.core;

import de.malteee.citysystem.money_system.Konto;
import org.bukkit.entity.Player;

public class CityPlayer {

    private Player player;
    private Konto konto;

    public CityPlayer(Player player) {
        this.player = player;
        konto = new Konto();

    }

    public Player toPlayer() {
        return player;
    }
}
