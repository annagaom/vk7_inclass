package org.example.Entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String specialization;
    private int experienceYear;

    @OneToMany(mappedBy = "instructor")
    private List<TrainingSession> trainingSessions = new ArrayList<>();

    public Instructor() {
    }

    public Instructor(String name, String specialization, int experienceYear) {
        this.name = name;
        this.specialization = specialization;
        this.experienceYear = experienceYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int experienceYear) {
        this.experienceYear = experienceYear;
    }

    @Override
    public String toString() {
        return "Instructor{" + "id=" + id + ", name=" + name + ", specialization=" + specialization + ", experienceYear=" + experienceYear + '}';
    }
}
