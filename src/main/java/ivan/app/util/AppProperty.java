package ivan.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class AppProperty {
    private static final String PATH_TO_PROPERTIES_FILE = "./src/main/resources/application.properties";
    private static Properties properties;

    static {
        readProperty();
    }

    public static Properties getProperties() {
        return properties;
    }

    private static void readProperty() {
        try (FileInputStream f = new FileInputStream(new File(PATH_TO_PROPERTIES_FILE))) {
            properties = new Properties();
            properties.load(f);

        } catch (FileNotFoundException e) {
            System.out.println("application.properties not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
