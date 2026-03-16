

# SOLID & Design Pattern – Hệ Thống Quản Lý Đơn Hàng
*Tổng hợp kiến thức từ 6 bài thực hành*

---

# 1. Giới thiệu

Trong lập trình hướng đối tượng, **SOLID** là tập hợp 5 nguyên lý giúp thiết kế hệ thống:

- Dễ bảo trì
- Dễ mở rộng
- Dễ kiểm thử
- Giảm phụ thuộc giữa các module

5 nguyên lý gồm:

| Nguyên lý | Tên |
|---|---|
| S | Single Responsibility Principle |
| O | Open/Closed Principle |
| L | Liskov Substitution Principle |
| I | Interface Segregation Principle |
| D | Dependency Inversion Principle |

Trong các bài tập trên, hệ thống **Quản lý đơn hàng** được xây dựng dần dần để áp dụng đầy đủ các nguyên lý này.

---

# 2. Single Responsibility Principle (SRP)

## 2.1 Khái niệm

**SRP – Single Responsibility Principle**

> Một class chỉ nên có **một trách nhiệm duy nhất** và **một lý do để thay đổi**.

Nếu một class làm nhiều việc khác nhau thì khi thay đổi một chức năng sẽ ảnh hưởng tới các chức năng khác.

---

## 2.2 Ví dụ vi phạm SRP

```java
class OrderProcessor {

    void calculateTotal() {}

    void processPayment() {}

    void saveOrder() {}

    void sendEmail() {}
}
```

Class này đang làm:

- Tính tiền
- Thanh toán
- Lưu đơn
- Gửi email

=> Vi phạm SRP.

---

## 2.3 Thiết kế đúng theo SRP

Tách thành các class riêng:

| Class | Trách nhiệm |
|---|---|
| Order | Quản lý dữ liệu đơn hàng |
| Product | Thông tin sản phẩm |
| Customer | Thông tin khách hàng |
| OrderCalculator | Tính tổng tiền |
| OrderRepository | Lưu đơn hàng |
| EmailService | Gửi email |

Ví dụ:

```java
class OrderCalculator {

    public double calculateTotal(Order order) {
        double total = 0;

        for(OrderItem item : order.items){
            total += item.getTotal();
        }

        return total;
    }
}
```

---

# 3. Open/Closed Principle (OCP)

## 3.1 Khái niệm

**OCP – Open/Closed Principle**

> Phần mềm nên **mở để mở rộng nhưng đóng để sửa đổi**.

Khi thêm chức năng mới, ta **không sửa code cũ** mà chỉ **thêm class mới**.

---

## 3.2 Ví dụ vi phạm OCP

```java
if(type.equals("PERCENT")){
    return total * 0.9;
}
else if(type.equals("FIXED")){
    return total - 50000;
}
```

Mỗi lần thêm loại giảm giá phải sửa code.

---

## 3.3 Giải pháp – Strategy Pattern

Tạo interface:

```java
interface DiscountStrategy {

    double applyDiscount(double total);
}
```

Implement:

```java
class PercentageDiscount implements DiscountStrategy{

    public double applyDiscount(double total){
        return total * 0.9;
    }
}
```

Thêm chiến lược mới:

```java
class HolidayDiscount implements DiscountStrategy{

    public double applyDiscount(double total){
        return total * 0.85;
    }
}
```

Không cần sửa code cũ.

---

# 4. Liskov Substitution Principle (LSP)

## 4.1 Khái niệm

**LSP – Liskov Substitution Principle**

> Đối tượng của lớp con phải có thể thay thế lớp cha mà chương trình vẫn chạy đúng.

---

Ví dụ:

```java
PaymentMethod payment = new CreditCardPayment();
```

Có thể thay bằng:

```java
payment = new MomoPayment();
```

Chương trình vẫn chạy.

---

# 5. Interface Segregation Principle (ISP)

## 5.1 Khái niệm

**ISP – Interface Segregation Principle**

> Không nên ép class phải implement những method mà nó không dùng.

---

## Ví dụ vi phạm

```java
interface PaymentMethod {

    void processCOD();

    void processCreditCard();

    void processMomo();
}
```

COD không cần CreditCard.

---

## Thiết kế đúng

```java
interface CODPayable {

    void processCOD(double amount);
}

interface CardPayable {

    void processCreditCard(double amount);
}

interface EWalletPayable {

    void processMomo(double amount);
}
```

---

# 6. Dependency Inversion Principle (DIP)

## 6.1 Khái niệm

**DIP – Dependency Inversion Principle**

> Module cấp cao không nên phụ thuộc module cấp thấp.

Cả hai nên phụ thuộc vào **abstraction (interface)**.

---

## Ví dụ sai

```java
class OrderService{

    FileOrderRepository repository = new FileOrderRepository();
}
```

---

## Thiết kế đúng

```java
interface OrderRepository{

    void save(Order order);
}
```

Implementation:

```java
class FileOrderRepository implements OrderRepository{}

class DatabaseOrderRepository implements OrderRepository{}
```

OrderService:

```java
class OrderService{

    private OrderRepository repository;

    public OrderService(OrderRepository repository){
        this.repository = repository;
    }
}
```

---

# 7. Design Pattern – Abstract Factory

## 7.1 Mục tiêu

Hệ thống bán hàng đa kênh:

- Website
- Mobile App
- POS

Mỗi kênh có:

- Discount khác nhau
- Payment khác nhau
- Notification khác nhau

---

## 7.2 Abstract Factory

```java
interface SalesChannelFactory{

    DiscountStrategy createDiscount();

    PaymentMethod createPayment();

    NotificationService createNotification();
}
```

---

## Website Factory

```java
class WebsiteFactory implements SalesChannelFactory{

    public DiscountStrategy createDiscount(){
        return new WebsiteDiscount();
    }

    public PaymentMethod createPayment(){
        return new CreditCardPayment();
    }

    public NotificationService createNotification(){
        return new EmailNotification();
    }
}
```

---

# 8. Lợi ích của SOLID

| Lợi ích | Mô tả |
|---|---|
| Maintainability | Dễ bảo trì |
| Scalability | Dễ mở rộng |
| Testability | Dễ kiểm thử |
| Flexibility | Linh hoạt |

---

# 9. Kết luận

Khi áp dụng **SOLID + Design Pattern**, hệ thống:

- Dễ mở rộng
- Ít phụ thuộc
- Code sạch hơn
- Kiến trúc rõ ràng

Ví dụ: thêm kênh bán mới

```java
class KioskFactory implements SalesChannelFactory{}
```

Không cần sửa code cũ.

---

# Tổng kết

SOLID là nền tảng của **Clean Architecture** và **Enterprise Software Development**.

Áp dụng đúng SOLID giúp hệ thống:

- phát triển lâu dài
- dễ bảo trì
- mở rộng linh hoạt

---