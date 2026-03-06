package Session01.Ex6;
public class User {

    private String name;
    private int age;

    public User(String name) {
        this.name = name;
    }

    public void setAge(int age) throws InvalidAgeException {

        if (age < 0) {
            throw new InvalidAgeException("Tuổi không thể âm!");
        }

        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}