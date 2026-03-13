package Session06.Ex1;

import Session06.Ex1.BookingCounter;
import Session06.Ex1.TicketPool;

public class DeadlockDemo {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 2);
        TicketPool roomB = new TicketPool("B", 2);

        // Quầy 1: khóa A -> B
        BookingCounter counter1 = new BookingCounter("Quầy 1", roomA, roomB);

        // Quầy 2: khóa B -> A
        BookingCounter counter2 = new BookingCounter("Quầy 2", roomB, roomA);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();
    }
}