package com.plays.grocery.service;

import com.plays.grocery.model.GroceryItem;
import com.plays.grocery.model.Order;
import com.plays.grocery.model.Customer;
import com.plays.grocery.repository.GroceryItemRepository;
import com.plays.grocery.repository.OrderRepository;
import com.plays.grocery.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GroceryItemRepository groceryItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository,
                            GroceryItemRepository groceryItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public Order create(Order order) {

        // Fetch real customer from DB
        Long customerId = order.getCustomer().getId();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Fetch real grocery items from DB
        List<GroceryItem> items = order.getItems().stream()
                .map(item -> groceryItemRepository.findById(item.getId())
                        .orElseThrow(() -> new RuntimeException("Item not found")))
                .toList();

        // Calculate total price
        double totalPrice = items.stream()
                .mapToDouble(GroceryItem::getPrice)
                .sum();

        order.setCustomer(customer);
        order.setItems(items);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}