package fr.sdv.b32223;

import fr.sdv.b32223.entities.Livre;

import javax.persistence.*;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("demo");
        EntityManager em = entityManagerFactory.createEntityManager();


        // Affichage
        Livre liv = em.find(Livre.class,2);
        if (liv != null) {
            System.out.println(liv.toString());
        }

        em.getTransaction().begin();

        /*
        //Ajout
        Livre livAjout = new Livre();
        livAjout.setAuteur("Kevin Aman");
        livAjout.setTitre("Les frasques de Kevin");
        em.persist(livAjout);
         */

        //Modifier
        Livre livModif = em.find(Livre.class, 5);
        System.out.println(livModif.toString());
        livModif.setTitre("Du plaisir dans la cuisine");
        livModif.setAuteur("1001 recettes de Cuisine");


        //Extraire en fonction du titre


        //Extraire en fonction de l'auteur


        //Supprimer


        //Tout afficher

        em.getTransaction().commit();




        //System.out.println(em);
        em.close();
    }
}