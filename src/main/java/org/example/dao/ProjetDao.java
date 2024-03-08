package org.example.dao;

import org.example.utils.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.List;

public class ProjetDao<Projet> {

    public void persisterProjet(Projet projet) {
        EntityManager em = projet.getEntityManager();
        em.getTransaction().begin();
        em.persist(projet);
        em.getTransaction().commit();
        em.close();
    }

    public List<Projet> rechercher() {
        EntityManager em = PersistenceManager.getEntityManager();
        List<Projet> result = em.createQuery("SELECT p FROM Projet p ORDER BY p.id ASC", Projet.class).getResultList();
        em.close();
        return result;
    }

}
