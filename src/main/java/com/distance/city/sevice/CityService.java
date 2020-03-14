package com.distance.city.sevice;

import com.distance.city.dto.CityDTO;
import com.distance.city.jdbc.domain.City;
import java.util.List;


public interface CityService {

    void insertCity(City city);

    List<City> findAll();

    List<CityDTO> calculateDistanceCities (String measures);
}
