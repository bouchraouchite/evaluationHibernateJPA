package org.example.dao;

package dao;

import entities.Projet;
import utils.PersistenceManager;
import javax.persistence.EntityManager;
import java.util.List;

public class ProjetDao {

    public void persisterProjet(Projet projet) {
        EntityManager em = PersistenceManager.getEntityManager();
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
