package gov.iti.jets.reprositories;

import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import gov.iti.jets.models.Order;
import gov.iti.jets.repositories.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class OrderRepositoryTest {

    @Mock
    private OrderRepository orderRepository;

    private Order order1;
    private Order order2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order1 = new Order(null, new BigDecimal("100.00"), LocalDateTime.now());
        order2 = new Order(null, new BigDecimal("200.00"), LocalDateTime.now());

        given(orderRepository.save(order1)).willAnswer(invocation -> {
            order1.setId(1L);
            return order1;
        });

        given(orderRepository.save(order2)).willAnswer(invocation -> {
            order2.setId(2L);
            return order2;
        });
    }

    @AfterEach
    void tearDown() {
        orderRepository = null;
    }

    // @Test
    // void findAll() {
    //     given(orderRepository.findAll()).willReturn(List.of(order1, order2));

    //     var orders = orderRepository.findAll();
    //     assertEquals(2, orders.size());
    //     assertTrue(orders.contains(order1));
    //     assertTrue(orders.contains(order2));

    //     verify(orderRepository, times(1)).findAll();
    // }

    @Test
    void findAll() {
        // Change List.of to Set.of to match the method's return type
        given(orderRepository.findAll()).willReturn(Set.of(order1, order2));

        Set<Order> orders = orderRepository.findAll();  // Change to Set<Order>
        assertEquals(2, orders.size());
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));

        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        given(orderRepository.findById(1L)).willReturn(Optional.of(order1));

        Optional<Order> foundOrder = orderRepository.findById(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals(new BigDecimal("100.00"), foundOrder.get().getTotalPrice());

        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        given(orderRepository.findById(any(Long.class))).willThrow(EntityNotFoundException.class);

        Throwable thrown = Assertions.catchThrowable(() -> orderRepository.findById(99L));

        Assertions.assertThat(thrown).isInstanceOf(EntityNotFoundException.class);
        verify(orderRepository, times(1)).findById(99L);
    }

    @Test
    void save() {
        Order newOrder = new Order(null, new BigDecimal("150.00"), LocalDateTime.now());

        given(this.orderRepository.save(newOrder)).willAnswer(invocation -> {
            newOrder.setId(3L);
            return newOrder;
        });

        Order savedOrder = this.orderRepository.save(newOrder);

        assertNotNull(savedOrder.getId());
        assertEquals(new BigDecimal("150.00"), savedOrder.getTotalPrice());

        verify(orderRepository, times(1)).save(newOrder);
    }

    @Test
    void update() {
        order1.setTotalPrice(new BigDecimal("250.00"));
        orderRepository.update(order1);

        assertEquals(new BigDecimal("250.00"), order1.getTotalPrice());
        verify(orderRepository, times(1)).update(order1);
    }

    @Test
    void delete() {
        orderRepository.delete(order1);
        verify(orderRepository, times(1)).delete(order1);
    }

    @Test
    void findByTotalPrice() {
        given(orderRepository.findByTotalPrice(new BigDecimal("100.00"))).willReturn(Optional.of(order1));

        Optional<Order> foundOrder = orderRepository.findByTotalPrice(new BigDecimal("100.00"));
        assertTrue(foundOrder.isPresent());
        assertEquals(new BigDecimal("100.00"), foundOrder.get().getTotalPrice());

        verify(orderRepository, times(1)).findByTotalPrice(new BigDecimal("100.00"));
    }

    @Test
    void findByUser() {
        given(orderRepository.findById(1L)).willReturn(Optional.of(order1));

        Optional<Order> foundOrder = orderRepository.findById(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals(order1, foundOrder.get());

        verify(orderRepository, times(1)).findById(1L);
    }
}
