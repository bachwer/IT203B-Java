package Session11.Ex2;

import Session11.Ex1.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void getMedicines() {
        String query = "SELECT name, quantity FROM Medicines";

        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("=== DANH MỤC THUỐC ===");

            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");

                System.out.println("Tên thuốc: " + name);
                System.out.println("Số lượng: " + quantity);
                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
