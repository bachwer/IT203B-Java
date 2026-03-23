package Session12.Ex2;
public class Main {
    public static void main(String[] args) {

        // Test giá trị chuẩn
        P2.updateVitalSigns(1, 37.5, 80);

        // Test kiểu locale (vẫn OK vì Java double)
        P2.updateVitalSigns(1, 38.2, 90);
    }
}