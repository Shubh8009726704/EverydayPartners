package org.everyday.controller;

import java.security.Principal;
import java.util.List;

import org.everyday.model.Category;
import org.everyday.model.OrderRequest;
import org.everyday.model.ProductOrder;
import org.everyday.model.UserDtls;
import org.everyday.service.CategoryService;
import org.everyday.service.OrderService;
import org.everyday.service.UserService;
import org.everyday.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@ModelAttribute
	public void getUserDetails(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			UserDtls userDtls = userService.getUserByEmail(email);
			m.addAttribute("user", userDtls);
		}
	}
	
	
	@GetMapping("/")
	public String home() {
		return "user/home";
	}
	
	private UserDtls getLoggedInUserDetails(Principal p) {
		String email = p.getName();
		UserDtls userDtls = userService.getUserByEmail(email);
		return userDtls;
	}

	
	@GetMapping("/orders")
	public String orderPage(Principal p,Model m) {
		UserDtls user = getLoggedInUserDetails(p);
		//List<Cart> carts = cartService.getCartsByUser(user.getId());
		//m.addAttribute("carts", carts);
		//if (carts.size() > 0) {
			//Double orderPrice = carts.get(carts.size() - 1).getTotalOrderPrice();
			//Double totalOrderPrice = carts.get(carts.size() - 1).getTotalOrderPrice() + 250 + 100;
			//m.addAttribute("orderPrice", orderPrice);
			//m.addAttribute("totalOrderPrice", totalOrderPrice);
		
		return "/user/order";
	}
	@PostMapping("/save-order")
	public String saveOrder(@ModelAttribute OrderRequest request,Principal p) {
		 //System.out.println(request);
		UserDtls user = getLoggedInUserDetails(p);
		orderService.saveOrder(user.getId(), request);

		return "/user/success";
	}
	

	@GetMapping("/success")
	public String loadSuccess() {
		return "/user/success";
	}
	
	@GetMapping("/user-orders")
	public String myOrder(Model m, Principal p) {
		UserDtls loginUser = getLoggedInUserDetails(p);
		List<ProductOrder> orders = orderService.getOrdersByUser(loginUser.getId());
		m.addAttribute("orders", orders);
		return "/user/my_orders";
	}
	
	@GetMapping("/update-status")
	public String updateOrderStatus(@RequestParam Integer id, @RequestParam Integer st, HttpSession session) {

		OrderStatus[] values = OrderStatus.values();
		String status = null;

		for (OrderStatus orderSt : values) {
			if (orderSt.getId().equals(st)) {
				status = orderSt.getName();
			}
		}

		Boolean updateOrder = orderService.updateOrderStatus(id, status);

		if (updateOrder) {
			session.setAttribute("succMsg", "Status Updated");
		} else {
			session.setAttribute("errorMsg", "status not updated");
		}
		return "redirect:/user/user-orders";
	}



}
