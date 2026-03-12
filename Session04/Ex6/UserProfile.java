package Session04.Ex6;

import java.time.LocalDate;

public class UserProfile {


    private String email;
    private LocalDate birthDate;

    public UserProfile(String email, LocalDate birthDate) {
        this.email = email;
        this.birthDate = birthDate;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
