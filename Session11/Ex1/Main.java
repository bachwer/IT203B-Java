package Session11.Ex1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        getPatients();
    }

    public static void getPatients() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getConnection();
            stmt = conn.createStatement();

            String query = "SELECT * FROM Patients";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Patient ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {}

            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {}

            try {
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
    }
}