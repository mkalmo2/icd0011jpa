package dao;

import model.Person;
import model.Phone;
import util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class PersonDao {

    public void insertPerson(String name) {
        insertPerson(name, Collections.emptyList());
    }

    public void insertPerson(String name, List<Phone> phones) {
        EntityManager em = null;
        try {
            em = JpaUtil.getFactory().createEntityManager();

            em.getTransaction().begin();

            Person person = new Person();
            person.setName(name);
            person.setPhones(phones);

            em.persist(person);

            em.getTransaction().commit();

        } finally {
            JpaUtil.closeQuietly(em);
        }
    }

}
