package Session01.Ex6;
import java.io.IOException;

public class UserService {

    public static void saveToFile(User user) throws IOException {

        System.out.println("Đang lưu dữ liệu người dùng vào file...");

        // giả lập lỗi ghi file
        throw new IOException("Không thể ghi dữ liệu vào file hệ thống.");
    }

    public static void processUserData(User user) throws IOException {

        System.out.println("Đang xử lý dữ liệu người dùng...");
        saveToFile(user);
    }
}