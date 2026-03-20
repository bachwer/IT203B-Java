package Session11.Ex5.service;

import Session11.Ex5.dao.DoctorDAO;
import Session11.Ex5.model.Doctor;

import java.util.List;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public void showDoctors() {
        List<Doctor> list = dao.getAllDoctors();

        if (list.isEmpty()) {
            System.out.println("Danh sách trống!");
            return;
        }

        for (Doctor d : list) {
            System.out.println(d.getId() + " | " +
                    d.getName() + " | " +
                    d.getSpecialty());
        }
    }

    public void addDoctor(String id, String name, String specialty) {
        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("❌ Không được để trống!");
            return;
        }

        dao.insertDoctor(new Doctor(id, name, specialty));
    }

    public void statistic() {
        dao.countBySpecialty();
    }
}