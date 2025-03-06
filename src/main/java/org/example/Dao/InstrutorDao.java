package org.example.Dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Entities.Instructor;

import java.time.Year;
import java.util.List;

public class InstrutorDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vk7_inclass");
    private EntityManager em = emf.createEntityManager();

    public void getInstrutorBySpecialization(String specialization) {
        em.getTransaction().begin();
        List<Instructor> instrutors = em.createQuery(
                        "SELECT i FROM Instructor i " +
                                "WHERE i.specialization = :specialization", Instructor.class)
                .setParameter("specialization", specialization)
                .getResultList();
        em.getTransaction().commit();

        for (Instructor instrutor : instrutors) {
            System.out.println(instrutor);
        }
    }

    public void getInstructorsWithExperienceMoreThan(int years) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Instructor> query = cb.createQuery(Instructor.class);
        Root<Instructor> root = query.from(Instructor.class);

        query.select(root).where(cb.greaterThanOrEqualTo(root.get("experienceYear"), years));
        List<Instructor> instrutors = em.createQuery(query).getResultList();

        for (Instructor instrutor : instrutors) {
            System.out.println(instrutor);
        }
    }
}
