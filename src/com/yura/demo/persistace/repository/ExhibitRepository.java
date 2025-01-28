package com.yura.demo.persistace.repository;

import com.yura.demo.persistace.models.Exhibit;
import java.util.ArrayList;
import java.util.List;

public class ExhibitRepository {

    private final List<Exhibit> exhibits = new ArrayList<>();

    public void save(Exhibit exhibit) {
        exhibits.add(exhibit);
    }

    public List<Exhibit> findAll() {
        return exhibits;
    }

    public Exhibit findById(int id) {
        for (Exhibit exhibit : exhibits) {
            if (exhibit.getId() == id) {
                return exhibit;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        exhibits.removeIf(exhibit -> exhibit.getId() == id);
    }
}