package com.plays.grocery.service;

import com.plays.grocery.model.Order;
import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<Order> getAll();

    Order getById(Long id);

    void delete(Long id);
}