package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.money_system.Konto;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class CityPlayer {

    private Player player;
    private Konto konto;
    private Residential residential;

    public CityPlayer(Player player) {
        this.player = player;

        try {
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_players WHERE UUID = '" + player.getUniqueId().toString() + "'").executeQuery();
            while (rs.next()) {

            }

        }catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public Player toPlayer() {
        return player;
    }

    public Konto getKonto() {
        return konto;
    }
}
