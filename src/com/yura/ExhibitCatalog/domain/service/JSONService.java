package com.yura.demo.domain.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yura.demo.persistace.models.Exhibit;
import com.yura.demo.persistace.models.User;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONService {

    private static final String EXHIBITS_FILE = "exhibits.json";
    private static final String USERS_FILE = "users.json";
    private final Gson gson = new Gson();

    public List<Exhibit> loadExhibits() {
        try (FileReader reader = new FileReader(EXHIBITS_FILE)) {
            Type listType = new TypeToken<List<Exhibit>>() {
            }.getType();
            List<Exhibit> exhibits = gson.fromJson(reader, listType);
            return exhibits != null ? exhibits : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading exhibits: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveExhibits(List<Exhibit> exhibits) {
        try (FileWriter writer = new FileWriter(EXHIBITS_FILE)) {
            gson.toJson(exhibits, writer);
        } catch (IOException e) {
            System.err.println("Error saving exhibits: " + e.getMessage());
        }
    }

    public List<User> loadUsers() {
        try (FileReader reader = new FileReader(USERS_FILE)) {
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            List<User> users = gson.fromJson(reader, listType);
            return users != null ? users : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users) {
        try (FileWriter writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }
}
