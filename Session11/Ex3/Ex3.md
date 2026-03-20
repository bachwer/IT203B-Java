✅ Phần 1 – Phân tích

🎯 executeUpdate() trả về gì?

Trong JDBC: int rowsAffected = stmt.executeUpdate(sql);
👉 Giá trị trả về (rowsAffected) là:
•	Số dòng (record) bị ảnh hưởng bởi câu lệnh SQL

⸻

🔍 Ý nghĩa cụ thể

Giá trị
Ý nghĩa
> 0  Có bản ghi được cập nhật thành công
= 0 Không có bản ghi nào bị tác động (rất quan trọng)
❗ Vấn đề của bài

Khi update: UPDATE Beds SET bed_status = 'Đang sử dụng' WHERE bed_id = 'Bed_999';

👉 Nếu Bed_999 không tồn tại:
•	SQL vẫn chạy không lỗi ❗
•	Nhưng: rowsAffected = 0


⛔ Đây chính là lý do:
•	Hệ thống “im lặng”
•	Y tá tưởng đã cập nhật thành công

⸻

💡 Cách xử lý đúng

👉 Dùng rowsAffected để kiểm tra:
if (rowsAffected == 0) {
    // Không tồn tại giường
}

✅ Kết luận phần 1
•	executeUpdate() = công cụ phát hiện lỗi logic
•	Nếu = 0 → giường không tồn tại hoặc sai điều kiện
•	Phải dùng để phản hồi cho người dùng
