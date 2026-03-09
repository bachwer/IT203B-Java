package Session02.Ex5;

public interface AdminActions {

    default void logActivity(String activity){
        System.out.println("Amid activity: " + activity);
    }
}
