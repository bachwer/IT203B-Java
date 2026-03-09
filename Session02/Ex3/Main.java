package Session02.Ex3;

public class Main {
    public static void main(String[] args) {

        User user = new User("123456");

        System.out.println(user.isAuthenticated());

        String encrypted = Authenticatable.encrypt("123456");
        System.out.println(encrypted);
    }
}
