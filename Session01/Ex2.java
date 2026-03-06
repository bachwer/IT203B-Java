package Session01;


import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Nhập tổng số người: ");
        int TotalPeople = input.nextInt();


        System.out.print("Nhập số nhóm muốn chia: ");
        int groupNUmber = input.nextInt();

        try{
            int newGroup = TotalPeople / groupNUmber;
            System.out.println("Mỗi Nhóm có: " + newGroup  + " Người");
        } catch(ArithmeticException e){
            System.out.println("Không thể chia cho 0!");
        }

        System.out.println("..");

        input.close();

    }
}
