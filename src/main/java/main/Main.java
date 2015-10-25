package main;

import javax.persistence.EntityManager;

import util.JpaUtil;
import dao.SetupDao;

public class Main {

    public static void main(String[] args) {

        new SetupDao().createSchema();

        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            // ...

        } finally {
            JpaUtil.closeQuietly(em);
        }

        JpaUtil.closeFactory();
    }


}
