package org.schoolsorokin.javacore.exception.wordcount;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CountWordsFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу: ");
        String filePath = sc.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            if (filePath.trim().isEmpty()) {
                throw new InputMismatchException("Путь не может быть пустым!");
            }

            int wordsCount = 0;

            String file = reader.readLine();

            if (file != null && !file.isEmpty()) {
                String[] words = file.trim().split(" ");
                wordsCount = words.length;
                System.out.println("Первая строка содержит " + wordsCount + " слов.");
            }
            else {
                System.out.println("Строка отсутствует или пуста.");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл не найден!");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " +  e.getMessage());
        }
        catch (InputMismatchException e) {
            System.out.println("Ошибка: Некорректный путь.");
        }
        System.out.println("Спасибо за использование!");
    }
}
