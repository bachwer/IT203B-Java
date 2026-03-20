package Session08.tonghop;

public class SmartHomeFacade {
    private Light l = new Light();
    private AC ac = new AC();
    private Fan f = new Fan();

    public void sleepMode() {
        System.out.println("\n--- KÍCH HOẠT CHẾ ĐỘ NGỦ ---");
        l.turnOff();
        ac.setTemp(28);
        System.out.println("Quạt: Chạy tốc độ thấp");
    }

    public void leaveHome() {
        System.out.println("\n--- KÍCH HOẠT CHẾ ĐỘ RỜI NHÀ ---");
        l.turnOff(); ac.turnOff(); f.turnOff();
    }
}
