package main;

import dao.PersonDao;
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

        new SetupDao().createSchema();
        PersonDao dao = new PersonDao();

        dao.insertPerson("Jack", Arrays.asList(new Phone("123"), new Phone("456")));
        dao.insertPerson("Jill");

        Person person = findByName("Jill");

        person.setName("Jillian");
        person.setAddress(new Address("Oak street 1"));

        save(person);

        System.out.println(findByName("Jillian"));

        printAll();
    }

    private static void save(Person person) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    private static Person findByName(String name) {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select p from Person p LEFT JOIN FETCH p.phones where p.name = :name", Person.class);

            query.setParameter("name", name);

            return query.getSingleResult();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

    private static void printAll() {
        EntityManager em = null;

        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select distinct p from Person p LEFT JOIN FETCH p.phones", Person.class);

            System.out.println(query.getResultList());


        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

}
