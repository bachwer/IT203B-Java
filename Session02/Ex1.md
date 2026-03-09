1. Kiểm tra xem một User có phải là Admin hay không
   Functional Interface phù hợp: Predicate<User>
- Lý do:
  •	Predicate<T> dùng để kiểm tra một điều kiện trên một đối tượng.
  •	Nhận 1 tham số đầu vào và trả về boolean.
  •	Phù hợp cho các thao tác filter, validate, check condition.
2. Chuyển đổi một đối tượng User thành chuỗi String chứa username
   Functional Interface phù hợp: Function<User, String>
Lý do:
   •	Function<T, R> dùng khi cần chuyển đổi dữ liệu từ kiểu này sang kiểu khác.
   •	Nhận 1 tham số đầu vào và trả về một giá trị.

3. In thông tin chi tiết của User ra màn hình Console
   Functional Interface phù hợp: Consumer<User>

Lý do:
•	Consumer<T> dùng khi nhận một đối tượng và thực hiện hành động với nó.
•	Không trả về giá trị (void).
•	Thường dùng cho logging, printing, update state.

4. Khởi tạo một đối tượng User mới với các giá trị mặc định
  Functional Interface phù hợp: Supplier<User>
Lý do:
•	Supplier<T> dùng khi không có tham số đầu vào nhưng trả về một đối tượng.
•	Thường dùng để tạo object, lazy initialization, factory method.


