package main;

import config.DbConfig;
import dao.PersonDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx =
              new AnnotationConfigApplicationContext(DbConfig.class);

        PersonDao dao = ctx.getBean(PersonDao.class);



        ctx.close();
    }
}