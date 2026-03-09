package Session02.Ex4;


import java.util.*;
import java.util.function.*;

class User {
    private final String username;

    public User() {
        this.username = "guest";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class Main {
    public static void main(String[] args) {

        List<User> users = List.of(
                new User("Alice"),
                new User("Bob"),
                new User("Charlie")
        );

        // Method reference lấy username
        Function<User, String> getUsername = User::getUsername;

        // Method reference in ra console
        Consumer<String> printer = System.out::println;

        // Constructor reference
        Supplier<User> createUser = User::new;

        users.stream()
                .map(getUsername)
                .forEach(printer);

        User newUser = createUser.get();
        System.out.println(newUser.getUsername());
    }
}