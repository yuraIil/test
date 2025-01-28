package com.yura.demo.persistace.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yura.demo.persistace.models.Exhibit;
import com.yura.demo.persistace.models.User;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONRepository {

    private final String exhibitsFile = "exhibits.json";
    private final String usersFile = "users.json";

    private final Gson gson = new Gson();

    public List<Exhibit> loadExhibits() {
        try (Reader reader = new FileReader(exhibitsFile)) {
            TypeToken<List<Exhibit>> token = new TypeToken<List<Exhibit>>() {
            };
            return gson.fromJson(reader, token.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveExhibits(List<Exhibit> exhibits) {
        try (Writer writer = new FileWriter(exhibitsFile)) {
            gson.toJson(exhibits, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> loadUsers() {
        try (Reader reader = new FileReader(usersFile)) {
            TypeToken<List<User>> token = new TypeToken<List<User>>() {
            };
            return gson.fromJson(reader, token.getType());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveUsers(List<User> users) {
        try (Writer writer = new FileWriter(usersFile)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
