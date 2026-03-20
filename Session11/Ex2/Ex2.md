✅ Phần 1 – Phân tích

❌ Vì sao dùng if là sai?

Giả sử code lỗi dạng:

if (rs.next()) {
System.out.println(rs.getString("name"));
}


Vấn đề:
•	if chỉ kiểm tra 1 lần
•	Chỉ xử lý 1 dòng đầu tiên
•	Không lặp qua các bản ghi còn lại

⛔ Vì vậy:
•	Chỉ in thuốc đầu tiên
•	Các thuốc sau → bị bỏ qua


🔄 Cơ chế hoạt động của ResultSet.next()

📌 Ban đầu:
•	Con trỏ nằm trước dòng đầu tiên

⸻

👉 Khi gọi rs.next() lần 1:
•	Con trỏ → dòng 1
•	Trả về true

⸻

👉 Khi gọi tiếp:
•	Con trỏ → dòng 2, 3, 4…

⸻

👉 Khi hết dữ liệu:
•	rs.next() → false

⸻

🔥 Minh họa







# Bài tập: Truy xuất danh mục thuốc (Pharmacy Catalogue)

---

## ✅ Phần 1 – Phân tích

### ❌ Vì sao dùng `if` là sai?

Giả sử code lỗi dạng:

```java
if (rs.next()) {
    System.out.println(rs.getString("name"));
}
```

### 📌 Vấn đề:
- `if` chỉ kiểm tra **1 lần duy nhất**
- Chỉ xử lý **1 dòng đầu tiên**
- Không lặp qua các bản ghi còn lại

⛔ Vì vậy:
- Chỉ in **thuốc đầu tiên**
- Các thuốc sau → bị bỏ qua

---

## 🔄 Cơ chế hoạt động của `ResultSet.next()`

### 📌 Ban đầu:
- Con trỏ nằm **trước dòng đầu tiên**

---

### 👉 Khi gọi `rs.next()` lần 1:
- Con trỏ → dòng 1
- Trả về `true`

---

### 👉 Khi gọi tiếp:
- Con trỏ → dòng 2, 3, 4…

---

### 👉 Khi hết dữ liệu:
- `rs.next()` → `false`

---

## 🔥 Minh họa

Giả sử bảng `Medicines`:

| id | name         | quantity |
|----|--------------|----------|
| 1  | Paracetamol | 100      |
| 2  | Aspirin     | 50       |
| 3  | Vitamin C   | 200      |

---

### ❌ Dùng `if`:

```java
if (rs.next()) {
    System.out.println(rs.getString("name"));
}
```

👉 Kết quả:
```
Paracetamol
```

---

### ✅ Dùng `while`:

```java
while (rs.next()) {
    System.out.println(rs.getString("name"));
}
```

👉 Kết quả:
```
Paracetamol
Aspirin
Vitamin C
```

---

## ⚠️ Trường hợp bảng rỗng

- `rs.next()` trả về `false` ngay từ đầu

### ❌ Nếu dùng `if`:
- Không in gì → dễ gây nhầm là lỗi

### ✅ Nếu dùng `while`:
- Không chạy vòng lặp → an toàn

---

## ✅ Kết luận phần 1

| Tiêu chí | if | while |
|----------|----|------|
| Số dòng xử lý | 1 | Tất cả |
| In danh sách | ❌ | ✅ |
| An toàn khi rỗng | ❌ | ✅ |

---

# ✅ Phần 2 – Thực thi

## 🎯 Yêu cầu
- In danh sách thuốc
- Gồm:
  - Tên thuốc
  - Số lượng tồn kho

---

## ✅ Code đúng chuẩn

```java
public static void getMedicines() {
    String query = "SELECT name, quantity FROM Medicines";

    try (Connection conn = DBContext.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        System.out.println("=== DANH MỤC THUỐC ===");

        while (rs.next()) {
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");

            System.out.println("Tên thuốc: " + name);
            System.out.println("Số lượng: " + quantity);
            System.out.println("----------------------");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

---

## ⭐ Bonus – Xử lý khi không có dữ liệu

```java
boolean isEmpty = true;

while (rs.next()) {
    isEmpty = false;
    // xử lý dữ liệu
}

if (isEmpty) {
    System.out.println("Kho thuốc đang trống!");
}
```

---

## 🎯 Tổng kết

- `if` → chỉ xử lý 1 dòng ❌  
- `while` → duyệt toàn bộ dữ liệu ✅  
- `ResultSet.next()`:
  - Mỗi lần gọi → di chuyển con trỏ xuống 1 dòng

---

## 🚀 Gợi ý nâng cao

- Dùng `PreparedStatement` để tránh SQL Injection
- Thêm chức năng tìm thuốc theo tên
- Kết hợp Connection Pool (HikariCP) cho hệ thống lớn