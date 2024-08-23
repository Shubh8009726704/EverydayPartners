package org.everyday.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.everyday.model.OrderAddress;
import org.everyday.model.OrderRequest;
import org.everyday.model.ProductOrder;
import org.everyday.repository.ProductOrderRepository;
import org.everyday.repository.ProductRepository;
import org.everyday.service.OrderService;
import org.everyday.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private ProductOrderRepository orderRepository;
	
	
	@Override
	public void saveOrder(Integer userid, OrderRequest orderRequest) {
		
		ProductOrder order = new ProductOrder();

		order.setOrderId(UUID.randomUUID().toString());
		order.setOrderDate(LocalDate.now());

		//order.setProduct(order.getProduct());
		//order.setPrice(order.getProduct().getDiscountPrice());

		order.setUser(order.getUser());

		order.setStatus(OrderStatus.IN_PROGRESS.getName());
		order.setPaymentType(orderRequest.getPaymentType());

		OrderAddress address = new OrderAddress();
		address.setFirstName(orderRequest.getFirstName());
		address.setLastName(orderRequest.getLastName());
		address.setEmail(orderRequest.getEmail());
		address.setMobileNo(orderRequest.getMobileNo());
		address.setAddress(orderRequest.getAddress());
		address.setCity(orderRequest.getCity());
		address.setState(orderRequest.getState());
		address.setPincode(orderRequest.getPincode());

		order.setOrderAddress(address);

		orderRepository.save(order);

	}
	@Override
	public List<ProductOrder> getOrdersByUser(Integer userId) {
		List<ProductOrder> orders = orderRepository.findByUserId(userId);
		return orders;
	}
	
	@Override
	public Boolean updateOrderStatus(Integer id, String status) {
		Optional<ProductOrder> findById = orderRepository.findById(id);
		if (findById.isPresent()) {
			ProductOrder productOrder = findById.get();
			productOrder.setStatus(status);
			orderRepository.save(productOrder);
			return true;
		}
		return false;
	}

	@Override
	public List<ProductOrder> getAllOrders() {
		return orderRepository.findAll();
	}

}
