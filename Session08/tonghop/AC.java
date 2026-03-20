package Session08.tonghop;

public class AC implements Device {
    private int temp = 25;
    public void turnOn() { System.out.println("Điều hòa: Bật"); }
    public void turnOff() { System.out.println("Điều hòa: Tắt"); }
    public void setTemp(int t) { this.temp = t; System.out.println("Điều hòa: Nhiệt độ = " + t); }
}
