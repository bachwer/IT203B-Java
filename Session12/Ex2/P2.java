package Session12.Ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class P2 {

    public static void updateVitalSigns(int patientId, double temperature, int hearRate){
        String url = "jdbc:mysql://localhost:3306/Hospital_db";
        String user = "root";
        String password = "12121212";

        String sql = "UPDATE patients SET temperature = ?, heart_rate = ? WHERE id = ?";


        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setDouble(1, temperature);
            ps.setInt(2, hearRate);
            ps.setInt(3, patientId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Cập nhật thành công");
            } else {
                System.out.println("❌ Không tìm thấy bệnh nhân");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
