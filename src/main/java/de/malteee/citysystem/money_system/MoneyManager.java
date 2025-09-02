package de.malteee.citysystem.money_system;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;

public class MoneyManager {

    private HashMap<CityPlayer, Konto> konten = new HashMap<>();

    public MoneyManager() {

    }

    public void initializeKonto(CityPlayer player) {
        try {
            ResultSet rs = CitySystem.getDatabase().getResult("SELECT * FROM tbl_players WHERE PLAYER_ID='" + player.toPlayer().getUniqueId() + "'");
            if (rs.next()) {
                konten.put(player, new Konto(rs.getInt("MONEY"), player));
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Konto getKonto(CityPlayer player) {
        return konten.get(player);
    }

}
