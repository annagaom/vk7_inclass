package org.example.Dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vk7_inclass");
    private EntityManager em = emf.createEntityManager();

    // Add a student to the database
    public void saveStudent(Student student) {
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    }

    // Find all training sessions attended by a student id
    public void getTrainingSessionsByStudent(int studentId) {
       List<TrainingSession> trainingSessions =  em.createQuery(
                        "SELECT ts FROM TrainingSession ts " +
                                "JOIN ts.students s " +
                                "WHERE s.id = :studentId", TrainingSession.class)
                .setParameter("studentId", studentId)
                .getResultList();

       for (TrainingSession ts : trainingSessions) {
           System.out.println(ts);
       }
    }

    // List all students with a specific rank
    public List<Student> getStudentsByRank(String rank) {
        return em.createQuery(
                        "SELECT s FROM Student s " +
                                "WHERE s.rank = :rank", Student.class)
                .setParameter("rank", rank)
                .getResultList();
    }

    public void getstudentsJoinedLastSixMonths() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        // Calculate the date six months ago from today
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        // Ensure proper date type comparison
        query.select(root).where(cb.greaterThanOrEqualTo(root.get("joinDate"), java.sql.Date.valueOf(sixMonthsAgo)));
        List<Student> students = em.createQuery(query).getResultList();

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
