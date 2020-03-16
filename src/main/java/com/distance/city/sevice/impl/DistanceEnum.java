package com.distance.city.sevice.impl;

public enum DistanceEnum implements Measures {

    KM {@Override public double calculateDistance(double distance) {return distance / 1000;}},

    MI {@Override public double calculateDistance(double distance) {return distance / 1609;}}

}
