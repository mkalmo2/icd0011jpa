package main;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.SetupDao;
import model.Address;
import model.Person;
import model.Phone;
import util.JpaUtil;

public class Main {

    public static void main(String[] args) {

        new SetupDao().createSchema();

        insertPerson("Jack");
        insertPerson("Jill");
        insertPerson("Joe");

        System.out.println(findAllPersons());
        Person jill = findPersonForName("Jill");

        jill.setAddress(new Address("Kase 1"));

        save(jill);

        jill = findPersonForName("Jill");

        System.out.println(jill.getAddress());

        jill.setPhones(Arrays.asList(
                new Phone("123"), new Phone("456")));

        save(jill);

        jill = findPersonForName("Jill");

        System.out.println(jill.getPhones());

        JpaUtil.closeFactory();
    }

    private static void save(Person person) {
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
            JpaUtil.closeQuietly(em);
        }
    }

    private static List<Person> findAllPersons() {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery("select p from Person p", Person.class);

            return query.getResultList();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    private static Person findPersonForName(String name) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select p from Person p where p.name = :name",
                    Person.class);
            query.setParameter("name", name);

            return query.getSingleResult();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    private static void insertPerson(String name) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            Person person = new Person();
            person.setName(name);

            em.persist(person);

            em.getTransaction().commit();
        } finally {
            JpaUtil.closeQuietly(em);
        }
    }


}
