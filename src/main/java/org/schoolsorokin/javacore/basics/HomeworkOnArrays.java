package org.schoolsorokin.javacore.basics;

import java.util.Random;

public class HomeworkOnArrays {
    public void arrays() {
        Random rand = new Random();

        //Создание массива
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10);
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }

        //Определение минимального и максимального элемента
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println();
        System.out.println("Минимальное значение в массиве: " + min);
        System.out.println("Максимальное значение в массиве: " + max);

        //Сортировка в массиве
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int num =  arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = num;
                }
            }
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        //Создание двумерного массива
        int rows = 2;
        int columns = 5;

        int[][] arr2 = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr2[i][j] = rand.nextInt(10);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr2[i][j] + " ");
            }
            System.out.println();
        }
    }
}






