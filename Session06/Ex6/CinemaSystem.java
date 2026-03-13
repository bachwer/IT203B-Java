package Session06.Ex6;

import java.util.Scanner;

public class CinemaSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CinemaManager manager = new CinemaManager();

        while (true) {

            System.out.println("\n===== MENU =====");

            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng");
            System.out.println("3. Tiếp tục");
            System.out.println("4. Thêm vé vào phòng");
            System.out.println("5. Xem thống kê");
            System.out.println("6. Phát hiện deadlock");
            System.out.println("7. Thoát");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Số phòng: ");
                    int rooms = sc.nextInt();

                    System.out.print("Vé mỗi phòng: ");
                    int tickets = sc.nextInt();

                    System.out.print("Số quầy: ");
                    int counters = sc.nextInt();

                    manager.start(rooms, tickets, counters);
                    break;

                case 2:
                    manager.pause();
                    break;

                case 3:
                    manager.resume();
                    break;

                case 5:
                    manager.statistics();
                    break;

                case 7:
                    manager.shutdown();
                    return;
            }
        }
    }
}