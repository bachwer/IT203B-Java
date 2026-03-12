package Session04.Ex6;


import java.time.LocalDate;
import java.util.List;

public class UserService {

    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {

        if(newProfile.getBirthDate().isAfter(LocalDate.now())){
            return null;
        }

        String newEmail = newProfile.getEmail();

        if(!newEmail.equals(existingUser.getEmail())){
            if(allUsers != null){
                for(User u : allUsers){
                    if(u.getEmail().equals(newEmail)){
                        return null;
                    }
                }
            }
        }

        existingUser.setEmail(newEmail);
        existingUser.setBirthDate(newProfile.getBirthDate());

        return existingUser;
    }
}