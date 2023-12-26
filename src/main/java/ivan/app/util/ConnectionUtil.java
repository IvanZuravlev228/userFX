package ivan.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName(AppProperty.getProperties().getProperty("datasource.driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find class for connection", e);
        }
    }

    public static Connection getConnection() {
        try {
            Properties dbProperties = new Properties();
            dbProperties.put("user", AppProperty.getProperties().getProperty("datasource.username"));
            dbProperties.put("password", AppProperty.getProperties().getProperty("datasource.password"));
            return DriverManager.getConnection(AppProperty.getProperties().getProperty("datasource.url"),
                    dbProperties);
        } catch (SQLException e) {
            throw new RuntimeException("Can't create connection to DB", e);
        }
    }
}
