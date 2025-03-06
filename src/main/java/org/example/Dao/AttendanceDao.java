package org.example.Dao;

import jakarta.persistence.*;
import org.example.Entities.Attendance;

public class AttendanceDao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("vk7_inclass");
    private EntityManager em = emf.createEntityManager();


}
