package Session12.Ex5;
import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== RHMS MENU =====");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Thêm bệnh nhân");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & tính phí");
            System.out.println("5. Thoát");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> PatientService.listPatients();
                case 2 -> PatientService.addPatient();
                case 3 -> PatientService.updateDisease();
                case 4 -> PatientService.discharge();
                case 5 -> System.exit(0);
            }
        }
    }


}