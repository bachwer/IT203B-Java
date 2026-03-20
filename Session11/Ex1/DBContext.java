package Session11.Ex1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    // Hằng số cấu hình
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Hospital_DB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12121212";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}