package Session12.Ex5;

import java.sql.*;
import java.util.Scanner;

public class PatientService {
    public static void listPatients() {
        String sql = "SELECT * FROM patients";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("department")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPatient() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Tên: ");
        String name = sc.nextLine();

        System.out.print("Tuổi: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Khoa: ");
        String dept = sc.nextLine();

        String sql = "INSERT INTO patients (name, age, department, admission_date) VALUES (?, ?, ?, CURDATE())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name); // 🔥 xử lý cả L'Oréal OK
            ps.setInt(2, age);
            ps.setString(3, dept);

            ps.executeUpdate();

            System.out.println("✅ Thêm thành công");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateDisease() {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID bệnh nhân: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Bệnh: ");
        String disease = sc.nextLine();

        String sql = "UPDATE patients SET disease = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, disease);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("✅ Cập nhật thành công");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void discharge() {
        Scanner sc = new Scanner(System.in);

        System.out.print("ID bệnh nhân: ");
        int id = sc.nextInt();

        String sql = "{CALL CALCULATE_DISCHARGE_FEE(?, ?)}";

        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.DECIMAL);

            cs.execute();

            double fee = cs.getDouble(2);

            System.out.println("💰 Viện phí: " + fee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
