package Session01;

import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        try{
            System.out.print("Nhập năm sinh của bạn: ");
            String yearString = input.nextLine();
            int year = Integer.parseInt(yearString);
            int yearOld = 2026 - year;
            System.out.println("Tuổi của bạn là: " + yearOld);
        }catch(NumberFormatException e){
            System.out.println("Lỗi: ");
        } finally {
            input.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }

    }
}
