package org.schoolsorokin.javacore.multithreading.completablefuture;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

public class ChainFuture {
    public static BigInteger getFactorial(int n) {
        if (n <= 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(n).multiply(getFactorial(n - 1));
    }

    static CompletableFuture<Integer> factorial1 = CompletableFuture.supplyAsync(() -> {
        int number = 5;
        System.out.println("Получение 1-го факториала из числа: " + number);
        if (number <= 0) {
            throw new RuntimeException("Факториал не может быть отрицательным!");
        }
        BigInteger factorial = getFactorial(number);
        return factorial;
    }).thenCompose(amount ->
            CompletableFuture.supplyAsync(() -> {
                System.out.println("Умножение факториала на 2.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int x = 2;
                return x * amount.intValue();
            })
    ).exceptionally(ex -> {
        System.out.println("Ошибка: " + ex.getMessage());
        return 0;
    });

    static CompletableFuture<Integer> factorial2 = CompletableFuture.supplyAsync(() -> {
        int number = 8;
        System.out.println("Получение 2-го факториала из числа: " + number);
        if (number <= 0) {
            throw new RuntimeException("Факториал не может быть отрицательным!");
        }
        BigInteger factorial = getFactorial(number);
        return factorial;
    }).thenCompose(amount ->
            CompletableFuture.supplyAsync(() -> {
                System.out.println("Умножение факториала на 2.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int x = 2;
                return x * amount.intValue();
            })
    ).exceptionally(ex -> {
        System.out.println("Ошибка: " + ex.getMessage());
        return 0;
    });

    public static void main(String[] args) {
        CompletableFuture<Integer> future2 = factorial2;
        CompletableFuture<Integer> future1 = factorial1;

        CompletableFuture<Integer> resultFuture = future1.thenCombine(future2, (x , y) -> {
            System.out.println("Суммируем факториалы");
            return x + y;
        }).exceptionally(ex -> {
            System.out.println("Произошла ошибка: " + ex.getMessage());
            return 0;
        });

        resultFuture.thenAccept(System.out::println);

        future1.join();
        future2.join();
    }
}
