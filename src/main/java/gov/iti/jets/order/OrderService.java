package gov.iti.jets.order;

import gov.iti.jets.system.exceptions.ObjectNotFoundException;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;

    // Constructor to inject OrderRepository
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // 1. Find order by ID
    public Optional<Order> findOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // 2. Find order by user ID
    public Optional<Order> findOrderByUserId(Long userId) {
        return orderRepository.findByUser(userId);
    }

    // 3. Create a new order
    public void createOrder(Order order) {
        orderRepository.save(order);
    }

    // 4. Update order
    public void updateOrder(Order order) {
        Optional<Order> existingOrder = orderRepository.findById(order.getId());
        if (existingOrder.isPresent()) {
            orderRepository.save(order);
        } else {
            throw new ObjectNotFoundException("Order", order.getId());
        }
    }

    // 5. Delete order by ID
    public void deleteOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
        } else {
            throw new ObjectNotFoundException("Order", orderId);
        }
    }
}
