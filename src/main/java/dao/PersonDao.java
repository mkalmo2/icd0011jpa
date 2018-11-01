package dao;

import model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Person save(Person person) {
        if (person.getId() == null) {
            em.persist(person);
        } else {
            em.merge(person);
        }

        return person;
    }

    public List<Person> findAll() {
        return em.createQuery(
                "SELECT distinct p FROM Person p left join fetch p.phones",
                Person.class).getResultList();

    }

    public Person findPersonByName(String name) {
        TypedQuery<Person> query = em.createQuery(
                "SELECT distinct p FROM Person p " +
                        "LEFT JOIN FETCH p.phones " +
                        "WHERE p.name = :name", Person.class);

        query.setParameter("name", name);

        return query.getSingleResult();

    }

}
