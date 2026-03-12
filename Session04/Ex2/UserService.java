package Session04.Ex2;

public class UserService {
    public boolean checkRegistrationAge(int age){


        if(age < 0 ){
            throw new IllegalArgumentException("age cannot be negative");
        }

        if(age >= 18){
            return true;
        }

        return false;
    }
}
