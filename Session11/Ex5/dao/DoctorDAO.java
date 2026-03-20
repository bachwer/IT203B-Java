package Session11.Ex5.dao;

import Session11.Ex1.DBContext;
import Session11.Ex5.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> getAllDoctors(){
        List<Doctor> list = new ArrayList<>();

        String sql = "SELECT * FROM doctors";

        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                list.add(new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialty")
                ));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertDoctor(Doctor d) {
        String sql = "INSERT INTO doctors VALUES (?, ?, ?)";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialty());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("❌ Mã bác sĩ đã tồn tại!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void countBySpecialty() {
        String sql = "SELECT specialty, COUNT(*) as total FROM doctors GROUP BY specialty";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getString("specialty") +
                        " - " + rs.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
