package Session06.Ex2;
public class TicketSupplier implements Runnable {

    private TicketPool pool;

    public TicketSupplier(TicketPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {

        try {

            Thread.sleep(5000);

            pool.addTickets(3);

            Thread.sleep(5000);

            pool.addTickets(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}