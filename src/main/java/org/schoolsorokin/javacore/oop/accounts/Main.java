package org.schoolsorokin.javacore.oop.accounts;

public class Main {
    public static void main(String[] args) {

        Printable saving = new SavingAccount(700.0);
        Printable credit = new  CreditAccount(300.0);

        ((Account) saving).deposit(100.0); //Баланс 800
        ((Account) credit).deposit(200.0); //Баланс 500

        ((Account) saving).withdraw(200.0); //Баланс 600
        ((Account) credit).withdraw(550.0); //Баланс -50

        ((Account) saving).withdraw(1000.0); //Пытаемся уйти в минус
        ((Account) credit).withdraw(1000.0); //Пытаемся выйти за границы лимита

        saving.printInfo();
        credit.printInfo();
    }
}
