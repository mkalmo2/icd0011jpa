package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("my-hsql-unit",
                        new PropertyLoader().getPropertiesAsMap());;

    public static EntityManagerFactory getFactory() {
        return emf;
    }

    public static void closeFactory() {
        emf.close();
    }

    public static void closeQuietly(EntityManager em) {
        if (em != null) {
            em.close();
        }
    }

}
