package Session03;

import java.util.List;
import java.util.Optional;

public class Ex3 {
    record User(String username, String email) {}

    static class UserRepository{
        List<User> users = List.of(
                new User("Alice", "alice@gmail.com"),
                new User("Bob", "Bob@gmail.com"),
                new User("Charlie", "Charlie@gmail.com")

        );

        Optional<User> findUserByUsername(){
            return users.stream()
                    .filter(user -> user.username().equals("Alice"))
                    .findFirst();
        }
    }

    public static void main(String[] args) {
        UserRepository repo = new UserRepository();

        Optional<User> user = repo.findUserByUsername();

//        user.ifPresent(u -> System.out.println("Welcome: " + u.username()));

        String result = user.map(u -> "Welcome: " + u.username()).orElse("Guest Login");

        System.out.println(result);
    }
}
