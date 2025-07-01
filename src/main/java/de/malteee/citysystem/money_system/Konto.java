package de.malteee.citysystem.money_system;

import de.malteee.citysystem.CitySystem;
import de.malteee.citysystem.core.CityPlayer;
import de.malteee.citysystem.database.Database;
import org.bukkit.Bukkit;

import java.time.LocalDate;
import java.util.Date;

public class Konto {

    private double money;
    private double mot = 0;
    public double motPerMinute = 1;

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
                if (reset) cPlayer.getKonto().clearMot();
                if (cPlayer.getKonto().getMot() < MOT_MAX) {
                    cPlayer.getKonto().addMot(cPlayer.getKonto().motPerMinute);
                }
            }
        }, 0, 20 * 60);
    }

    public Konto(int balance) {
        this.money = balance;

    }

    public double getMot() {
        return mot;
    }

    public void addMot(double i) {
        if (mot + i > MOT_MAX) {
            double a = (mot + i) - MOT_MAX;
            this.mot += a;
            this.money += a;
        }else {
            this.mot += i;
            this.money += i;
        }
    }

    public void clearMot() {
        this.mot = 0;
    }

    public int getMoney() {

        return -1;
    }

    public void setMoney(int i) {

    }

    public void addMoney(int i) {

    }

    public void removeMoney(int i) {

    }

    private void save()  {

    }

    private void transfer(Konto receiver) {

    }
}
