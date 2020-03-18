package com.distance.city.sevice.impl;

import com.distance.city.dao.CityDAO;
import com.distance.city.dto.CityDTO;
import com.distance.city.jdbc.domain.City;
import com.distance.city.sevice.CityService;
import com.distance.city.sevice.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static org.apache.lucene.util.SloppyMath.haversinMeters;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public void insertCity(City city) {
        city.setId(null);
        cityDAO.save(city);
    }

    @Override
    public List<City> findAll() {
        List<City> cities =cityDAO.findAll();
        if(cities == null || cities.isEmpty()) {
            throw new ObjectNotFoundException("Nenhuma cidade encontrada");
        }
        return cities;
    }

    @Override
    public void deleteCity (Integer id) {
        if ( id == null ) {
            throw new ObjectNotFoundException("Nenhuma cidade encontrada");
        }
        cityDAO.deleteById(id);
    }

    @Override
    public List<CityDTO> calculateDistanceCities (String measures) {
        List<City> citiesSource = findAll();
        List<CityDTO> cities = new ArrayList<>();
        comparatorCities(citiesSource,cities,measures);
        return cities;
    }

    private void comparatorCities (List<City> citiesSource, List<CityDTO> cities, String measures) {
        for (City cityFrom : citiesSource) {
            for (City cityTo : citiesSource)  {
                if(!cityFrom.getName().equals(cityTo.getName())){
                    createCityDTO(cityFrom,cityTo,cities,measures);
                }
            }
        }
    }

    private void createCityDTO(City cityFrom, City cityTo, List<CityDTO> cities,String measures) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setCityFrom(cityFrom.getName());
            cityDTO.setCityTo(cityTo.getName());
            cityDTO.setMeasures(measures);
            cityDTO.setDistance(distance(measures,cityFrom.getLatitude(),cityFrom.getLongitude(),cityTo.getLatitude(),cityTo.getLongitude()));
            cities.add(cityDTO);
    }

    public double distance (String measures,double latitudeCityFrom, double longitudeCityFrom, double latitudeCityTo, double longitudeCityTo) {
        DistanceEnum distanceEnum;
        try {
            distanceEnum = DistanceEnum.valueOf(measures.toUpperCase());
            return  distanceEnum.calculateDistance(haversinMeters(latitudeCityFrom, longitudeCityFrom, latitudeCityTo, longitudeCityTo));
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException("Parâmetro Inválido : " + measures, illegalArgumentException);
        }
    }
}