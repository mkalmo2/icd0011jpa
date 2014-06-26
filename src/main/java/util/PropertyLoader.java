package util;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.tools.ant.taskdefs.Property;

public class PropertyLoader {

    public HashMap<String, String> getPropertiesAsMap() {
        final Property p = getPropertyObject();

        HashMap<String, String> hashMap = new HashMap<String, String>();

        Hashtable<String, Object> properties = p.getProject().getProperties();
        for (Entry<String, Object> each : properties.entrySet()) {
            hashMap.put(each.getKey(), (String) each.getValue());
        }

        return hashMap;
    }

    private Property getPropertyObject() {
        String filePath = getClass()
                .getResource("/application.properties").getFile();

        final Property p = AntUtil.getTask(Property.class, "property");
        p.setFile(new File(filePath));
        p.execute();
        return p;
    }

    public Properties getProperties() {
        final Property p = getPropertyObject();

        return new Properties() {
            private static final long serialVersionUID = 1L;

            public String getProperty(String key) {
                return p.getProject().getProperty(key);
            }
        };
    }


}
