package Session06.Ex2;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool pool;

    public BookingCounter(String counterName, TicketPool pool) {
        this.counterName = counterName;
        this.pool = pool;
    }

    @Override
    public void run() {

        while (true) {

            String ticket = pool.sellTicket(counterName);

            System.out.println(counterName + " bán vé " + ticket);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}