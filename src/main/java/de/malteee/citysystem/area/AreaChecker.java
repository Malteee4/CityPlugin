package de.malteee.citysystem.area;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.City;
import de.malteee.citysystem.core.CityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AreaChecker implements Listener {

    public static ArrayList<SuperiorArea> superiorAreas = new ArrayList<>();

    public static void initializeAreas() {
        try {
            ResultSet rs = CitySystem.getDatabase().getCon().prepareStatement("SELECT * FROM tbl_superior_areas").executeQuery();
            while (rs.next()) {

                return;
            }
            initializeSuperiorAreas(new Location(CitySystem.mainWorld, -9570, 100, -10050), 2011, 9);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeSuperiorAreas(Location start, int size, int rowCount) {

    }

    private static void createSuperiorArea(Location loc1, Location loc2) {
        try {

        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void startChecking() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CitySystem.getPlugin(), () -> {
            for (SuperiorArea area : superiorAreas) {
                boolean empty = true;
                ArrayList<CityPlayer> possible = new ArrayList<>();
                for (CityPlayer player : CitySystem.getCityPlayers()) {
                    if (area.isPlayerIn(player.toPlayer())) {
                        possible.add(player);
                        player.setSuperiorArea(area);
                        empty = false;
                    }
                }if (!empty) {
                    for (Area a : area.getAreas()) {
                        for (CityPlayer pl : possible) {
                            if (a.isPlayerIn(pl.toPlayer())) {
                                //TODO: check area type
                                //if on plot -> set as current area
                                //else if in city -> set city area as current area
                                //if in wilderness -> set current area as null
                            }
                        }
                    }
                }
            }
        }, 0, 8);
    }

    /*@EventHandler
    public void handlePlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        CityPlayer cPlayer = CitySystem.getCityPlayer(player);
        boolean inArea = cPlayer.isInArea(), newArea = false;
        Area currentArea = cPlayer.getCurrentArea();
        SuperiorArea superiorArea = null;
        for (SuperiorArea area : superiorAreas) {
            if (area.isPlayerIn(player)) {
                superiorArea = area; cPlayer.setSuperiorArea(area);
                break;
            }
        }if (superiorArea == null) return;
        for (Area a : superiorArea.getAreas()) {
            if (a.isPlayerIn(player)) {
                switch (a.getType()) {
                    case CITY -> {
                        City city = a.getCity();
                        if (currentArea != null) {
                            if(currentArea.getType().equals(Area.AreaType.CITY)) {
                                City second = currentArea.getCity();
                                if (!second.equals(city)) {
                                    player.sendMessage("city left"); //second
                                    player.sendMessage("city entered"); //city
                                    break;
                                }
                            }
                        }
                        player.sendMessage("city entered");
                    }
                    case PLOT -> {
                        if (currentArea != null) {
                            if(currentArea.getType().equals(Area.AreaType.CITY)) {
                                City left = currentArea.getCity();
                                player.sendMessage("city left"); //left
                            }
                        }
                    }
                }
                cPlayer.setCurrentArea(a);
                return;
            }
        }cPlayer.setCurrentArea(null);
    }*/
}
