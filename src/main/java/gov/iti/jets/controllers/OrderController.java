package gov.iti.jets.controllers;

import java.util.List;

import gov.iti.jets.models.Order;
import gov.iti.jets.services.OrderService;
import gov.iti.jets.system.exceptions.ObjectNotFoundException;

public class OrderController {
    private final OrderService orderService;

    // Constructor to inject the OrderService
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // 1. Get order by ID
    public Order getOrderById(Long orderId) {
        return orderService.findOrderById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order", orderId));
    }


    // 2. Get orders by User ID
    public List<Order> getOrderByUserId(Long userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        if (orders.isEmpty()) {
            throw new ObjectNotFoundException("Orders for User", userId);
        }
        return orders;
    }

    // 3. Create a new order
    public void createOrder(Order order) {
        orderService.createOrder(order);
    }

    // 4. Update order
    public void updateOrder(Order order) {
        orderService.updateOrder(order);
    }

    // 5. Delete order by ID
    public void deleteOrder(Long orderId) {
        orderService.deleteOrder(orderId);
    }
}
