package Session11.Ex6;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppointmentRepository repo = new AppointmentRepository();

        // Thêm
        Appointment a1 = new Appointment(0, "Nguyen Van A", Date.valueOf("2026-03-25"), "Dr. B", "Scheduled");
        repo.addAppointment(a1);

        // Cập nhật
        Appointment update = new Appointment(1, "Nguyen Van A", Date.valueOf("2026-03-26"), "Dr. C", "Updated");
        repo.updateAppointment(update);

        // Lấy theo ID
        Appointment result = repo.getAppointmentById(1);
        if (result != null) {
            System.out.println(result.getPatientName() + " - " + result.getDoctorName());
        }

        // Lấy tất cả
        List<Appointment> list = repo.getAllAppointments();
        for (Appointment a : list) {
            System.out.println(a.getId() + " | " + a.getPatientName() + " | " + a.getStatus());
        }

        // Xóa
        repo.deleteAppointment(1);
    }
}