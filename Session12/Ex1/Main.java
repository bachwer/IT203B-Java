package Session12.Ex1;
public class Main {
    public static void main(String[] args) {

        // Test login đúng
        boolean result1 = P2.login("DOC001", "123456");
        System.out.println("Login đúng: " + result1);

        // Test SQL Injection (phải FAIL)
        boolean result2 = P2.login("DOC001", "' OR '1'='1");
        System.out.println("Injection test: " + result2);
    }
}