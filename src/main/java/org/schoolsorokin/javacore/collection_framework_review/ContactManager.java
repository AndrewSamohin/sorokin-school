package org.schoolsorokin.javacore.collection_framework_review;

import java.util.*;

public class ContactManager {

    private final List<Contact> contactList = new ArrayList<>();
    private final Set<Contact> contactSet = new HashSet<>();
    private final Map<String, List<Contact>> contactMap = new HashMap<>();

    //добавление контакта
    public boolean addContact(Contact contact) {
        //Проверка на дубликат
        if (contactSet.contains(contact)) return false;
        //Добавление контакта в коллекции
        contactSet.add(contact);
        contactList.add(contact);
        //Добавление в Map по группе
        String group = contact.getGroup();
        if (!contactMap.containsKey(group)) {
            contactMap.put(group, new  ArrayList<>());//Если группы нет, то создаем
        }
        contactMap.get(group).add(contact); //Добавление контакта в список группы
        return true;
    }

    //Удаление контакта
    public boolean removeContact(Contact contact) {
        if (!contactSet.contains(contact)) return false;
        contactSet.remove(contact);
        contactList.remove(contact);

        //Берем список контактов по ключу
        List<Contact> groupContacts = contactMap.get(contact.getGroup());
        //Проверяем существует ли такая группа
        if (groupContacts != null) {
            groupContacts.remove(contact);//Удаляем контакт
            if (groupContacts.isEmpty()) {
                contactMap.remove(contact.getGroup());//Если в группе нет больше контактов, тоже удаляем
            }
        }
        return true;
    }

    //Выводим все контакты
    public void showAllContacts() {
        if (contactMap.isEmpty()) {
            System.out.println("Контактов нет.");
            return;
        }
        //Set для уже выведенных групп
        Set<String> withdrawnGroups = new HashSet<>();
        Iterator<Contact> iterator = contactList.iterator();

        //Проходим по контактам
        while (iterator.hasNext()) {
            Contact contact = iterator.next();
            String group = contact.getGroup();

            //Если группа не найдена
            if (!withdrawnGroups.contains(group)) {
                System.out.println("Контакты в группе " + group + ": ");

                for (Contact con :  contactList) {
                    if (con.getGroup().equals(group)) {
                        System.out.println(con);
                    }
                }
                System.out.println();
                withdrawnGroups.add(group);
            }
        }
    }


    //Поиск контакта по имени
    public List<Contact> findContact(String name) {
        //Создаем список, чтоб в случае совпадения вывести все контакты с данным именем
        List<Contact> contactsFound = new ArrayList<>();
        //Проверка на null
        if (name == null || name.isBlank()) {
            return contactsFound;
        }

        //Проходим по списку и ищем совпадение
        for (Contact contact : contactList) {
            if (contact.getName().equalsIgnoreCase(name)) {
                contactsFound.add(contact);
            }
        }
        return contactsFound;
    }

    //Просмотр контактов по группе
    public void showContactsByGroup(String group) {
        List<Contact> groupContacts = contactMap.get(group);

        // Проверяем, что группа существует и в ней есть контакты
        if (groupContacts != null && !groupContacts.isEmpty()) {
            System.out.println("Контакты в группе " + group + ": ");
            for (Contact contact : groupContacts) {
                System.out.println(contact);
            }
        } else {
            System.out.println("Группа не найдена.");
        }
    }
}
