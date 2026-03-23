package Session12.Ex5;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Hospital_db",
                "root",
                "12121212"
        );
    }
}
