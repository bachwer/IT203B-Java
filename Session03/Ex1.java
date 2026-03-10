package Session03;

import java.util.List;

public class Ex1 {

    enum Status {ACTIVE, INACTIVE}

    record User(String username, String email, Status status){ };

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("Toan 2k6", "toanfkboy@gmail.com",Status.ACTIVE),
                new User("Toan 2k8", "toanfkboy1@gmail.com",Status.ACTIVE),
                new User("Toan 2k9", "toanfkboy2@gmail.com",Status.INACTIVE)
        );
        users.forEach(user -> System.out.println("Username: " + user.username + ", Email: "+user.email() + ", Status: " + user.status ));


    }
}
