package Session02.Ex6;

@FunctionalInterface
interface UserProcessor {
    String process(User u);
}

public class Main {

    public static void main(String[] args) {

        User user = new User("bach");

        // Method Reference tới static method
        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(user);

        System.out.println(result);
    }
}