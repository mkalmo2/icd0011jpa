package dao;

import model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PersonDao {

    private EntityManager em;

    public void save(Person person) {

    }

    public List<Person> findAll() {
        return null;
    }

    public Person findPersonByName(String name) {
        return null;
    }
}
