package Session12.Ex3;

import java.sql.*;


public class P2 {
    public static void getSurgeryFee(int surgeryId) {
        String url = "jdbc:mysql://localhost:3306/Hospital_db";
        String user = "root";
        String password = "12121212";

        String sql = "{CALL GET_SURGERY_FEE(?, ?)}";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             CallableStatement cs = conn.prepareCall(sql)) {

            // 1. Set IN parameter
            cs.setInt(1, surgeryId);

            // 2. Register OUT parameter (QUAN TRỌNG)
            cs.registerOutParameter(2, Types.DECIMAL);

            // 3. Execute
            cs.execute();

            // 4. Lấy kết quả
            double totalCost = cs.getDouble(2);

            System.out.println("💰 Chi phí phẫu thuật: " + totalCost);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
