package org.example;
package main;
import dao.ProjetDao;
import dao.TacheDao;
import dao.UtilisateurDao;
import entities.Projet;
import entities.Tache;
import entities.Utilisateur;
import utils.PersistenceManager;

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
        for (Projet p : projetDao.rechercher()) {
            System.out.println(p.getTitre() + " - " + p.getDescription());
        }

        
        tacheDao.supprimerTachesParProjet(projet.getId());

       
        utilisateurDao.supprimerUtilisateur(utilisateur.getId());

      
        PersistenceManager.closeEntityManagerFactory();
    }
}
