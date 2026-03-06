package Session01.Ex5;

public class User {

    private int age;

    public void setAge(int age) throws CustomException {

        if (age < 0) {
            throw new CustomException("Tuổi không thể âm!");
        }

        this.age = age;
    }

    public int getAge() {
        return age;
    }
}