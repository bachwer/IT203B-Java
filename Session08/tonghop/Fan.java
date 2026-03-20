package Session08.tonghop;

public class Fan implements Device, TempObserver {
    public void turnOn() { System.out.println("Quạt: Bật"); }
    public void turnOff() { System.out.println("Quạt: Tắt"); }
    @Override
    public void update(int temp) {
        if (temp > 25) System.out.println("Quạt: Nhiệt độ " + temp + " -> Chạy mạnh");
        else System.out.println("Quạt: Nhiệt độ " + temp + " -> Chạy chậm/Tắt");
    }
}
