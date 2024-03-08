package org.example.dao;


import org.example.entities.Projet;
import org.example.entities.Tache;
import org.example.utils.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.List;

public class TacheDao {

    public void ajouterTache(Tache tache) {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(tache);
        em.getTransaction().commit();
        em.close();
    }

    public List<Tache> rechercher() {
        EntityManager em = PersistenceManager.getEntityManager();
        List<Tache> result = em.createQuery("SELECT t FROM Tache t ORDER BY t.id ASC", Tache.class).getResultList();
        em.close();
        return result;
    }

    public void supprimerTachesParProjet(Long projetId) {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        Projet projet = em.find(Projet.class, projetId);
        if (projet != null) {
            projet.getTaches().forEach(tache -> em.remove(tache));
        }
        em.getTransaction().commit();
        em.close();
    }
}
