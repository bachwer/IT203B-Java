package Session10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class UserBusiness {
    static Scanner input = new Scanner(System.in);
static List<User> users = new ArrayList<>();

    private static int getValidAge(){
        while(true){
            try{
                System.out.print("Nhập Age (>0): ");
                int age = input.nextInt();
                input.nextLine();
                if(age > 0){
                    return age;
                }
                System.out.println("Age phải lớn hơn 0!");
            }catch(Exception e){
                System.out.println("Age phải là số!");
                input.nextLine();
            }
        }
    }

    private static String getValidRole(){
        while(true){
            System.out.print("Nhập Role (ADMIN / USER): ");
            String role = input.nextLine().toUpperCase();

            if(role.equals("ADMIN") || role.equals("USER")){
                return role;
            }
            System.out.println("Role chỉ được ADMIN hoặc USER!");
        }
    }

    private static double getValidScore(){
        while(true){
            try{
                System.out.print("Nhập Score (0 - 10): ");
                double score = input.nextDouble();
                input.nextLine();

                if(score >= 0 && score <= 10){
                    return score;
                }
                System.out.println("Score phải từ 0 đến 10!");
            }catch(Exception e){
                System.out.println("Score phải là số!");
                input.nextLine();
            }
        }
    }


    private static UserBusiness instance;

    private UserBusiness(){}

    public static UserBusiness getInstance(){
        if(instance == null){
            instance = new UserBusiness();
        }
        return instance;
    }



    public static Optional<User> checkID(String id){

        Optional<User> result = users.stream().filter(user -> user.getId().equals(id)).findFirst();

        return result;
    }




    public static void addUser(){

       while(true){
           System.out.println("Type (exit) to exit");
           System.out.print("Nhập ID: ");
           String id = input.nextLine();

           if(id.equalsIgnoreCase("exit")){
               return;
           }

           if(checkID(id).isPresent()){
               System.out.println("ID đã tồn tại  ");
               return;
           }
           System.out.print("Nhập Name: ");
           String name = input.nextLine();
           int age = getValidAge();
           String role = getValidRole();
           double score = getValidScore();



           User newUser =  new User(id, name, age, role, score );
           users.add(newUser);
           System.out.println("Add Thành Công user: " + name);


       }
    }

    public static void ShowUsers(){
        if(users.isEmpty()){
            System.out.println("KO cos User nao !");
            return;
        }

        System.out.println("------------ALL USER ----------");
        for(User u: users){
            u.displayUser();
        }

        System.out.println("------------END----------");
    }

    public static void UpdateUser(){
        System.out.println("Nhập ID: ");
        String id = input.nextLine();

        Optional<User> userOpt = checkID(id);
        userOpt.ifPresent(u -> {
            System.out.print("Nhập Name: ");
            String name = input.nextLine();
            int age = getValidAge();
            String role = getValidRole();
            double score = getValidScore();

            u.setName(name);
            u.setAge(age);
            u.setRole(role);
            u.setScore(score);

            System.out.println("Update Thành Công");

        });

        if(!userOpt.isPresent()){
            System.out.println("Không tìm thấy User");
        }
    }

    public static void searchByName(){
        System.out.print("Nhập Tên: ");
        String name =  input.nextLine();

        Optional<User> result = users.stream().filter(user -> user.getName().toLowerCase().equals(name.toLowerCase())).findFirst();


        result.ifPresent(u -> {
            System.out.println("Đã tìm Thấy User");
            u.displayUser();

        });

        if(!result.isPresent()){
            System.out.println("Ko tìm thấy");
        }



    }


    public static void deleteUser(){
        System.out.println("Nhập ID muốn xoá: ");
        String id = input.nextLine();
        Optional<User> result =  checkID(id);

        if(!result.isPresent()){
            System.out.println("Ko tìm thấy");
            return;
        }

        result.ifPresent(u -> {
            users.remove(u);
            System.out.println("Xoá thành công");
        });
    }

    public static void findUserByRole(){
        List<User> result = users.stream()
                .filter(u -> u.getRole().equalsIgnoreCase("ADMIN"))
                .collect(toList());

        if(result.isEmpty()){
            return;
        }

        System.out.println("------------ALL USER ADMIN ----------");
        for(User u: result){
            u.displayUser();
        }

        System.out.println("------------END----------");
    }

    public static void sortUserByScore(){
        if(users.isEmpty()){
            System.out.println("KO cos User nao !");
            return;
        }
        List<User> result = users.stream()
                .sorted((u1, u2) -> Double.compare(u2.getScore(), u1.getScore()))
                .collect(toList());

        result.forEach(User::displayUser);

    }

}
