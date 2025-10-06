package groupID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("addressB");
        EntityManager em = emf.createEntityManager();

        try {
            BuddyInfo buddy = new BuddyInfo("Umniyah", "K2H7P1", "12345");

            em.getTransaction().begin();
            em.persist(buddy);
            em.getTransaction().commit();

            System.out.println("Persisted BuddyInfo :P : " + buddy);

            List<BuddyInfo> buddies = em.createQuery("SELECT b FROM BuddyInfo b", BuddyInfo.class)
                    .getResultList();

            System.out.println("\nDB Buddies:");
            for (BuddyInfo b : buddies) {
                System.out.println(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}