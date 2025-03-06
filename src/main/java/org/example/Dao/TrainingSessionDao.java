package org.example.Dao;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.Entities.TrainingSession;

import java.util.List;

public class TrainingSessionDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vk7_inclass");
    private EntityManager em = emf.createEntityManager();

    public void getTainingSessionsByLocation(String location) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TrainingSession> cq = cb.createQuery(TrainingSession.class);
        Root<TrainingSession> root = cq.from(TrainingSession.class);
        cq.select(root).where(cb.equal(root.get("location"), location));
        List<TrainingSession> trainingSessions = em.createQuery(cq).getResultList();

        for (TrainingSession ts : trainingSessions) {
            System.out.println(ts);
        }
    }

}
