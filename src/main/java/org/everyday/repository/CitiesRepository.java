package org.everyday.repository;

import java.util.List;

import org.everyday.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CitiesRepository extends JpaRepository<Cities, Integer> {

	public Boolean existsByName(String name);

	public List<Cities> findByIsActiveTrue();

}
