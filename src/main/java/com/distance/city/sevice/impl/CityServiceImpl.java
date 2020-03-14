package com.distance.city.sevice.impl;

import com.distance.city.dao.CityDAO;
import com.distance.city.dto.CityDTO;
import com.distance.city.jdbc.domain.City;
import com.distance.city.sevice.CityService;
import com.distance.city.sevice.impl.DistanceEnum;
import com.distance.city.sevice.impl.Measures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static org.apache.lucene.util.SloppyMath.haversinMeters;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private CityDTO cityDTO;

    @Autowired
    private Measures mea;

    public void insertCity(City city) {
        city.setId(null);
        cityDAO.save(city);
    }

    public List<City> findAll() {
        return cityDAO.findAll();
    }

    @Override
    public List<CityDTO> calculateDistanceCities (String measures) {
        List<City> citiesSource = findAll();
        //List<City> cities1 = new ArrayList<>();
        List<CityDTO> cities = new ArrayList<>();

        for (City city : citiesSource) {
            for (City city1 : citiesSource)  {
                if(!city.getName().equals(city1.getName())){
                    setCityDTO(city,city1,cities,measures);
                }
            }
        }
        return cities;
    }

    private void setCityDTO(City cityFrom, City cityTo, List<CityDTO> cities,String measures) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setCityFrom(cityFrom.getName());
            cityDTO.setCityTo(cityFrom.getName());
            cityDTO.setMeasures(measures);
            cityDTO.setDistance(distance(measures,cityFrom.getLatitude(),cityFrom.getLongitude(),cityTo.getLatitude(),cityTo.getLongitude()));
            cities.add(cityDTO);
    }

    private double distance (String measures,double latitudeCityFrom, double longitudeCityFrom, double latitudeCityTo, double longitudeCityTo) {
       DistanceEnum distanceEnum = DistanceEnum.valueOf(measures);
       return  distanceEnum.calculateDistance(mea.calculateDistance(haversinMeters(latitudeCityFrom, longitudeCityFrom, latitudeCityTo, longitudeCityTo)));
    }
}
