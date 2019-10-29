package main;

import config.DbConfig;
import config.HsqlDataSource;
import config.PostgresDataSource;
import dao.PersonDao;
import model.Address;
import model.Person;
import model.Phone;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx =
              new AnnotationConfigApplicationContext(
                      DbConfig.class, HsqlDataSource.class);

        try (ctx) {

            PersonDao dao = ctx.getBean(PersonDao.class);

            Person person = new Person("jill");
            person.setAddress(new Address("kase 2"));

            person.getPhones().add(new Phone("123"));
            person.getPhones().add(new Phone("456"));

            dao.save(person);

            System.out.println(dao.findAll());
        }




    }
}