package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.money_system.Konto;
import de.malteee.citysystem.money_system.MoneyManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;

public class Timer {

    public static int day = LocalDate.now().getDayOfMonth();
    public static final int MOT_MAX = 300;

    public Timer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CitySystem.getPlugin(), () -> {
            boolean reset = false;
            if (LocalDate.now().getDayOfMonth() != day) {
                day = LocalDate.now().getDayOfMonth();
                FileConfiguration config = CitySystem.getPlugin().getConfig();
                config.set("login_today", new ArrayList<>());
                for (String uuid : config.getStringList("job_cooldown"))
                    config.set("job_cooldown." + uuid, Math.max(config.getInt("job_cooldown." + uuid) - 1, 0));
                CitySystem.getPlugin().saveConfig();
                reset = true;
            }
            MoneyManager mm = CitySystem.getMm();
            for (CityPlayer cPlayer : CitySystem.getCityPlayers()) {
                Konto konto = mm.getKonto(cPlayer);
                if (reset) {
                    konto.clearMot();
                    cPlayer.setBlocksWilderness(0);
                }
                if (konto.getMot() < MOT_MAX) {
                    konto.addMot(konto.motPerMinute);
                }
            }
        }, 0, 20 * 60);
    }
}
