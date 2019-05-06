package com.be.ac.ulb.g05.Model;

public class Address {

    private final String name;
    private final double lat;
    private final double lng;

    public Address(String name, double lat, double lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return name;
    }

}
