package com.distance.city.sevice.impl;

import com.distance.city.dao.CityDAO;
import com.distance.city.dto.CityDTO;
import com.distance.city.jdbc.domain.City;
import com.distance.city.sevice.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import static org.apache.lucene.util.SloppyMath.haversinMeters;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    @Override
    public void insertCity(City city) {
        city.setId(null);
        cityDAO.save(city);
    }

    @Override
    public List<City> findAll() {
        return cityDAO.findAll();
    }

    @Override
    public List<CityDTO> calculateDistanceCities (String measures) {
        List<City> citiesSource = findAll();
        List<CityDTO> cities = new ArrayList<>();

        for (City cityFrom : citiesSource) {
            for (City cityTo : citiesSource)  {
                if(!cityFrom.getName().equals(cityTo.getName())){
                    createCityDTO(cityFrom,cityTo,cities,measures);
                }
            }
        }
        return cities;
    }

    private void createCityDTO(City cityFrom, City cityTo, List<CityDTO> cities,String measures) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setCityFrom(cityFrom.getName());
            cityDTO.setCityTo(cityFrom.getName());
            cityDTO.setMeasures(measures);
            cityDTO.setDistance(distance(measures,cityFrom.getLatitude(),cityFrom.getLongitude(),cityTo.getLatitude(),cityTo.getLongitude()));
            cities.add(cityDTO);
    }

    private double distance (String measures,double latitudeCityFrom, double longitudeCityFrom, double latitudeCityTo, double longitudeCityTo) {
        DistanceEnum distanceEnum;
        try {
            distanceEnum = DistanceEnum.valueOf(measures.toUpperCase());
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("Parâmetro Inválido : " + measures, illegalArgumentException);
        }
       return  distanceEnum.calculateDistance(haversinMeters(latitudeCityFrom, longitudeCityFrom, latitudeCityTo, longitudeCityTo));
    }
}
