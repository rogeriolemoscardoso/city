package com.distance.city.dao;

import com.distance.city.jdbc.ConnectionFactory;
import com.distance.city.jdbc.domain.City;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.distance.city.util.Constants.*;

@Component
public class CityDAO {

    private Connection connection;

    public CityDAO() throws SQLException {
       this.connection = new ConnectionFactory().getConnection();
    }

    public void save (City city) {
        String sql = INSERT_CITY;;

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1,city.getName());
            pst.setDouble(2,city.getLatitude());
            pst.setDouble(3,city.getLongitude());
            pst.execute();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<City> findAll () {
        String sql = SELECT_CITIES;
        ArrayList<City> cities = new ArrayList<>();

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setLatitude(rs.getFloat("latitude"));
                city.setLongitude(rs.getFloat("longitude"));
                cities.add(city);
            }
           pst.close();
            return cities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Integer id) {
        String sql = DELETE_CITIES;

        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setLong(1,id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
