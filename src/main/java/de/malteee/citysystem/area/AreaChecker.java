package de.malteee.citysystem.area;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.City;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.utilities.Tools;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class AreaChecker implements Listener {

    public static ArrayList<SuperiorArea> superiorAreas = new ArrayList<>();
    public static HashSet<Area> areas = new HashSet<>();

    public static void initializeAreas() {
        try {
            ResultSet rs = CitySystem.getDatabase().getResult("SELECT * FROM tbl_superior_areas");
            while (rs.next()) {
                superiorAreas.add(new SuperiorArea(Tools.getLocFromString(rs.getString("LOC1"), CitySystem.getPlugin()), Tools.getLocFromString(rs.getString("LOC2"), CitySystem.getPlugin()),
                        rs.getString("AREA_ID")));
            }rs.close();
            if (superiorAreas.isEmpty())
                initializeSuperiorAreas(new Location(CitySystem.mainWorld, -9570, 100, -10050), 2011, 9);
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = CitySystem.getDatabase().getResult("SELECT * FROM tbl_areas");
            while (rs.next()) {
                areas.add(new Area(Tools.getLocFromString(rs.getString("LOC1"), CitySystem.getPlugin()), Tools.getLocFromString(rs.getString("LOC2"), CitySystem.getPlugin()),
                        Area.AreaType.valueOf(rs.getString("TYPE")), getSuperiorByID(rs.getString("SUPERIOR")), false));
            }rs.close();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        startChecking();
    }

    private static SuperiorArea getSuperiorByID(String id) {
        for (SuperiorArea area : superiorAreas) {
            if (area.getId().equalsIgnoreCase(id))
                return area;
        }return null;
    }

    private static void initializeSuperiorAreas(Location start, int size, int rowCount) {
        int currentX = start.getBlockX(), currentZ = start.getBlockZ();
        Location loc1 = start.clone();
        Location loc2 = start.clone();
        loc2.setX(loc2.getBlockX() + size - 1);
        loc2.setZ(loc2.getBlockZ() + size - 1);
        for (int i = 0; i < rowCount; i++) {
            for (int i2 = 0; i2 < rowCount; i2++) {
                superiorAreas.add(new SuperiorArea(loc1, loc2));
                currentX += size;
                loc1.setX(currentX);
                loc2.setX(currentX + size - 1);
            }
            currentZ += size;
            currentX = start.getBlockX();
            loc1.setZ(currentZ);
            loc2.setZ(currentZ + size - 1);
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
                    for (CityPlayer pl : possible) {
                        boolean inArea = false;
                        for (Area a : area.getAreas()) {
                            if (a.isPlayerIn(pl.toPlayer())) {
                                inArea = true;
                                if (pl.getCurrentArea() != null)
                                    if (pl.getCurrentArea().equals(a)) continue;
                                if (a.getType().equals(Area.AreaType.SPAWN))
                                    pl.toPlayer().sendMessage("§aYou've entered a spawn area!");
                                pl.setCurrentArea(a);
                                //TODO: check area type
                                //if on plot -> set as current area
                                //else if in city -> set city area as current area
                                //if in wilderness -> set current area as null
                            }
                        }if (!inArea) pl.setCurrentArea(null);
                    }
                }
            }
        }, 0, 8);
    }

    public static Area getAreaByLocation(Location location) {
        Optional<Area> area = areas.stream().filter(a -> a.partOf(location)).findFirst();
        return area.orElse(null);
    }

    public static Area getAreaByID(String id) {
        Optional<Area> area = areas.stream().filter(a -> a.id.equalsIgnoreCase(id)).findFirst();
        return area.orElse(null);
    }

    public static SuperiorArea getSuperiorByLocation(Location location) {

        return null;
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
