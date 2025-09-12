package org.schoolsorokin.javacore.testing.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }

    @Test
    void testOrderProcessedSuccessfully() {
        Order order = new Order(1, "PC", 2, 100000.0);
        when(orderRepository.saveOrder(order)).thenReturn(1);

        String result = orderService.processOrder(order);

        assertEquals("Order processed successfully", result);
        verify(orderRepository, times(1)).saveOrder(order);
    }

    @Test
    void testOrderProcessingFailed() {
        Order order = new Order(3, "Tablet", 1, 600.0);
        when(orderRepository.saveOrder(order)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.processOrder(order);
        });
        assertEquals("Database error", exception.getMessage());

        verify(orderRepository, times(1)).saveOrder(order);
    }
    //Тесты для calculateTotal
    @Test
    void testSuccessfulCostCalculation() {
        int orderId = 1;
        Order order = new Order(orderId, "Book", 3, 100.0);

        when(orderRepository.getOrderById(orderId)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(orderId);

        assertEquals(300.0, result);
        verify(orderRepository, times(1)).getOrderById(orderId);
    }

    @Test
    void testOrderNotFound() {
        int orderId = 2;

        when(orderRepository.getOrderById(orderId)).thenReturn(Optional.empty());

        double result = orderService.calculateTotal(orderId);

        assertEquals(0.0, result);
        verify(orderRepository, times(1)).getOrderById(orderId);
    }

    @Test
    void testCorrectCalculationWithZeroQuantityOrPrice() {
        int orderId = 1;
        Order order = new Order(orderId, "Book", 0, 100.0);
        when(orderRepository.getOrderById(orderId)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(orderId);

        assertEquals(0.0, result);
        verify(orderRepository, times(1)).getOrderById(orderId);
    }
}