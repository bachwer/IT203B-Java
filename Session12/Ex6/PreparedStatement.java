package Session12.Ex6;

import java.sql.*;

public class PreparedStatement {
    public static void updateMedicineStock(int id, int addedQuantity) {
        String sql = "UPDATE medicines SET stock = stock + ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, addedQuantity); // 🔥 đúng thứ tự
            ps.setInt(2, id);

            ps.executeUpdate();
            System.out.println("✅ Cập nhật kho thành công");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void findMedicinesByPriceRange(double min, double max) {
        String sql = "SELECT * FROM medicines WHERE price BETWEEN ? AND ?";

        try (Connection conn = DBUtil.getConnection();
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, min);
            ps.setDouble(2, max);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void calculatePrescriptionTotal(int id) {
        String sql = "{CALL CalculatePrescriptionTotal(?, ?)}";

        try (Connection conn = DBUtil.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DECIMAL); // 🔥 bắt buộc

            cs.execute();

            double total = cs.getDouble(2);
            System.out.println("💰 Tổng tiền đơn: " + total);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getDailyRevenue(String date) {
        String sql = "{CALL GetDailyRevenue(?, ?)}";

        try (Connection conn = DBUtil.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, date); // yyyy-MM-dd
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();

            double revenue = cs.getDouble(2);
            System.out.println("📊 Doanh thu: " + revenue);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
