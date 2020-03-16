package com.distance.city;

import com.distance.city.dao.CityDAO;
import com.distance.city.dto.CityDTO;
import com.distance.city.jdbc.domain.City;
import com.distance.city.sevice.exceptions.ObjectNotFoundException;
import com.distance.city.sevice.impl.CityServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityApplicationTests {

	@InjectMocks
	private CityServiceImpl cityServiceImp;

	@InjectMocks
	private CityDAO cityDao;

	@Test
	void contextLoads() {
	}

	@Test
	public void testDistance () {
		City cityFrom = new City();
		City cityTo = new City();

		cityFrom.setName("São Paulo");
		cityFrom.setLatitude(-23.5489);
		cityFrom.setLongitude(-46.6388);

		cityTo.setName("Rio de Janeiro");
		cityTo.setLatitude(-22.9035);
		cityTo.setLongitude(-43.2096);

		double cityDTOSKm = cityServiceImp.distance("KM",cityFrom.getLatitude(),cityFrom.getLongitude(),cityTo.getLatitude(),cityTo.getLongitude());
		double cityDTOSMi = cityServiceImp.distance("MI",cityFrom.getLatitude(),cityFrom.getLongitude(),cityTo.getLatitude(),cityTo.getLongitude());

		assertEquals(357.6696153671614,cityDTOSKm);
		assertEquals(222.29311085591138,cityDTOSMi);
	}

	@Test
	public void testDistanceException () {
		City cityFrom = new City();
		City cityTo = new City();

		cityFrom.setName("São Paulo");
		cityFrom.setLatitude(-23.5489);
		cityFrom.setLongitude(-46.6388);

		cityTo.setName("Rio de Janeiro");
		cityTo.setLatitude(-22.9035);
		cityTo.setLongitude(-43.2096);

		Assertions.assertThrows(IllegalArgumentException.class,() -> cityServiceImp.distance("Metro",cityFrom.getLatitude(),cityFrom.getLongitude(),cityTo.getLatitude(),cityTo.getLongitude()));
	}

	@Test
	public void testcalculateDistanceCities () {
		cityServiceImp = new CityServiceImpl(cityDao);
		List<CityDTO> result = cityServiceImp.calculateDistanceCities("KM");
		assertNotNull(result);
		assertNotNull(result.get(0).getDistance());
	}

	@Test
	public void testcalculateDistanceCitiesException () {
		cityServiceImp = new CityServiceImpl(cityDao);
		Assertions.assertThrows(ObjectNotFoundException.class, () -> cityServiceImp.calculateDistanceCities("KM"));
	}
}
