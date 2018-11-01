package dao;

import model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PersonDao {

    private EntityManager em;

    public void save(Person person) {

    }

}
