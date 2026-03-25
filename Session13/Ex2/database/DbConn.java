package Session13.Ex2.database;

import java.sql.*;
import config.DatabaseConfig;

public class DbConn {
    public static Connection getC() throws Exception {
        Class.forName(DatabaseConfig.DRIVER);
        return DriverManager.getConnection(DatabaseConfig.getURL("RikkeiHospital_Pay"), DatabaseConfig.USER, DatabaseConfig.PASS);
    }
}
