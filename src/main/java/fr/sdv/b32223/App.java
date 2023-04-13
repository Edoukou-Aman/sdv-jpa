package fr.sdv.b32223;

import fr.sdv.b32223.entities.Livre;

import javax.persistence.*;
import java.util.List;

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
        //Ajout
        Livre livAjout = new Livre();
        livAjout.setAuteur("Kevin Aman");
        livAjout.setTitre("Les frasques de Kevin");
        em.persist(livAjout);


        //Modifier
        Livre livModif = em.find(Livre.class, 5);
        System.out.println(livModif.toString());
        livModif.setTitre("Du plaisir dans la cuisine");
        livModif.setAuteur("1001 recettes de Cuisine");
        em.persist(livModif);


       //Extraire en fonction de l'auteur
        List<Livre> livreAut = null;
        livreAut = em
                .createQuery("select a from Livre a where a.auteur =:auteur",Livre.class)
                .setParameter("auteur", "Léon Tolstoï")
                .getResultList();
        for(Livre livre:livreAut) {
            System.out.println("Livre n°"+livre.getId()+" Titre : "+livre.getTitre()+", Auteur : "+ livre.getAuteur());
        }

        //Extraire en fonction du titre
        List<Livre> livreTit = null;
        livreTit = em
                .createQuery("select t from Livre t where t.titre =:titre",Livre.class)
                .setParameter("titre", "Du plaisir dans la cuisine")
                .getResultList();
        for(Livre livre:livreTit) {
            System.out.println("Livre n°"+livre.getId()+" Titre : "+livre.getTitre()+", Auteur : "+ livre.getAuteur());
        }


        //Supprimer
        Livre livSupp = em.find(Livre.class, 8);
        em.remove(livSupp);



        //Tout afficher
        List<Livre> livreAff = null;
        livreAff = em.createNativeQuery("select * from Livre", Livre.class).getResultList();
        for(Livre livre:livreAff) {
            System.out.println("Livre n°"+livre.getId()+" Titre : "+livre.getTitre()+", Auteur : "+ livre.getAuteur());
        }
        em.getTransaction().commit();

        System.out.println(em);
        em.close();
    }
}