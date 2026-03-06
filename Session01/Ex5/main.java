package Session01.Ex5;


public class main {
    public static void main(String[] args) {

        User user = new User();

        try {
            user.setAge(-10);
        } catch (CustomException e) {
            System.out.println("Lỗi nghiệp vụ: " + e.getMessage());
        }

        System.out.println("Chương trình vẫn tiếp tục chạy...");
    }
}