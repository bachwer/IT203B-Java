package Session01.Ex3;

public class main {
    public static void main(String[] args) {

        User user = new User();


        try{
            user.setAge(-5);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println("..");
    }
}
