package main;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import dao.SetupDao;
import model.*;
import util.JpaUtil;

public class Main {

    public static void main(String[] args) {

//        new SetupDao().createSchema();

//    	insertPerson("John");
//    	insertPerson("Mery");

        // Tehke meetod, mis v�imaldab loetud isikut salvestada.

//    	System.out.println(findAllPerson());
//    	Person john = findPersonByName("John");
//    	john.setName("Jonny");
//    	savePerson(john);

//    	Person person = new Person();
//    	person.setName("Jill");
//    	Address address = new Address();
//    	address.setStreet("Kase 2");
//    	person.setAddress(address);
//
//    	savePerson(person);

//    	Person jill = findPersonByName("Jill");
//    	System.out.println(jill);
//    	System.out.println(jill.getAddress());

        // Lisage isikule telefonide list @OneToMany.

//        Person person = new Person();
//        person.setName("Alice1");
//        Phone phone = new Phone();
//        phone.setNumber("111");
//        phone.setPerson(person);
//        person.setPhones(Arrays.asList(phone));
//        savePerson(person);

        Person p = findPersonByName("Alice1");
        System.out.println(p);
        System.out.println(p.getPhones());

        JpaUtil.closeFactory();
    }

    private static void savePerson(Person person) {
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
            if (em != null) em.close();
        }
    }

    private static Person findPersonByName(String name) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select p from Person p where p.name = :name",
                    Person.class);
            query.setParameter("name", name);

            return query.getSingleResult();

        } finally {
            if (em != null) em.close();
        }
    }

    private static List<Person> findAllPerson() {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            TypedQuery<Person> query = em.createQuery(
                    "select p from Person p", Person.class);

            return query.getResultList();

        } finally {
            if (em != null) em.close();
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
            if (em != null) em.close();
        }
    }

}
