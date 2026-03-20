package Session11.Ex3;

import Session11.Ex1.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BedService {
    public static void main(String[] args) {
        updateBedStatus("Bed_001");
        updateBedStatus("Bed_999");
    }

    public static void updateBedStatus(String bedId ){
        String sql = "UPDATE Beds SET bed_status = ? WHERE bed_id = ?";


        try(Connection conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, "Đang sử dụng");
            ps.setString(2, bedId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Cập nhật thành công giường: " + bedId);
            } else {
                System.out.println("❌ Lỗi: Mã giường không tồn tại (" + bedId + ")");
            }


        } catch(Exception e){
            System.out.println("❌ Lỗi hệ thống khi cập nhật giường!");
            e.printStackTrace();
        }
    }
}
