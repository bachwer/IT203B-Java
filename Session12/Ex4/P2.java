package Session12.Ex4;

import java.sql.*;

public class P2 {
    public static void insertBatch(){
        String url = "jdbc:mysql://localhost:3306/hospital_db";
        String user = "root";
        String password = "121212";
        String sql = "INSERT INTO lab_results (patient_id, result_value) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 🔥 chỉ prepare 1 lần

            for (int i = 1; i <= 1000; i++) {
                ps.setInt(1, i);
                ps.setDouble(2, 5.5);

                ps.executeUpdate(); // execute nhiều lần
            }

            System.out.println("✅ Insert thành công");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
