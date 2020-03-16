package com.distance.city.util;

public class Constants {

    public final static  String URL = "jdbc:mysql://localhost:3306/city";

    public final static String USER = "root";

    public final static String PASSWORD = "root";

    public final static String INSERT_CITY = "insert into city (name,latitude,longitude) value (?,?,?)";

    public final static String SELECT_CITIES = "select * from city";

}
