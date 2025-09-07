package org.schoolsorokin.javacore.streamAPI.review;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Создаем продукты
        List<Product> products = Arrays.asList(
                new Product(1L, "Настольная игра", "Игрушки",
                        BigDecimal.valueOf(500)),
                new Product(2L, "Велосипед", "Детские товары",
                        BigDecimal.valueOf(11500)),
                new Product(3L, "Кукла", "Игрушки",
                        BigDecimal.valueOf(1700)),
                new Product(4L, "Робот", "Игрушки",
                        BigDecimal.valueOf(2300)),
                new Product(5L, "Самокат", "Детские товары",
                        BigDecimal.valueOf(7300)),
                new Product(6L, "Ролики", "Детские товары",
                        BigDecimal.valueOf(4900)),
                new Product(7L, "Преступление и наказание", "Книги",
                        BigDecimal.valueOf(319)),
                new Product(8L, "Капитанская дочка", "Книги",
                        BigDecimal.valueOf(289)),
                new Product(9L, "Скотный двор", "Книги",
                        BigDecimal.valueOf(199))
        );

        //Создаем заказы
        //Заказ №1
        Set<Order> ordersForCustomer1 = new HashSet<>(Arrays.asList(
                new Order(1L,
                        LocalDate.of(2021, 2, 20),
                        LocalDate.of(2021, 2, 25), "Доставлен",
                        new HashSet<>(
                                Arrays.asList(
                                        products.get(0), products.get(5), products.get(2)
                                ))),
                new Order(2L,
                        LocalDate.of(2021, 2, 10),
                        LocalDate.of(2021, 2, 15), "Доставлен",
                        new HashSet<>(
                                Arrays.asList(
                                        products.get(2), products.get(6)
                                ))),
                new Order(3L,
                        LocalDate.of(2021, 3, 14),
                        LocalDate.of(2021, 3, 16), "В пути",
                        new HashSet<>(
                                Arrays.asList(
                                        products.get(1), products.get(7), products.get(5)
                                ))),
                new Order(4L,
                        LocalDate.of(2021, 3, 21),
                        LocalDate.of(2021, 3, 22), "В пути",
                        new HashSet<>(
                                Arrays.asList(
                                        products.get(4)
                                ))),
                new Order(5L,
                        LocalDate.of(2021, 3, 25),
                        LocalDate.of(2021, 3, 25), "В обработке",
                        new HashSet<>(
                                Arrays.asList(
                                        products.get(8), products.get(4), products.get(6)
                                )))
        ));

        //Заказ №2
        Set<Order> ordersForCustomer2 = new HashSet<>(Arrays.asList(
                new Order(6L,
                        LocalDate.of(2021, 2, 11),
                        LocalDate.of(2021, 2, 15), "Доставлен",
                        new HashSet<>(
                                Arrays.asList(
                                        products.get(0), products.get(2), products.get(7), products.get(5)
                                ))),
                new Order(7L,
                        LocalDate.of(2021, 2, 14),
                        LocalDate.of(2021, 2, 16), "В пути",
                        new HashSet<>(
                                Arrays.asList(
                                        products.get(1), products.get(4)
                                ))),
                new Order(8L,
                        LocalDate.of(2021, 3, 1),
                        LocalDate.of(2021, 3, 4), "В пути",
                        new HashSet<>(
                                Arrays.asList(
                                    products.get(5), products.get(6), products.get(8)
                        ))),
                new Order(9L,
                        LocalDate.of(2021, 3, 14),
                        LocalDate.of(2021, 3, 15), "В обработке",
                        new HashSet<>(
                                Arrays.asList(
                                    products.get(5), products.get(6), products.get(7)
                        ))),
                new Order(10L,
                        LocalDate.of(2021, 3, 11),
                        LocalDate.of(2021, 3, 12), "Отменен",
                        new HashSet<>(
                                Arrays.asList(
                                     products.get(3)
                        )))
        ));
        //Создаем покупателей
        List<Customer> customers = Arrays.asList(
            new Customer(1L, "Клиент 1", 1L, new HashSet<>(ordersForCustomer1)),
            new Customer(2L, "Клиент 2", 1L, new HashSet<>(ordersForCustomer1)),
            new Customer(3L, "Клиент 3", 2L, new HashSet<>(ordersForCustomer2)),
            new Customer(4L, "Клиент 4", 2L, new HashSet<>(ordersForCustomer2)),
            new Customer(5L, "Клиент 5", 2L, new HashSet<>(ordersForCustomer1))
        );

        //Задание 1
        System.out.println("Задание 1");
        List<Product> listBooks = products.stream()
                .filter(category -> category.getCategory().equals("Книги")) //Только книги
                .filter(price ->  price.getPrice().compareTo(new BigDecimal("100")) > 0) //Книги должны быть больше 100
                .collect(Collectors.toList());

        listBooks.forEach(System.out::println);

        //Задание 2
        System.out.println("\nЗадание 2");
        List<Product> listChildrenProducts = products.stream()
                .filter(category -> category.getCategory().equals("Детские товары"))
                .collect(Collectors.toList());

        listChildrenProducts.forEach(System.out::println);

        //Задание 3
        System.out.println("\nЗадание 3");
        BigDecimal discountedToys = products.stream()
                .filter(product -> product.getCategory().equals("Игрушки"))
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Сумма цен всех продуктов \"Игрушки\": " +  discountedToys);

        //Задание 4
        System.out.println("\nЗадание 4");
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);

        List<Product> productsClients = customers.stream()
                .filter(customer -> customer.getLevel() == 2) //Клиенты только второго уровня
                .flatMap(customer -> customer.getOrders().stream()) //Берем заказы клиентов
                .filter(order -> !order.getOrderDate().isBefore(startDate)
                    && !order.getOrderDate().isAfter(endDate))
                .flatMap(order -> order.getProducts().stream()) // Берем продукты
                .distinct() //Убираем дубликаты
                .collect(Collectors.toList());

        productsClients.forEach(System.out::println);

        //Задание 5
        System.out.println("\nЗадание 5");
        List<Product> cheapestBooks = products.stream()
                .filter(product -> product.getCategory().equals("Книги"))
                .sorted(Comparator.comparing(Product::getPrice)) //Сортируем по цене
                .limit(2)
                .collect(Collectors.toList());

        System.out.println("Две самые дешевые книги: ");
        cheapestBooks.forEach(System.out::println);

        //Задание 6
        System.out.println("\nЗадание 6");
        List<Order> threeLatestOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream()) //Получаем поток заказов
                .distinct()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("3 последних заказа: ");
        threeLatestOrders.forEach(System.out::println);

        //Задание 7
        System.out.println("\nЗадание 7");
        LocalDate march = LocalDate.of(2021, 3, 15);

        List<Product> ordersInMarch = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isEqual(march))
                .distinct()
                .peek(order -> System.out.println("ID заказа: " + order.getId()))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Товары из заказов сделанных 15 марта: ");
        ordersInMarch.forEach(System.out::println);

        //Задание 8
        System.out.println("\nЗадание 8");
        LocalDate startDateFebruary = LocalDate.of(2021, 2, 1);
        LocalDate endDateFebruary = LocalDate.of(2021, 3, 1);

        BigDecimal sumOrdersInFebruary = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> !order.getOrderDate().isBefore(startDate)
                        && !order.getOrderDate().isAfter(endDate))
                .map(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)) //Сумма продуктов заказа
                .reduce(BigDecimal.ZERO, BigDecimal::add); //Складываем все заказы

        System.out.println("Общая сумма заказов в феврале: " + sumOrdersInFebruary);

        //Задание 9
        System.out.println("\nЗадание 9");

        OptionalDouble averagePayment = customers.stream()
                .flatMap(customer -> customer.getOrders().stream()) //Все заказы
                .filter(order -> order.getOrderDate().isEqual(LocalDate.of(2021, 3, 14)))
                .mapToDouble(order -> order.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .doubleValue()) //Сумму переводим в double (Возможна потеря точности)
                .average();

        System.out.println("Средний платеж по заказам 14 марта: " +  averagePayment.orElse(0));

        //Задание 10
        System.out.println("\nЗадание 10");

        DoubleSummaryStatistics statistics = products.stream()
                .filter(product -> product.getCategory().equals("Книги"))
                .mapToDouble(product -> product.getPrice().doubleValue())
                .summaryStatistics();

        System.out.println("Количество книг: " + statistics.getCount());
        System.out.println("Сумма цен книг: " + statistics.getSum());
        System.out.println("Средняя цена книг: " +  statistics.getAverage());
        System.out.println("Минимальная цена книги: " + statistics.getMin());
        System.out.println("Максимальная цена книги: " + statistics.getMax());

        //Задание 11
        System.out.println("\nЗадание 11");
        Map<Long, Integer> orderProductCount = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .collect(Collectors.groupingBy(
                        Order::getId, //Ключ - id заказа
                        Collectors.summingInt(order -> order.getProducts().size()) //Значение - кол-во товаров
                ));

        orderProductCount.forEach((id, count) -> System.out.println(id + ": " + count));

        //Задание 12
        System.out.println("\nЗадание 12");

        Map<Customer, List<Order>> customerOrders = customers.stream()
                .collect(Collectors.toMap(
                        customer -> customer,
                        customer -> new ArrayList<>(customer.getOrders())
                ));

        customerOrders.forEach((id, orders) -> System.out.println(id + ": " + orders));

        //Задание 13
        System.out.println("\nЗадание 13");

        Map<Order, Double> totalAmountOfOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.getProducts().stream()
                                .map(Product::getPrice)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .doubleValue()
                ));

        totalAmountOfOrders.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Order::getId))) //Сортировка заказов по порядку
                .forEach(entry -> System.out.println(entry.getKey().getId() + ": " + entry.getValue()));

        //Задание 14
        System.out.println("\nЗадание 14");

        Map<String, List<String>> categoriesOfProductNames = customers.stream()
                .flatMap(customer -> customer.getOrders().stream()) //Все заказы всех покупателей
                .flatMap(customer -> customer.getProducts().stream()) //Все продукты
                .distinct()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList()) //Зачение - список названий товаров
                ));

        categoriesOfProductNames.forEach((category, names) ->
                System.out.println(category + ": " + names));

        //Задание 15
        System.out.println("\nЗадание 15");

        Map<String, Product> mostExpensiveProduct = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)),//Ищем макс. продукт по цене в каждой категории
                                Optional::get//Достаем продукт
                        )
                ));

        mostExpensiveProduct.forEach((category, product) ->
                System.out.println("Самые дорогие продукты в категориях: \n" + category + ": " + product));
    }
}