package Session06.Ex2;
public class CinemaSystem {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 2);
        TicketPool roomB = new TicketPool("B", 5);

        Thread counter1 = new Thread(new BookingCounter("Quầy 1", roomA));
        Thread counter2 = new Thread(new BookingCounter("Quầy 2", roomB));

        Thread supplier = new Thread(new TicketSupplier(roomA));

        counter1.start();
        counter2.start();
        supplier.start();
    }
}