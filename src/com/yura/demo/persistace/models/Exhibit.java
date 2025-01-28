package com.yura.demo.persistace.models;

public class Exhibit {

    private final int id;
    private final String name;
    private final String period;
    private final String material;
    private final String collection;
    private final String description;
    private final String location;

    public Exhibit(int id, String name, String period, String material, String collection,
        String description, String location) {
        this.id = id;
        this.name = name;
        this.period = period;
        this.material = material;
        this.collection = collection;
        this.description = description;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String border = "+------------------------------------------------------------+";
        return String.format(
            "%s\n| %-58s |\n| %-14s: %-40s |\n| %-14s: %-40s |\n| %-14s: %-40s |\n| %-14s: %-40s |\n| %-14s: %-40s |\n%s\n| %-58s |\n",
            border, "Експонат #" + id, "Назва", name, "Період", period, "Матеріал", material,
            "Колекція", collection, "Місцезнаходження", location, border, "Опис: " + description,
            border);
    }


}
