package main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.SetupDao;
import model.*;
import util.JpaUtil;

public class Main {

    public static void main(String[] args) {

        new SetupDao().createSchema();

        persist("Jill");
        persist("Jack");
        persist("John");
        System.out.println(findAllPersons());

        Person jill = findByName("Jill");
        jill.setName("Mary");
        save(jill);

        Person person = findByName("Jack");
        System.out.println(person);

        setAddress(person, "Kuuse 1");

        System.out.println(findByName("Jack").getAddress());

        JpaUtil.closeFactory();
    }

    private static void setAddress(Person person, String street) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();
            em.getTransaction().begin();

            Address address = new Address();
            address.setStreet(street);
            person.setAddress(address);
            em.persist(address);
            em.merge(person);

            em.getTransaction().commit();
        } finally {
            close(em);
        }
    }



    public static void save(Person person) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();
            em.getTransaction().begin();

            if (person.getId() == null) {
                em.persist(person);
            } else {
                em.merge(person);
            }

            em.getTransaction().commit();
        } finally {
            close(em);
        }
    }

    public static void persist(String name) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();
            em.getTransaction().begin();

            Person person = new Person();
            person.setName(name);
            em.persist(person);

            em.getTransaction().commit();
        } finally {
            close(em);
        }
    }

    private static void close(EntityManager em) {
        if (em != null) em.close();
    }

    public static void persistPhone(String number) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();
            em.getTransaction().begin();

            Phone phone = new Phone();
            em.persist(phone);

            em.getTransaction().commit();
        } finally {
            close(em);
        }
    }

    public static List<Person> findAllPersons() {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select p from Person p", Person.class);

            return query.getResultList();

        } finally {
            close(em);
        }
    }

    public static Person findByName(String name) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select p from Person p where p.name = :name", Person.class);
            query.setParameter("name", name);
            return query.getSingleResult();

        } finally {
            close(em);
        }
    }

}
