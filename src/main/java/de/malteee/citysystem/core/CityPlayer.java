package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.money_system.Konto;
import de.malteee.citysystem.utilities.Area;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class CityPlayer {

    private Player player;
    private Konto konto;
    private Residential residential;
    private Area currentArea, superiorArea;

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
    public void setSuperiorArea(Area area) {
        this.superiorArea = area;
    }

    public Area getSuperiorArea() {
        return superiorArea;
    }

    public void setCurrentArea(Area area) {
        this.currentArea = area;
    }

    public Area getCurrentArea() {
        return currentArea;
    }

    public boolean isInArea() {
        return  currentArea == null;
    }

    public Player toPlayer() {
        return player;
    }

    public Konto getKonto() {
        return konto;
    }
}
