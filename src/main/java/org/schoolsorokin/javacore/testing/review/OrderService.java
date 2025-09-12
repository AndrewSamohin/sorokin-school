package org.schoolsorokin.javacore.testing.review;

public class OrderService {
    private final OrderRepository orderRepository;

    // Внедрение зависимости через конструктор
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        int id = orderRepository.saveOrder(order);
        if (id > 0) {
            return "Order processed successfully";
        } else {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        return orderRepository.getOrderById(id)
                .map(Order::getTotalPrice)
                .orElse(0.0);
    }
}
