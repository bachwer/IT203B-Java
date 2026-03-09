package Session02.Ex5;

public interface UserActions {

    default void logActivity(String activity){
        System.out.println("User activity: " + activity);
    }

}
