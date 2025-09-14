package org.schoolsorokin.javacore.testing.review;

import java.util.Optional;

public interface OrderRepository {
    int saveOrder(Order order); //Сохраняет заказ и возвращает id
    Optional<Order> getOrderById(int id); //Возвращает заказ по id
}