package de.malteee.citysystem;

import de.malteee.citysystem.database.Database;
import org.bukkit.plugin.java.JavaPlugin;

public class CitySystem extends JavaPlugin {

    private static CitySystem plugin;
    private static Database db;

    public void onEnable() {
        plugin = this;
        db = new Database().connect("database");

    }

    public void onDisable() {

    }

    public static Database getDatabase() {
        return db;
    }

    public static CitySystem getPlugin() {
        return plugin;
    }
}
