package Session11.Ex6;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    // Thêm lịch khám
    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, appointment.getPatientName());
            stmt.setDate(2, appointment.getAppointmentDate());
            stmt.setString(3, appointment.getDoctorName());
            stmt.setString(4, appointment.getStatus());

            stmt.executeUpdate();
            System.out.println("Thêm lịch khám thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật
    public void updateAppointment(Appointment appointment) {
        String sql = "UPDATE appointments SET patient_name=?, appointment_date=?, doctor_name=?, status=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, appointment.getPatientName());
            stmt.setDate(2, appointment.getAppointmentDate());
            stmt.setString(3, appointment.getDoctorName());
            stmt.setString(4, appointment.getStatus());
            stmt.setInt(5, appointment.getId());

            stmt.executeUpdate();
            System.out.println("Cập nhật thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa
    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Xóa thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy theo ID
    public Appointment getAppointmentById(int id) {
        String sql = "SELECT * FROM appointments WHERE id=?";
        Appointment appointment = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointment;
    }

    // Lấy tất cả
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
                list.add(appointment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}