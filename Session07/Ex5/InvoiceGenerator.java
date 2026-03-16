package Session07.Ex5;
class InvoiceGenerator {

    public void printInvoice(Order order, double finalAmount) {

        System.out.println("=== HÓA ĐƠN ===");

        System.out.println("Khách: " + order.customer.name);

        for(OrderItem item : order.items) {

            System.out.println(
                    item.product.name +
                            " - Số lượng: " + item.quantity +
                            " - Đơn giá: " + item.product.price +
                            " - Thành tiền: " + item.getTotal()
            );
        }

        System.out.println("Cần thanh toán: " + finalAmount);
    }
}