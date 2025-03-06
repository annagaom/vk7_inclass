package org.example.Entities;

import jakarta.persistence.*;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String status;
   private String Notes;

    @Convert(converter = AttendanceStatusConverter.class)
    private AttendanceStatus attendanceStatus;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "trainingSession_id")
    private TrainingSession trainingSession;

    public Attendance() {
    }

    public Attendance(String status) {
        this.status = status;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance{" + "id=" + id + ", status=" + status + '}';
    }

}
