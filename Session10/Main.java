package Session10;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("********************* QUẢN LÝ NGƯỜI DÙNG *********************");
            System.out.println("1. Hiển thị danh sách toàn bộ người dùng");
            System.out.println("2. Thêm mới người dùng");
            System.out.println("3. Cập nhật thông tin người dùng theo mã");
            System.out.println("4. Xóa người dùng theo mã");
            System.out.println("5. Tìm kiếm người dùng theo tên");
            System.out.println("6. Lọc danh sách người dùng ADMIN");
            System.out.println("7. Sắp xếp danh sách theo điểm đánh giá giảm dần");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    UserBusiness.ShowUsers();
                    break;
                case 2:
                    UserBusiness.addUser();
                    break;
                case 3:
                    UserBusiness.UpdateUser();
                    break;
                case 4:
                    UserBusiness.deleteUser();
                    break;
                case 5:
                    UserBusiness.searchByName();
                    break;
                case 6:
                    UserBusiness.findUserByRole();
                    break;
                case 7:
                    UserBusiness.sortUserByScore();
                    break;
                case 8:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 8);

        input.close();
    }
}
