package de.malteee.citysystem;

import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.database.Database;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CitySystem extends JavaPlugin {

    private static CitySystem plugin;
    private static Database db;

    private static HashSet<CityPlayer> players = new HashSet<>();

    public void onEnable() {
        plugin = this;
        db = new Database().connect("database");

    }

    public void onDisable() {

    }

    public static void addPlayer(CityPlayer player) {
        players.add(player);
    }

    public static void removePlayer(CityPlayer player) {
        players.remove(player);
    }

    public static CityPlayer getCityPlayer(Player player) {
        for (CityPlayer pl : players) {
            if (pl.toPlayer().equals(player))
                return pl;
        }
        return null;
    }

    public static Set<CityPlayer> getCityPlayers() {
        return players;
    }

    public static Database getDatabase() {
        return db;
    }

    public static CitySystem getPlugin() {
        return plugin;
    }
}
