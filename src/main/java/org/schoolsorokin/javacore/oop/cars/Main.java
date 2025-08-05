package org.schoolsorokin.javacore.oop.cars;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.setBrand("Toyota");
        car.setModel("Avensis");
        car.setYear(1780);

        System.out.println("Марка: " + car.getBrand()
                + "\nМодель: " + car.getModel()
                + "\nГод: " + car.getYear());
    }
}

class Car {
    private String brand;
    private String model;
    private int year;
    //Getter для brand
    public String getBrand() {
        return brand;
    }
    //Setter для brand
    public void setBrand(String brand) {
        this.brand = brand;
    }
    //Getter для model
    public String getModel() {
        return model;
    }
    //Setter для model
    public void setModel(String model) {
        this.model = model;
    }
    //Getter для year
    public int getYear() {
        return year;
    }
    //Setter для year
    public void setYear(int year) {
        if (year < 1886) {
            System.out.println("Первая машина была создана");
            return;
        }
        this.year = year;
    }
}