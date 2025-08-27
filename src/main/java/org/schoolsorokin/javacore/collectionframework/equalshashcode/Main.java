package org.schoolsorokin.javacore.collectionframework.equalshashcode;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Product, String> products = new HashMap<>();
        Product product1 = new Product("Хлеб", 99.99, 2);
        Product product2 = new Product("Молоко", 199.99, 2);
        Product product3 = new Product("Сыр", 249.99, 3);
        Product product4 = new Product("Батон", 49.99, 1);
        Product product5 = new Product("Колбаса", 329.99, 1);

        products.put(product1, "Выпечка");
        products.put(product2, "Молочка");
        products.put(product3, "Молочка");
        products.put(product4, "Выпечка");
        products.put(product5, "Мясо");

        for (HashMap.Entry<Product, String> entry : products.entrySet()) {
            System.out.println(entry.getValue() + ": " + entry.getKey());
        }
    }
}
