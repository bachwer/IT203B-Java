package Session04.Ex1;

public class UserValidator {
    public boolean isValidUsername(String username){

        if(username == null){
            return false;
        }


        if(username.length() < 6 || username.length() > 20){
            return false;
        }

        return !username.contains(" ");

    }

}
