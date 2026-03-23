package Session12.Ex6;
import java.util.Scanner;

import static Session12.Ex6.PreparedStatement.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Update stock");
        System.out.println("2. Find by price");
        System.out.println("3. Total prescription");
        System.out.println("4. Daily revenue");

        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> updateMedicineStock(1, 20);
            case 2 -> findMedicinesByPriceRange(10, 20);
            case 3 -> calculatePrescriptionTotal(1);
            case 4 -> getDailyRevenue("2026-03-23");
        }
    }
}