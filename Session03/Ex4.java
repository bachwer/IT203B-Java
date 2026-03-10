package Session03;

import java.util.List;
import java.util.stream.Collectors;

public class Ex4 {

    record User(String username, String email){};

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alice1", "alice@gmail.com"),
                new User("alice2", "alice2@gmail.com"),
                new User("alice3", "alice3@gmail.com"),
                new User("alice4", "alice4@gmail.com")
        );


        List<User> uniqueUsers = users.stream().collect(Collectors.toMap(User::username, user -> user, (u1, u2) -> u1)).values().stream().toList();
        uniqueUsers.forEach(System.out::println);

    }
}
