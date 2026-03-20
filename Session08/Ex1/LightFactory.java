package Session08.Ex1;

public class LightFactory extends DeviceFactory {
    public Device createDevice() { 
        System.out.println("LightFactory: Đã tạo đèn mới."); 
        return new Light(); 
    }
}
