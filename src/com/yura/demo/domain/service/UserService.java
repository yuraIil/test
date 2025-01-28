package com.yura.demo.domain.service;

import com.yura.demo.persistace.models.User;
import com.yura.demo.persistace.repository.JSONRepository;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final JSONRepository repository;

    public UserService(JSONRepository repository) {
        this.repository = repository;
    }

    public boolean isUsernameTaken(String username) {
        List<User> users = repository.loadUsers();
        if (users == null) {
            users = new ArrayList<>();  // Якщо список користувачів пустий (null), ініціалізуємо його
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;  // Якщо ім'я вже зайняте, повертаємо true
            }
        }
        return false;  // Якщо ім'я не зайняте, повертаємо false
    }

    public boolean register(String username, String password, boolean isAdmin, String adminKey) {
        if (isUsernameTaken(username)) {
            return false;  // Якщо ім'я користувача вже існує, реєстрація не вдалася
        }

        // Якщо користувач хоче бути адміністратором, перевіряємо adminKey (якщо потрібно)
        if (isAdmin && !adminKey.equals("0000")) {
            return false;  // Якщо неправильний ключ, реєстрація не вдалася
        }

        List<User> users = repository.loadUsers();
        if (users == null) {
            users = new ArrayList<>();  // Ініціалізуємо список користувачів, якщо він null
        }

        // Додаємо нового користувача в список і зберігаємо його
        users.add(new User(username, password, isAdmin));
        repository.saveUsers(users);
        return true;  // Успішна реєстрація
    }

    public User login(String username, String password) {
        List<User> users = repository.loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;  // Повертаємо користувача, якщо ім'я та пароль співпадають
            }
        }
        return null;  // Якщо не знайдено відповідного користувача, повертаємо null
    }
}
