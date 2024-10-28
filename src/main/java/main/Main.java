package main;

import config.DbConfig;
import config.HsqlDataSource;
import dao.PersonDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx =
              new AnnotationConfigApplicationContext(
                      DbConfig.class, HsqlDataSource.class);

        PersonDao dao = ctx.getBean(PersonDao.class);


    }
}