package com.yura.demo.domain.service;

import com.yura.demo.persistace.models.Exhibit;
import com.yura.demo.persistace.repository.JSONRepository;
import java.util.ArrayList;
import java.util.List;

public class ExhibitService {

    private final JSONRepository repository;

    public ExhibitService(JSONRepository repository) {
        this.repository = repository;
    }

    public void addExhibit(Exhibit exhibit) {
        if (exhibit.getName() == null || exhibit.getName().isEmpty()) {
            throw new IllegalArgumentException("Назва експоната не може бути порожньою");
        }
        List<Exhibit> exhibits = repository.loadExhibits();
        if (exhibits == null) {
            exhibits = new ArrayList<>(); // Переконайтеся, що список не null
        }
        exhibits.add(exhibit);
        repository.saveExhibits(exhibits);
    }

    public List<Exhibit> getAllExhibits() {
        List<Exhibit> exhibits = repository.loadExhibits();
        if (exhibits == null) {
            exhibits = new ArrayList<>();  // Ініціалізація порожнім списком, якщо результат null
        }
        return exhibits;
    }

    public Exhibit getExhibitById(int id) {
        List<Exhibit> exhibits = repository.loadExhibits();
        for (Exhibit exhibit : exhibits) {
            if (exhibit.getId() == id) {
                return exhibit;
            }
        }
        return null;
    }

    public void deleteExhibit(int id) {
        List<Exhibit> exhibits = repository.loadExhibits();
        exhibits.removeIf(exhibit -> exhibit.getId() == id);
        repository.saveExhibits(exhibits);
    }
}
