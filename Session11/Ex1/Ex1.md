Phần 1 – Phân tích

Việc khởi tạo kết nối liên tục nhưng không đóng (close) hoặc không quản lý tập trung là cực kỳ nguy hiểm, đặc biệt trong hệ thống y tế cần chạy 24/7, vì các lý do sau:

1. Rò rỉ tài nguyên (Resource Leak)
   •	Mỗi kết nối DB tiêu tốn:
   •	RAM
   •	Socket mạng
   •	Thread
   •	Nếu không đóng → số lượng connection tăng dần → cạn kiệt tài nguyên

👉 Hệ quả:
•	Server DB không nhận thêm kết nối mới
•	Ứng dụng bị treo sau vài tiếng (đúng với mô tả đề bài)

⸻

2. Lỗi "Communications link failure"
   •	Xảy ra khi:
   •	Connection bị timeout
   •	Kết nối bị giữ quá lâu mà không dùng
   •	Khi app cố dùng lại connection “chết” → lỗi xuất hiện

👉 Nguyên nhân gốc:
•	Không đóng → không tạo connection mới đúng cách
•	Không có cơ chế reconnect

⸻

3. Không phù hợp với hệ thống 24/7 (bệnh viện)

Hệ thống bệnh án yêu cầu:
•	Luôn hoạt động ổn định
•	Không downtime
•	Không mất dữ liệu

👉 Nếu connection leak:
•	Bác sĩ không truy cập được hồ sơ
•	Nguy cơ ảnh hưởng đến điều trị (rất nghiêm trọng)

⸻

4. Khó bảo trì & debug
   •	Mỗi nơi tự mở connection → khó kiểm soát
   •	Không biết connection nào đang mở
   •	Debug rất khó khi lỗi xảy ra sau thời gian dài

⸻

5. Không tận dụng Connection Pool
   •	Các DB (MySQL, SQL Server…) đều hỗ trợ connection pooling
   •	Nếu không đóng connection:
   •	Pool không tái sử dụng được
   •	Hiệu năng giảm mạnh

⸻

✅ Kết luận phần 1:

Việc không đóng connection sẽ dẫn đến:
•	Rò rỉ tài nguyên
•	Treo hệ thống
•	Lỗi kết nối
•	Nguy cơ gián đoạn hệ thống y tế (critical system)

⸻

Phần 2 – Thực thi

🎯 Yêu cầu:
•	Dùng hằng số cấu hình
•	Đảm bảo mọi truy vấn đều đóng connection trong finally