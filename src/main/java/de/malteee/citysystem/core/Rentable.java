package de.malteee.citysystem.core;

public interface Rentable {

    void startRenting(CityPlayer player);
    void setRent(int i);
    int getRent();

}
