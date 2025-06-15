package de.malteee.citysystem;

import de.malteee.citysystem.commands.BreakShopCommand;
import de.malteee.citysystem.commands.SetWorldSpawnCommand;
import de.malteee.citysystem.commands.WorldSpawnCommand;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.database.Database;
import de.malteee.citysystem.utilities.ShopSign;
import de.malteee.citysystem.utilities.Tools;
import de.malteee.citysystem.utilities.WorldCreation;
import de.malteee.citysystem.world_managing.PlayerChatListener;
import de.malteee.citysystem.world_managing.PlayerJoinListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        PluginManager pluginManager = getPlugin().getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new ShopSign(), this);
        pluginManager.registerEvents(new PlayerChatListener(), this);

        getCommand("spawn").setExecutor(new WorldSpawnCommand());
        getCommand("setSpawn").setExecutor(new SetWorldSpawnCommand());
        getCommand("breakShop").setExecutor(new BreakShopCommand());
        getCommand("world").setExecutor(new WorldCreation());

        try {
            ResultSet rs = db.getCon().prepareStatement("SELECT VALUE FROM tbl_properties WHERE CODE='worldspawn'").executeQuery();
            while (rs.next()) {
                String loc = rs.getString("VALUE");
                WorldSpawnCommand.worldSpawn = Tools.getLocFromString(loc, this);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
