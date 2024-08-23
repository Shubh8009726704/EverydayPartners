package org.everyday.service;

import java.util.List;

import org.everyday.model.Cities;
import org.springframework.data.domain.Page;


public interface CitiesService {

	public Cities saveCities(Cities Cities);

	public Boolean existCities(String name);

	public List<Cities> getAllCities();

	
	 public Boolean deleteCities(int id);
	 
	  public Cities getCitiesById(int id);
	  
	  public List<Cities> getAllActiveCities();
	  
	  public Page<Cities> getAllCitiesPagination(Integer pageNo,Integer
	  pageSize);
	 

}
