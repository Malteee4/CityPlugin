package de.malteee.citysystem;

import de.malteee.citysystem.commands_admin.BreakShopCommand;
import de.malteee.citysystem.commands_admin.SetWorldSpawnCommand;
import de.malteee.citysystem.commands_general.*;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.core.StatsSaver;
import de.malteee.citysystem.database.Database;
import de.malteee.citysystem.utilities.*;
import de.malteee.citysystem.chat.PlayerChatListener;
import de.malteee.citysystem.world_managing.PlayerJoinListener;
import de.malteee.citysystem.world_managing.PlayerLeaveListener;
import de.malteee.citysystem.world_managing.PlayerManipulateWorldListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CitySystem extends JavaPlugin {

    private static CitySystem plugin;
    private static Database db;

    private static HashSet<CityPlayer> players = new HashSet<>();

    public static World spawnWorld = Bukkit.getWorld("world");
    public static World mainWorld = Bukkit.getWorld("mainWorld");
    public static World farmWorld = Bukkit.getWorld("farmWorld");

    private List<String> maps = getConfig().getStringList("worlds");

    public void onEnable() {
        plugin = this;
        db = new Database().connect("database");

        PluginManager pluginManager = getPlugin().getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new ShopSign(), this);
        pluginManager.registerEvents(new PlayerChatListener(), this);
        pluginManager.registerEvents(new PlayerLeaveListener(), this);
        pluginManager.registerEvents(new PlayerManipulateWorldListener(), this);
        pluginManager.registerEvents(new StatsSaver(), this);

        getCommand("spawn").setExecutor(new WorldSpawnCommand());
        getCommand("setSpawn").setExecutor(new SetWorldSpawnCommand());
        getCommand("breakShop").setExecutor(new BreakShopCommand());
        getCommand("world").setExecutor(new WorldCreation());
        getCommand("money").setExecutor(new MoneyCommand());
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("farmWorld").setExecutor(new FarmworldCommand());

        for(int i = 0; i<maps.size(); i++) {
            WorldCreator w = (WorldCreator) new WorldCreator(maps.get(i)).type(WorldType.NORMAL);
            Bukkit.createWorld(w);
            Bukkit.getWorlds().add(Bukkit.getWorld(maps.get(i)));
        }

        try {
            ResultSet rs = db.getCon().prepareStatement("SELECT VALUE FROM tbl_properties WHERE CODE='worldspawn'").executeQuery();
            while (rs.next()) {
                String loc = rs.getString("VALUE");
                WorldSpawnCommand.worldSpawn.put(spawnWorld, Tools.getLocFromString(loc, this));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AreaChecker.initializeAreas();

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            mainWorld = Bukkit.getWorld("mainWorld");
            farmWorld = Bukkit.getWorld("farmWorld");
        }, 160);
    }

    public void onDisable() {

    }

    public static boolean isRegistered(Player player) {
        try {
            ResultSet rs = db.getCon().prepareStatement("SELECT * FROM tbl_players WHERE UUID = '" + player.getUniqueId().toString() + "'").getResultSet();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void registerPlayer(Player player) {

    }

    public static void loadPlayer(Player player) {
        try {
            ResultSet rs = db.getCon().prepareStatement("SELECT * FROM tbl_players WHERE UUID = '" + player.getUniqueId().toString() + "'").getResultSet();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
