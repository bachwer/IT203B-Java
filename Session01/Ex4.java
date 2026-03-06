package Session01;

import java.io.IOException;

public class Ex4 {
    public static void main(String[] args) {
        try{
            processUserData();
        }catch(IOException e){
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
        System.out.println("Chương trình vẫn tiếp tục ");

    }

    public static void saveToFIle() throws IOException {
        System.out.println("Saving data to file !");

        throw new IOException("Error when to save data to file ! ");
    }


    public static void processUserData() throws IOException {
        System.out.println("Đang xử lý dữ liệu người dùng..");
        saveToFIle();
    }
}
