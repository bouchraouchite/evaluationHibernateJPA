package org.example.dao;

import org.example.utils.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class ProjetDao<Projet> {

    public void persisterProjet(Projet projet) {
        EntityManager em=PersistenceManager.getEntityManager();
        EntityTransaction transaction =null;
        try{
        transaction= em.getTransaction();
        transaction.begin();
        em.persist(projet);
        transaction.commit();
    }catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to save employee: " + ex);
            throw new RuntimeException("Failed to save employee", ex);
        }
    }

    public List<Projet> rechercher() {
        EntityManager em = PersistenceManager.getEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            String hql="SELECT p FROM Projet p ORDER BY p.id ASC";
             Query query=em.createQuery(hql,ProjetDao.class);
            List<Projet> result =query.getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

}
