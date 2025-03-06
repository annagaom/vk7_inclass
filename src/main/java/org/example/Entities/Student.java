package org.example.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String rank;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Convert(converter = AikidoRankConverter.class)
    private AikidoRank aikidoRank;


    @Column(name = "joinDate")
    private LocalDate joinDate; // Changed to LocalDate for consistency

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<ProgressReport> progressReports = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "attendance",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "trainingSession_id")
    )
    private List<TrainingSession> trainingSessions = new ArrayList<>();

    @Transient
    private String formattedJoinDate;  // Transient field for formatted date

    public Student(String nini, String mail, String red, Date joinDate) {}

    public Student(String name, String email, String rank, LocalDate joinDate) {
        this.name = name;
        this.email = email;
        this.rank = rank;
        this.joinDate = joinDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public List<ProgressReport> getProgressReports() {
        return progressReports;
    }

    public void setProgressReports(List<ProgressReport> progressReports) {
        this.progressReports = progressReports;
    }

    // Automatically set createdAt and updatedAt when the entity is created
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;  // Set updatedAt to createdAt initially
    }

    // Automatically set updatedAt before the entity is updated
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // This method will be called after the entity is loaded from the database
    @PostLoad
    protected void onLoad() {
        // Format the joinDate to a readable string when the entity is loaded
        if (joinDate != null) {
            this.formattedJoinDate = joinDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

    // Getter for the formattedJoinDate field
    public String getFormattedJoinDate() {
        return formattedJoinDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rank='" + rank + '\'' +
                ", joinDate=" + joinDate +
                ", formattedJoinDate='" + formattedJoinDate + '\'' +
                '}';
    }
}
