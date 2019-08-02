package com.crmpetproject.crmpetproject.repository.impl;

import com.crmpetproject.crmpetproject.models.ActiveClient;
import com.crmpetproject.crmpetproject.repository.interfaces.ActiveClientRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ActiveClientRepositoryImpl implements ActiveClientRepositoryCustom {

    private final EntityManager entityManager;

    @Autowired
    public ActiveClientRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ActiveClient> getStudentsWithTodayNotificationsEnabled() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime tomorrow = LocalDate.now().plusDays(1).atStartOfDay();
        return entityManager.createQuery("SELECT s FROM Student s WHERE (((s.notifyEmail = TRUE)" +
                " OR (s.notifySMS = TRUE) OR (s.notifyVK = TRUE))" +
                " AND (s.nextPaymentDate >= :today AND s.nextPaymentDate < :tomorrow))")
                .setParameter("today", today)
                .setParameter("tomorrow", tomorrow)
                .getResultList();
    }

    @Override
    public void detach(ActiveClient student) {
        entityManager.detach(student);
    }
}
