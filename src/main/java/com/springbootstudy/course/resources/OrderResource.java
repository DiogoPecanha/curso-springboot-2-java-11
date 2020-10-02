package com.springbootstudy.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootstudy.course.entities.Order;
import com.springbootstudy.course.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		
		List<Order> Orders = service.findAll();		
		return ResponseEntity.ok(Orders);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order order = service.findById(id);
		
		if (order == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(order);
		}
	}
}
