package de.malteee.citysystem.money_system;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class MoneyManager {

    private HashMap<UUID, Konto> konten = new HashMap<>();

    public MoneyManager() {
        for (OfflinePlayer offPlayer : Bukkit.getOfflinePlayers()) {
            try {
                ResultSet rs = CitySystem.getDatabase().getResult("SELECT * FROM tbl_players WHERE PLAYER_ID='" + offPlayer.getUniqueId() + "'");
                if (rs.next()) {
                    konten.put(offPlayer.getUniqueId(), new Konto(rs.getInt("MONEY")));
                }rs.close();
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void saveScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CitySystem.getPlugin(), () -> {
            try {
                Database db = CitySystem.getDatabase();
                konten.entrySet().forEach(entry -> {
                    db.execute("UPDATE tbl_players SET MONEY=" + entry.getValue().getMoney() + " WHERE UUID='" + entry.getKey().toString() + "'");
                });
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        }, 0, 20 * 30);
    }

    public void createKonto(CityPlayer player) {
        try {
            konten.put(player.toPlayer().getUniqueId(), new Konto(0));
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public Konto getKonto(CityPlayer player) {
        return konten.get(player.toPlayer().getUniqueId());
    }

    public Konto getKonto(String name) {
        for (CityPlayer cPlayer : CitySystem.getCityPlayers()) {
            if (cPlayer.toPlayer().getName().equalsIgnoreCase(name))
                return konten.get(cPlayer);
        }
        return null;
    }
}
