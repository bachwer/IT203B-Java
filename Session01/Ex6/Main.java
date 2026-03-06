package Session01.Ex6;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.print("Nhập tên người dùng: ");
            String name = scanner.nextLine();

            User user = new User(name);

            // Defensive programming
            if (user.getName() != null) {
                System.out.println("Xin chào " + user.getName());
            }

            // Bài 1: nhập năm sinh
            System.out.print("Nhập năm sinh: ");
            String yearStr = scanner.nextLine();

            int year = Integer.parseInt(yearStr);
            int age = 2026 - year;

            user.setAge(age);

            System.out.println("Tuổi người dùng: " + user.getAge());

            // Bài 2: chia nhóm
            System.out.print("Nhập tổng số người: ");
            int totalUsers = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập số nhóm: ");
            int groups = Integer.parseInt(scanner.nextLine());

            if (groups == 0) {
                System.out.println("Không thể chia cho 0!");
            } else {
                int result = totalUsers / groups;
                System.out.println("Mỗi nhóm có: " + result + " người.");
            }

            // Bài 4: ghi file
            UserService.processUserData(user);

        }

        // lỗi parse số
        catch (NumberFormatException e) {
            Logger.logError("Người dùng nhập sai định dạng số", e);
        }

        // lỗi nghiệp vụ
        catch (InvalidAgeException e) {
            Logger.logError("Lỗi nghiệp vụ tuổi", e);
        }

        // lỗi môi trường
        catch (IOException e) {
            Logger.logError("Lỗi hệ thống khi ghi file", e);
        }

        finally {
            scanner.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }

        System.out.println("Chương trình kết thúc an toàn.");
    }
}