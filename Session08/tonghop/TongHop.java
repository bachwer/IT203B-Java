package Session08.tonghop;

import java.util.*;

public class TongHop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HardwareConnection.getInstance();

        SmartHomeFacade facade = new SmartHomeFacade();
        TempSubject sensorSystem = new TempSubject();
        Fan smartFan = new Fan();
        sensorSystem.attach(smartFan);

        while (true) {
            System.out.println("\n=== SMART HOME CONTROL ===");
            System.out.println("1. Bật/Tắt đèn (Command)");
            System.out.println("2. Điều chỉnh nhiệt độ (Observer)");
            System.out.println("3. Xem nhiệt độ hiện tại (Adapter)");
            System.out.println("4. Chế độ ngủ (Facade)");
            System.out.println("5. Chế độ rời nhà (Facade)");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 0) break;

            switch (choice) {
                case 1:
                    Command on = new LightOnCommand(new Light());
                    on.execute();
                    break;
                case 2:
                    System.out.print("Nhập nhiệt độ mới: ");
                    int t = Integer.parseInt(sc.nextLine());
                    sensorSystem.notifyAll(t);
                    break;
                case 3:
                    TempSensor adapter = new ThermometerAdapter(new OldThermometer());
                    System.out.println("Nhiệt độ từ cảm biến cũ: " + String.format("%.1f", adapter.getCelsius()) + "°C");
                    break;
                case 4:
                    facade.sleepMode();
                    break;
                case 5:
                    facade.leaveHome();
                    break;
            }
        }
    }
}
