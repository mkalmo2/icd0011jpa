package dao;

import java.io.File;
import java.util.Properties;

import org.apache.tools.ant.taskdefs.SQLExec;

import util.AntUtil;
import util.PropertyLoader;

public class SetupDao {

    public void createSchema() {
        String filePath = getClass().getResource("/schema.sql").getFile();

        executeSql(filePath, new PropertyLoader().getProperties());
    }

    private void executeSql(String filePath, Properties prop) {
        SQLExec sql = AntUtil.getTask(SQLExec.class, "sql");
        sql.setSrc(new File(filePath));
        sql.setDriver("org.hsqldb.jdbcDriver");
        sql.setUserid("sa");
        sql.setPassword("");
        sql.setUrl(prop.getProperty("javax.persistence.jdbc.url"));
        sql.execute();
    }

}
