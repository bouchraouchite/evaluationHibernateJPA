package org.example.dao;




import org.example.entities.Utilisateur;
import org.example.utils.PersistenceManager;

import javax.persistence.EntityManager;
import java.util.List;


public class UtilisateurDao {

    public void persisterUtilisateur(Utilisateur utilisateur) {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        em.persist(utilisateur);
        em.getTransaction().commit();
        em.close();
    }

    public List<Utilisateur> rechercher() {
        EntityManager em = PersistenceManager.getEntityManager();
        List<Utilisateur> result = em.createQuery("SELECT u FROM Utilisateur u ORDER BY u.id ASC", Utilisateur.class).getResultList();
        em.close();
        return result;
    }

    public void supprimerUtilisateur(Long id) {
        EntityManager em = PersistenceManager.getEntityManager();
        em.getTransaction().begin();
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        if (utilisateur != null) {
            em.remove(utilisateur);
        }
        em.getTransaction().commit();
        em.close();
    }
}
