package Session02;


@FunctionalInterface
interface PasswordValidator{
    boolean validate(String password);
}

public class EX2 {
    public static void main(String[] args) {
        PasswordValidator validator = password -> password.length() >= 8;

        System.out.println(validator.validate("12345678"));
        System.out.println(validator.validate("1234"));

    }
}
