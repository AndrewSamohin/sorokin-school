package org.schoolsorokin.javacore.basics;

public class VariablesAndDataTypes {
    public void variables() {
        //Инициализируем переменные
        int numberOfDays = 365;
        double bodyTemp = 36.6;
        short daysInWeek = 7;
        long secondsInYear = 31536000;
        byte hoursInDay = 24;
        boolean condition = true;
        char letter = 'A';
        //Вывод переменных в консоль
        System.out.println("Количество дней в году: " + numberOfDays);
        System.out.println("Температура тела: " + bodyTemp);
        System.out.println("Количество дней в неделе: " + daysInWeek);
        System.out.println("Количество секунд в году: " + secondsInYear);
        System.out.println("Количество часов в дне: " + hoursInDay);
        System.out.println("2 + 2 = 4? Ответ: " + condition);
        System.out.println("Первая буква алфавита: " + letter);
        //Присваивание значение long в int
        int newValueInt = (int) secondsInYear;
        System.out.println("Проверка int: " + newValueInt);
        //Вывод последовательных символов
        System.out.println("Первая буква: " + letter);
        System.out.println("Вторая буква: " + (char) (letter + 1));
        System.out.println("Третья буква: " + (char) (letter + 2));
        System.out.println("Четвертая буква: " + (char) (letter + 3));
    }
}
