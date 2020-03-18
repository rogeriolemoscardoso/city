package com.distance.city.util;

public class Constants {

    public final static  String URL = "jdbc:mysql://bd:3306/city?createDatabaseIfNotExist=true";

    public final static String USER = "root";

    public final static String PASSWORD = "root";

    public final static String INSERT_CITY = "insert into city (name,latitude,longitude) value (?,?,?)";

    public final static String SELECT_CITIES = "select * from city";

    public final static String DELETE_CITIES = "delete from city where id = ? " ;
}
