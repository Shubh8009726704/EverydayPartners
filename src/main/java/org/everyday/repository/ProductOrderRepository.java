package org.everyday.repository;

import java.util.List;

import org.everyday.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

	List<ProductOrder> findByUserId(Integer userId);

}