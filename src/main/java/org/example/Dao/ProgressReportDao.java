package org.example.Dao;

import jakarta.persistence.*;
import org.example.Entities.ProgressReport;
import org.example.Entities.Student;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ProgressReportDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vk7_inclass");
    private EntityManager em = emf.createEntityManager();

    public ProgressReport getProgressReportById(long id) {
        em.getTransaction().begin();
        ProgressReport progressReport = em.find(ProgressReport.class, id);
        em.getTransaction().commit();
        return progressReport;
    }

    public void getStudentsWithProgressReportsLastThreeMonths() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        List <Student> students = em.createQuery(
                        "SELECT pr.student FROM ProgressReport pr " +
                                "WHERE pr.reportDate >= :threeMonthsAgo", Student.class)
                .setParameter("threeMonthsAgo", java.sql.Date.valueOf(threeMonthsAgo))
                .getResultList();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public ProgressReport updateProgressReport(ProgressReport updatedReport) {

        try {
            em.getTransaction().begin();
            ProgressReport existingReport = em.find(ProgressReport.class, updatedReport.getId());

            if (existingReport == null) {
                throw new RuntimeException("Raporttia ei löydy!");
            }

            if (existingReport.getVersion() != updatedReport.getVersion()) {
                throw new OptimisticLockException("Tietoa on muokattu toisessa istunnossa. Päivitä ja yritä uudelleen.");
            }
            existingReport.setReportDate(updatedReport.getReportDate());
            existingReport.setAchievements(updatedReport.getAchievements());
            existingReport.setAreasOfImprovement(updatedReport.getAreasOfImprovement());

            em.merge(existingReport);
            em.getTransaction().commit();
            return existingReport;

        } catch (OptimisticLockException e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Konflikti! Toinen käyttäjä on jo päivittänyt tietoja.", e);
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Päivitys epäonnistui.", e);
        } finally {
            em.close();
        }
    }
}
