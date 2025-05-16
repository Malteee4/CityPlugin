package de.malteee.citysystem.core;

public class Residential extends Plot implements Rentable {


    protected Residential(String id, City city) {
        super(id, city);
    }

    @Override
    public void startRenting(CityPlayer player) {

    }

    @Override
    public void setRent(int i) {

    }

    @Override
    public int getRent() {
        return 0;
    }
}
