String name = input; // dữ liệu từ người dùng

String sql = "SELECT * FROM Patients WHERE name = '" + name + "'";
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);


🔥 Kịch bản bị tấn công

Người dùng nhập: ' OR '1'='1


👉 Câu SQL sau khi bị nối chuỗi: SELECT * FROM Patients WHERE name = '' OR '1'='1'


💥 Phân tích

🔍 Điều kiện WHERE: name = '' OR '1'='1'

name = '' → thường false
	'1'='1' → luôn true

👉 Toàn bộ điều kiện:

🚨 Hậu quả
•	WHERE luôn đúng
•	Trả về TOÀN BỘ dữ liệu bệnh nhân
•	Dẫn đến:
•	Lộ thông tin y tế (rất nghiêm trọng ⚠️)
•	Vi phạm bảo mật

🔥 Các kiểu tấn công khác

SELECT * FROM Patients WHERE name = '' OR 1=1 --'

✅ Hàm lọc input:

public static String sanitizeInput(String input) {
if (input == null) return "";

    return input
            .replace("'", "")
            .replace("--", "")
            .replace(";", "");
}


✅ Code sử dụng Statement (đã lọc):

public static void findPatientByName(String input) {
String cleanInput = sanitizeInput(input);

    String sql = "SELECT * FROM Patients WHERE name = '" + cleanInput + "'";

    try (Connection conn = DBContext.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("-------------------");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
# Bài tập: Tra cứu bệnh nhân & Hiểm họa rò rỉ thông tin (SQL Injection)

---

## 📌 Bối cảnh

Bác sĩ tra cứu bệnh nhân theo tên thông qua ô tìm kiếm. Tuy nhiên hệ thống bị tấn công SQL Injection khiến toàn bộ dữ liệu bệnh án bị lộ.

---

# ✅ Phần 1 – Phân tích

## ❌ Code lỗi (nối chuỗi)

```java
String name = input; // dữ liệu từ người dùng

String sql = "SELECT * FROM Patients WHERE name = '" + name + "'";
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
```

---

## 🔥 Kịch bản tấn công

Người dùng nhập:

```
' OR '1'='1
```

---

## 👉 SQL sau khi bị inject

```sql
SELECT * FROM Patients WHERE name = '' OR '1'='1'
```

---

## 💥 Phân tích điều kiện WHERE

```sql
name = '' OR '1'='1'
```

- `name = ''` → thường **false**
- `'1'='1'` → luôn **true**

👉 Kết quả:

```
false OR true = true
```

---

## 🚨 Hậu quả

- WHERE luôn đúng
- Trả về **TOÀN BỘ dữ liệu bệnh nhân**
- Dẫn đến:
  - Lộ thông tin y tế ⚠️
  - Vi phạm bảo mật nghiêm trọng

---

## 🔥 Các kiểu tấn công khác

```sql
SELECT * FROM Patients WHERE name = '' OR 1=1 --'
```

- `--` dùng để comment phần còn lại
- Điều kiện luôn đúng

---

## ✅ Kết luận phần 1

| Nguyên nhân | Hậu quả |
|------------|--------|
| Nối chuỗi SQL | Bị SQL Injection |
| Không kiểm soát input | Lộ dữ liệu |
| WHERE luôn true | Trả toàn bộ bảng |

---

# ✅ Phần 2 – Thực thi (lọc input theo yêu cầu đề)

## 🎯 Mục tiêu
- Loại bỏ ký tự nguy hiểm:
  - `'`
  - `--`
  - `;`

---

## ✅ Hàm lọc input

```java
public static String sanitizeInput(String input) {
    if (input == null) return "";

    return input
            .replace("'", "")
            .replace("--", "")
            .replace(";", "");
}
```

---

## ✅ Code dùng Statement (đã lọc)

```java
public static void findPatientByName(String input) {
    String cleanInput = sanitizeInput(input);

    String sql = "SELECT * FROM Patients WHERE name = '" + cleanInput + "'";

    try (Connection conn = DBContext.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("-------------------");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

---

# ⭐ Bonus – Cách đúng chuẩn (PreparedStatement)

```java
public static void findPatientSafe(String name) {
    String sql = "SELECT * FROM Patients WHERE name = ?";

    try (Connection conn = DBContext.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

---

# 🎯 Tổng kết

- Nối chuỗi SQL → ❌ nguy hiểm
- SQL Injection làm điều kiện luôn đúng
- Lọc input → ⚠️ chỉ tạm thời
- PreparedStatement → ✅ giải pháp chuẩn

---

# 🚀 Gợi ý nâng cao

- Tìm kiếm gần đúng (LIKE '%name%')
- Log lại truy vấn đáng ngờ
- Áp dụng phân quyền truy cập dữ liệu