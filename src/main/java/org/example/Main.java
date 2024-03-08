package org.example;

import org.example.dao.ProjetDao;
import org.example.dao.TacheDao;
import org.example.dao.UtilisateurDao;
import org.example.entities.Projet;
import org.example.entities.Tache;
import org.example.entities.Utilisateur;
import org.example.utils.PersistenceManager;


public class Main {
    public static void main(String[] args) {
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        ProjetDao projetDao = new ProjetDao();
        TacheDao tacheDao = new TacheDao();

 
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("bouchra");
        utilisateur.setEmail("bouchra.ouchite@e-polytechnique.ma");
        utilisateurDao.persisterUtilisateur(utilisateur);

     
        Projet projet = new Projet();
        projet.setTitre(" Projet");
        projet.setDescription(" premier projet.");
        projet.setUtilisateur(utilisateur);
        projetDao.persisterProjet(projet);

        Tache tache = new Tache();
        tache.setTitre("tâche");
        tache.setDescription(" ma première tâche.");
        tache.setProjet(projet);
        tacheDao.ajouterTache(tache);

       
        System.out.println("Projets trouvés : ");
        for (Object p : projetDao.rechercher()) {
            System.out.println(p.getTitre() + " - " + p.getDescription());
        }

        
        tacheDao.supprimerTachesParProjet(projet.getId());

       
        utilisateurDao.supprimerUtilisateur(utilisateur.getId());

      
        PersistenceManager.closeEntityManagerFactory();
    }
}
