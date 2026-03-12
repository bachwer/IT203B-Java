package Session04.Ex4;

public class PasswordService {

    public String evaluatePasswordStrength(String pass){
        if(pass == null || pass.length() < 8){
            return "Yeu";
        }

        boolean hasUpper = pass.matches(".*[A-Z].*");
        boolean hasLower = pass.matches(".*[a-z].*");
        boolean hasNumber = pass.matches(".*[0-9].*");
        boolean hasSpecial = pass.matches(".*[!@#$%^&*()].*");

        if(hasUpper && hasLower && hasNumber && hasSpecial){
            return "Manh";
        }

        if(hasUpper || hasLower || hasNumber || hasSpecial){
            return "Trung binh";
        }

        return "Yeu";
    }
}
