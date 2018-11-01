package main;

import config.DbConfig;
import dao.PersonDao;
import model.Address;
import model.Person;
import model.Phone;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx =
              new AnnotationConfigApplicationContext(DbConfig.class);

        PersonDao dao = ctx.getBean(PersonDao.class);

        Person person = new Person("jack");

        person.getPhones().add(new Phone("123"));
        person.getPhones().add(new Phone("456"));

        person.setAddress(new Address("kase 1"));

        dao.save(person);
        dao.save(new Person("jill"));

        System.out.println(dao.findAll());
        System.out.println(dao.findPersonByName("jill"));

        ctx.close();
    }
}