package Session03;

import java.util.List;

public class Ex2 {

    record User(String username, String email) {}


    public static void main(String[] args) {
        List<User> users = List.of(
                new User("toanalice", "alice@gmail.com"),
                new User("toanbob", "bob@yahoo.com"),
                new User("toancharlie", "charlie@gmail.com")
        );


        users.stream()
                .filter(user -> user.email().endsWith("@gmail.com"))
                .forEach(user -> System.out.println(user.username()));
    }
}
