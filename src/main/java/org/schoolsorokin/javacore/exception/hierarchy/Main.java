package org.schoolsorokin.javacore.exception.hierarchy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        readFile();
        createArray();
    }
    //Чтение файла
    private static String readFile() {
        File file = new File("text.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void createArray() {
        int[] array = {1, 2, 3, 4, 5};
        try {
            System.out.println(array[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: обращение к несуществующему элементу массива");
        }
    }

}
