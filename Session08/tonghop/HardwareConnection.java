package Session08.tonghop;

public class HardwareConnection {
    private static HardwareConnection instance;
    private HardwareConnection() {}
    public static HardwareConnection getInstance() {
        if (instance == null) {
            instance = new HardwareConnection();
            System.out.println("Hardware: Đã kết nối phần cứng.");
        }
        return instance;
    }
}
