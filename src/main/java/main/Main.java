package main;

import dao.SetupDao;
import model.Address;
import model.Person;
import model.Phone;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            new Main().run();
        } finally {
            JpaUtil.closeFactory();
        }
    }

    private void run() {
        new SetupDao().createSchema();

        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            em.getTransaction().commit();

            System.out.println(1);

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

}
