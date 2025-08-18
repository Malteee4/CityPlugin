package de.malteee.citysystem.money_system;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;

public class MoneyManager {

    private HashMap<CityPlayer, Konto> konten = new HashMap<>();

    public MoneyManager() {
        try {
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_players").executeQuery();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeKonto() {

    }

    public Konto getKonto(CityPlayer player) {
        return konten.get(player);
    }

}
