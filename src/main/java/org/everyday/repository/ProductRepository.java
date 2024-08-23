package org.everyday.repository;

import java.util.List;

import org.everyday.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByIsActiveTrue();

	List<Product> findByCategory(String category);

}
