package org.example.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TrainingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;
    private String location;
    private String duration; // Stored as "2 hours" or "90 minutes"

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToMany(mappedBy = "trainingSessions")
    private List<Student> students = new ArrayList<>();

    @Transient
    private String formattedDate;

    @Transient
    private String durationInHours;

    public TrainingSession() {}

    public TrainingSession(Date date, String location, String duration, Instructor instructor) {
        this.date = date;
        this.location = location;
        this.duration = duration;
        this.instructor = instructor;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        System.out.println("TrainingSession created at: " + createdAt);
    }

    @PostLoad
    protected void onLoad() {
        // Convert Date to formatted string
        if (date != null) {
            this.formattedDate = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        // Convert duration (assuming stored as "X minutes") to "Xh Ym"
        if (duration != null && duration.contains("minutes")) {
            try {
                int minutes = Integer.parseInt(duration.replace("minutes", "").trim());
                int hours = minutes / 60;
                int remainingMinutes = minutes % 60;
                this.durationInHours = hours + "h " + (remainingMinutes > 0 ? remainingMinutes + "m" : "");
            } catch (NumberFormatException e) {
                this.durationInHours = duration; // Fallback to raw value if parsing fails
            }
        } else {
            this.durationInHours = duration;
        }

        System.out.println("TrainingSession loaded: Date=" + formattedDate + ", Duration=" + durationInHours);
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public String getDurationInHours() {
        return durationInHours;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "id=" + id +
                ", date=" + date +
                ", formattedDate=" + formattedDate +
                ", location='" + location + '\'' +
                ", duration='" + duration + '\'' +
                ", durationInHours='" + durationInHours + '\'' +
                '}';
    }
}
