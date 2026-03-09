package Session02.Ex5;

class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        System.out.println("SuperAdmin activity: " + activity);

        // Có thể gọi method của interface cụ thể nếu muốn
        UserActions.super.logActivity(activity);
        AdminActions.super.logActivity(activity);
    }
}