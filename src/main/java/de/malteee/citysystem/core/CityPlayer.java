package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.jobs.Job;
import de.malteee.citysystem.money_system.Konto;
import de.malteee.citysystem.utilities.Area;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class CityPlayer {

    private Player player;
    private Konto konto;
    private Residential residential;
    private Area currentArea, superiorArea;
    private boolean buildAllowed;
    private Job job;

    public CityPlayer(Player player) {
        this.player = player;
        try {
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_players WHERE PLAYER_ID = '" + player.getUniqueId().toString() + "'").executeQuery();
            rs.next();
            konto = new Konto(rs.getInt("MONEY"));
            //TODO: job
            rs.close();
        }catch (Exception exception) {
            exception.printStackTrace();
        }

    }
    public boolean isBuildAllowed() {
        return buildAllowed;
    }

    public void setBuildAllowed(boolean b) {
        buildAllowed = b;
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
