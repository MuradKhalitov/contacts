package org.example;

import org.example.exception.InvalidChoiceCodeException;
import org.example.exception.UserNotFoundException;
import org.example.exception.UserParseException;
import org.example.exception.WriteDataException;
import org.example.model.ChoiceType;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Scanner;

@Component
public class ApplicationInterface {

    private final UserService userService;

    private final Scanner scanner;

    public ApplicationInterface(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть все контакты");
            System.out.println("2. Добавить контакт");
            System.out.println("3. Удалить контакт");
            System.out.println("4. Выгрузить контакты в файл");
            System.out.println("0. Выход");

            ChoiceType choice;
            try {
                choice = ChoiceType.from(scanner.nextLine());
            } catch (InvalidChoiceCodeException ex) {
                System.out.println(ex.getMessage());
                continue;
            }

            switch (choice) {
                case ALL_CONTACTS -> showContacts();
                case ADD_CONTACT -> addContact();
                case DELETE_CONTACT -> deleteContact();
                case STORE_CONTACTS_TO_FILE -> storeToFile();
                case EXIT -> {
                    System.out.println("Выход из программы...");
                    isRunning = false;
                }
                default -> System.out.println("Некорректный ввод!");
            }
        }
    }

    private void showContacts() {
        Collection<User> users = userService.getAll();

        if (users.isEmpty()) {
            System.out.println("Контакты отсутствуют!");
        } else {
            System.out.println("Список контактов:");
            for (User user : users) {
                System.out.println(user.getFullName() + " | " + user.getPhoneNumber() + " | " + user.getEmail());
            }
        }
    }

    private void addContact() {
        System.out.println("Введите данные контакта в формате: ФИО;телефон;email");

        String inputData = scanner.nextLine();
        try {
            userService.save(User.parse(inputData));
            System.out.println("Контакт успешно добавлен!");
        } catch (UserParseException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void deleteContact() {
        System.out.println("Введите email контакта для удаления");
        String email = scanner.nextLine();
        try {
            userService.delete(email);
            System.out.println("Контакт успешно удален!");
        } catch (UserNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void storeToFile() {
        try {
            userService.storeToFile();
            System.out.println("Данные успешно сохранены в файл!");
        } catch (WriteDataException ex) {
            System.out.println("Ошибка при сохранении данных в файл!");
        }
    }
}
