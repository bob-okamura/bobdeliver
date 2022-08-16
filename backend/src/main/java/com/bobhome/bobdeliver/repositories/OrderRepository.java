package com.bobhome.bobdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bobhome.bobdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
