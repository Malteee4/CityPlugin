package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import org.bukkit.Bukkit;

import java.time.LocalDate;

public class Timer {

    public static int day = LocalDate.now().getDayOfMonth();
    public static final int MOT_MAX = 300;

    static {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CitySystem.getPlugin(), () -> {
            boolean reset = false;
            if (LocalDate.now().getDayOfMonth() != day) {
                day = LocalDate.now().getDayOfMonth();
                reset = true;
            }
            for (CityPlayer cPlayer : CitySystem.getCityPlayers()) {
                if (reset) {
                    cPlayer.getKonto().clearMot();
                    cPlayer.setBlocksWilderness(0);
                }
                if (cPlayer.getKonto().getMot() < MOT_MAX) {
                    cPlayer.getKonto().addMot(cPlayer.getKonto().motPerMinute);
                }
            }
        }, 0, 20 * 60);
    }
}
