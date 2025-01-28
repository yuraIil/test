package com.yura.demo;

import com.yura.demo.domain.service.ExhibitService;
import com.yura.demo.domain.service.UserService;
import com.yura.demo.persistace.models.Exhibit;
import com.yura.demo.persistace.models.User;
import com.yura.demo.persistace.repository.JSONRepository;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final JSONRepository jsonRepository = new JSONRepository();
    private static final UserService userService = new UserService(jsonRepository);
    private static final ExhibitService exhibitService = new ExhibitService(jsonRepository);

    public static void main(String[] args) {
        User loggedInUser = null;

        while (true) {
            if (loggedInUser == null) {
                System.out.println("1. Зареєструватися");
                System.out.println("2. Увійти");
                System.out.println("3. Вийти");

                System.out.print("Виберіть опцію: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> register();
                    case 2 -> loggedInUser = authenticate();
                    case 3 -> {
                        System.out.println("До побачення!");
                        return;
                    }
                    default -> System.out.println("Невірний вибір!");
                }
            } else {
                if (loggedInUser.isAdmin()) {
                    adminMenu();
                } else {
                    userMenu();
                }
            }
        }
    }

    private static void register() {
        System.out.println("Реєстрація користувача");
        System.out.print("Введіть ім'я користувача: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty() || userService.isUsernameTaken(username)) {
            System.out.println("Некоректне або зайняте ім'я користувача!");
            return;
        }

        System.out.print("Введіть пароль (мінімум 6 символів): ");
        String password = scanner.nextLine().trim();

        if (password.length() < 6) {
            System.out.println("Пароль занадто короткий!");
            return;
        }

        System.out.print("Ви бажаєте бути адміном? (так/ні): ");
        String isAdminInput = scanner.nextLine().trim().toLowerCase();
        boolean isAdmin = isAdminInput.equals("так");

        String adminKey = "";
        if (isAdmin) {
            System.out.print("Введіть ключ адміністратора: ");
            adminKey = scanner.nextLine().trim();
        }

        boolean success = userService.register(username, password, isAdmin, adminKey);
        if (success) {
            System.out.println("Реєстрація успішна!");
        } else {
            System.out.println("Не вдалося зареєструвати користувача!");
        }
    }

    private static User authenticate() {
        System.out.println("Вхід в систему");
        System.out.print("Введіть ім'я користувача: ");
        String username = scanner.nextLine().trim();

        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine().trim();

        User user = userService.login(username, password);
        if (user != null) {
            System.out.println("Вхід успішний!");
            return user;
        } else {
            System.out.println("Невірне ім'я користувача або пароль!");
            return null;
        }
    }

    private static void userMenu() {
        System.out.println("1. Переглянути експонати");
        System.out.println("2. Вийти");

        System.out.print("Виберіть опцію: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> showExhibits();
            case 2 -> System.out.println("Ви вийшли.");
            default -> System.out.println("Невірний вибір!");
        }
    }

    private static void adminMenu() {
        System.out.println("1. Переглянути експонати");
        System.out.println("2. Додати новий експонат");
        System.out.println("3. Вийти");

        System.out.print("Виберіть опцію: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> showExhibits();
            case 2 -> addExhibit();
            case 3 -> System.out.println("Ви вийшли.");
            default -> System.out.println("Невірний вибір!");
        }
    }

    private static void showExhibits() {
        System.out.println("Список експонатів:");
        for (Exhibit exhibit : exhibitService.getAllExhibits()) {
            System.out.println(exhibit);
        }
    }

    private static void addExhibit() {
        System.out.println("Додати новий експонат:");
        System.out.print("Назва експоната: ");
        String name = scanner.nextLine().trim();

        System.out.print("Період: ");
        String period = scanner.nextLine().trim();

        System.out.print("Матеріал: ");
        String material = scanner.nextLine().trim();

        System.out.print("Колекція: ");
        String collection = scanner.nextLine().trim();

        System.out.print("Місцезнаходження: ");
        String location = scanner.nextLine().trim();

        System.out.print("Опис: ");
        String description = scanner.nextLine().trim();

        List<Exhibit> exhibits = exhibitService.getAllExhibits();
        int newId =
            exhibits.isEmpty() ? 1 : exhibits.size() + 1;  // Якщо список порожній, починаємо з 1
        Exhibit exhibit = new Exhibit(newId, name, period, material, collection, location,
            description);
        exhibitService.addExhibit(exhibit);
        System.out.println("Експонат додано.");
    }
}
