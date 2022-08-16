package com.bobhome.bobdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobhome.bobdeliver.dtos.OrderDTO;
import com.bobhome.bobdeliver.dtos.ProductDTO;
import com.bobhome.bobdeliver.entities.Order;
import com.bobhome.bobdeliver.entities.Product;
import com.bobhome.bobdeliver.enums.OrderStatus;
import com.bobhome.bobdeliver.repositories.OrderRepository;
import com.bobhome.bobdeliver.repositories.ProductRepository;
import com.bobhome.bobdeliver.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productRepository;

	//busca somente pedidos com status pendente, com seus produtos e ordenado por hora.
	@Transactional(readOnly = true)
	public List<OrderDTO> findOrdersWithProducts() {
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		Order entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity " + id + " not found"));
		return new OrderDTO(entity, entity.getProducts());
	}

	@Transactional
	public OrderDTO insert(OrderDTO orderDto) {
		Order entity = new Order();
		copyDTOtoEntity(orderDto, entity);
		entity = repository.save(entity);
		return new OrderDTO(entity);
		
	}

	private void copyDTOtoEntity(OrderDTO orderDto, Order entity) {
		entity.setAddress(orderDto.getAddress());
		entity.setLatitude(orderDto.getLatitude());
		entity.setLongitude(orderDto.getLongitude());
		entity.setMoment(Instant.now());
		entity.setStatus(OrderStatus.PENDING);
		
		entity.getProducts().clear();
		for(ProductDTO prodDto : orderDto.getProducts()) {
			Product product = productRepository.getById(prodDto.getId());
			entity.getProducts().add(product);
		}
		
	}
	
}
