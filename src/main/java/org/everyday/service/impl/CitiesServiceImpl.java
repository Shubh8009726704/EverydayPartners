package org.everyday.service.impl;

import java.util.List;

import org.everyday.model.Cities;
import org.everyday.repository.CitiesRepository;
import org.everyday.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CitiesServiceImpl implements CitiesService {

	@Autowired
	private CitiesRepository citiesRepository;

	@Override
	public Cities saveCities(Cities cities) {
		return citiesRepository.save(cities);
	}

	@Override
	public List<Cities> getAllCities() {
		return citiesRepository.findAll();
	}

	@Override
	public Boolean existCities(String name) {
		return citiesRepository.existsByName(name);
	}

	@Override
	public Boolean deleteCities(int id) {
		Cities cities = citiesRepository.findById(id).orElse(null);

		if (!ObjectUtils.isEmpty(cities)) {
			citiesRepository.delete(cities);
			return true;
		}
		return false;
	}

	@Override
	public Cities getCitiesById(int id) {
		Cities cities = citiesRepository.findById(id).orElse(null);
		return cities;
	}

	@Override
	public List<Cities> getAllActiveCities() {
		List<Cities> city = citiesRepository.findByIsActiveTrue();
		return city;
	}

	@Override
	public Page<Cities> getAllCitiesPagination(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return citiesRepository.findAll(pageable);
	}

}
