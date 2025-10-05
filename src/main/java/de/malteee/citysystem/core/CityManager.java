package de.malteee.citysystem.core;

import de.malteee.citysystem.CitySystem;
import org.bukkit.Location;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CityManager {

    private ArrayList<City> cities = new ArrayList<>();

    public CityManager() {
        try {
            ResultSet rs = CitySystem.getDatabase().getResult("SELECT CITY_ID FROM tbl_city");
            while (rs.next()) {
                cities.add(new City(rs.getString("CITY_ID")));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public void removeCity(City city) {

    }


}
