package Session06.Ex6;
public class TicketSupplier implements Runnable {

    private TicketPool[] pools;

    public TicketSupplier(TicketPool[] pools) {
        this.pools = pools;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                return;
            }

            for (TicketPool p : pools)
                p.addTickets(2);
        }
    }
}