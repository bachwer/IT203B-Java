package Session12.Ex1;

import java.sql.*;

public class P2 {
    public static boolean login(String doctorCode, String password){
        String url = "jdbc:mysql://localhost:3306/Hospital_DB";
        String user = "root";
        String pass = "12121212";

        String sql =  "SELECT * FROM doctors WHERE doctor_code = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, doctorCode);
                ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // có kết quả = login thành công


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
