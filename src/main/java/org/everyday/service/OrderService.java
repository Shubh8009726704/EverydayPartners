package org.everyday.service;

import java.util.List;

import org.everyday.model.OrderRequest;
import org.everyday.model.ProductOrder;


public interface OrderService {

	public void saveOrder(Integer userid,OrderRequest orderRequest);
	
	public List<ProductOrder> getOrdersByUser(Integer userId);
	
	public Boolean updateOrderStatus(Integer id,String status);
	
	public List<ProductOrder> getAllOrders();


}
