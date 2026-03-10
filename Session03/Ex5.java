package Session03;

import java.util.Comparator;
import java.util.List;

public class Ex5 {
    record User(String username, String email) {}

    public static void main(String[] args){

        List<User> users = List.of(
                new User("alexander", "a@gmail.com"),
                new User("charlotte", "c@gmail.com"),
                new User("Benjamin", "b@gmail.com"),
                new User("bob", "bob@gmail.com"),
                new User("anna", "anna@gmail.com")
        );
        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .forEach(u -> System.out.println(u.username()));

    }
}
