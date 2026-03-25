package Session12.Ex6;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Hospital_db",
                "root",
                "12121212"
        );
    }
}