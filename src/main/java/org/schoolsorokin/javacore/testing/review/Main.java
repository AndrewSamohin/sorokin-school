package org.schoolsorokin.javacore.testing.review;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        OrderRepository repository = new OrderRepository() {
            @Override
            public int saveOrder(Order order) {
                System.out.println(order.getProductName());
                return order.getId();
            }

            @Override
            public Optional<Order> getOrderById(int id) {
                return Optional.empty();
            }
        };
        // Создаём сервис
        OrderService service = new OrderService(repository);

        // Создаём заказ
        Order order = new Order(1, "Laptop", 2, 1000.0);

        // Обработка заказа
        String result = service.processOrder(order);
        System.out.println(result); // "Order processed successfully"

        // Подсчёт общей стоимости
        double total = service.calculateTotal(order.getId());
        System.out.println("Total price: " + total); // 2000.0
    }
}
