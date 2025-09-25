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
    public static final int MOT_MAX = 300;
    private CityPlayer owner;

    public Konto(int balance) {
        this.money = balance;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double i) {
        if (i < 0) return;
        this.money = i;
    }

    public void addMoney(double i) {
        this.money += i;
    }

    public void removeMoney(double i) {

    }

    public void transfer(CityPlayer sender, double i) {
        
    }

    public void save()  {

    }

    public void transfer(Konto receiver) {

    }
}
