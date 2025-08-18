package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.area.SuperiorArea;
import de.malteee.citysystem.jobs.Job;
import de.malteee.citysystem.money_system.Konto;
import de.malteee.citysystem.area.Area;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.ResultSet;

public class CityPlayer {

    private Player player;
    private Residential residential;
    private Area currentArea;
    private SuperiorArea superiorArea;
    private Job job;
    private Konto konto;

    public static final int BLOCKS_MAX = 100;
    private int blocks_wild = 0, days_active;   //there is a maximum of how many blocks you're allowed to break and place in the wilderness
    private boolean buildAllowed, inWilderness;

    private Location[] markedLocations = new Location[2];

   public CityPlayer(Player player) {
        this.player = player;
        try {
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_players WHERE PLAYER_ID = '" + player.getUniqueId().toString() + "'").executeQuery();
            rs.next();

            //TODO: job
            FileConfiguration config = CitySystem.getPlugin().getConfig();
            if (!config.contains("active." + player.getUniqueId().toString()))
                config.set("active." + player.getUniqueId().toString(), 0);
            days_active = config.getInt("active." + player.getUniqueId().toString());
            CitySystem.getPlugin().saveConfig();
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

    public void setSuperiorArea(SuperiorArea area) {
        this.superiorArea = area;
    }

    public SuperiorArea getSuperiorArea() {
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

    public int getDaysActive() {
        return days_active;
    }
}
