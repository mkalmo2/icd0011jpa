package main;

import javax.persistence.EntityManager;

import util.JpaUtil;
import dao.SetupDao;

public class Main {

    public static void main(String[] args) {

        new SetupDao().createSchema();

        JpaUtil.closeFactory();
    }

    public static void persist() {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();
            em.getTransaction().begin();

            // ...

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
