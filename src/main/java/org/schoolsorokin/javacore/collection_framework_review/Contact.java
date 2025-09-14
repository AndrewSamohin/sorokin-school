package org.schoolsorokin.javacore.collection_framework_review;

import java.util.Objects;

public class Contact {
    private final String name;
    private final String phone;
    private final String email;
    private final String group;

    //Конструктор
    public Contact(String name, String phone, String email, String group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }
    //Геттеры
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
    //Переопределение equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        //Контакты будут считаться одинаковыми, если совпадает имя и телефон
        return Objects.equals(this.name, contact.name) &&
                Objects.equals(this.phone, contact.phone);
    }

    @Override
    public int hashCode() {
        //Ключевые поля - имя и телефон
        return Objects.hash(name, phone);
    }

    @Override
    public String toString() {
        return name + " ( " +  phone + " )" + ", email: " + email;
    }
}
