package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.jobs.Job;
import de.malteee.citysystem.money_system.Konto;
import de.malteee.citysystem.area.Area;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.ResultSet;

public class CityPlayer {

    private Player player;
    private Konto konto;
    private Residential residential;
    private Area currentArea, superiorArea;
    private Job job;

    public static final int BLOCKS_MAX = 200;
    private int blocks_wild = 0;   //there is a maximum of how many blocks you're allowed to break and place in the wilderness
    private boolean buildAllowed, inWilderness;

    private Location[] markedLocations = new Location[2];

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

    public boolean isInWilderness() {
        return inWilderness;
    }

    public void setInWilderness(boolean b) {
        this.inWilderness = b;
    }

    public int getBlocksInWilderness() {
        return blocks_wild;
    }

    public void setBlocksWilderness(int i) {
        blocks_wild = i;
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

    public Job getJob() {
       return job;
    }

    public void setJob(Job job) {
       this.job = job;
    }

    public void setMarked(Location loc, int index) {
       markedLocations[index] = loc;
    }

    public boolean isMarked(int index) {
       return !(markedLocations[index] == null);
    }
}
