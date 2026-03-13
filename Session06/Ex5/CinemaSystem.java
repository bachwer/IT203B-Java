package Session06.Ex5;

public class CinemaSystem {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 8);
        TicketPool roomC = new TicketPool("C", 12);

        TicketPool[] pools = {roomA, roomB, roomC};

        // 5 quầy bán vé
        for (int i = 1; i <= 5; i++) {

            Thread t = new Thread(
                    new BookingCounter("Quầy " + i, pools)
            );

            t.start();
        }

        // Timeout manager
        Thread timeoutThread =
                new Thread(new TimeoutManager(pools));

        timeoutThread.start();
    }
}