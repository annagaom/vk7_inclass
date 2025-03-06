package org.example.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ProgressReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version; // Optimistic locking field

    private Date reportDate;
    private String achievements;
    private String areasOfImprovement;
    private long student_id;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    public ProgressReport() {
    }

    public ProgressReport(Date reportDate, String achievements, String areasOfImprovement, int student_id) {
        this.reportDate = reportDate;
        this.achievements = achievements;
        this.areasOfImprovement = areasOfImprovement;
        this.student_id = student_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getAreasOfImprovement() {
        return areasOfImprovement;
    }

    public void setAreasOfImprovement(String areasOfImprovement) {
        this.areasOfImprovement = areasOfImprovement;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "ProgressReport{" + "id=" + id + ", reportDate=" + reportDate + ", achievements=" + achievements + ", areasOfImprovement=" + areasOfImprovement + ", version=" + version + '}';
    }
}
