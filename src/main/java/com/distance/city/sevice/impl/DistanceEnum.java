package com.distance.city.sevice.impl;

import org.springframework.stereotype.Component;

public enum DistanceEnum implements Measures {

    KM {@Override public double calculateDistance(double distance) {return 1;}},

    MI {@Override public double calculateDistance(double distance) {return 2;}}

}
