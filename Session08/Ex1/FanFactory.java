package Session08.Ex1;

public class FanFactory extends DeviceFactory {
    public Device createDevice() { 
        System.out.println("FanFactory: Đã tạo quạt mới."); 
        return new Fan(); 
    }
}
