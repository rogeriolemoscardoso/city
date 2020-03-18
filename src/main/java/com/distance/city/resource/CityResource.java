package com.distance.city.resource;

import com.distance.city.dto.CityDTO;
import com.distance.city.jdbc.domain.City;
import com.distance.city.sevice.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityResource {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "/distance", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CityDTO>> findDistanceCity (@RequestParam("measures") String measures) {
       return ResponseEntity.ok().body(cityService.calculateDistanceCities(measures));
    }

    @GetMapping
    public ResponseEntity<List<City>> findAll () {
        List<City> cities = cityService.findAll();
        return ResponseEntity.ok().body(cities);
    }

    @PostMapping
    public ResponseEntity<Void> insertCity (@RequestBody City city) {
        cityService.insertCity(city);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteCity (@PathVariable Integer id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

}
