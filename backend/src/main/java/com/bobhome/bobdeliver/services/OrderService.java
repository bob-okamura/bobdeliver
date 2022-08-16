package com.bobhome.bobdeliver.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobhome.bobdeliver.dtos.OrderDTO;
import com.bobhome.bobdeliver.entities.Order;
import com.bobhome.bobdeliver.repositories.OrderRepository;
import com.bobhome.bobdeliver.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findAll();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		Order entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		return new OrderDTO(entity, entity.getProducts());
	}
	
}
