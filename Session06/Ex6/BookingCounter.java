package Session06.Ex6;
import java.util.Random;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool[] pools;

    private volatile boolean paused = false;

    private Random random = new Random();

    public BookingCounter(String name, TicketPool[] pools) {
        this.name = name;
        this.pools = pools;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    @Override
    public void run() {

        System.out.println(name + " bắt đầu bán vé...");

        while (true) {

            if (paused) {
                try { Thread.sleep(500); } catch (Exception e) {}
                continue;
            }

            TicketPool pool = pools[random.nextInt(pools.length)];

            Ticket t = pool.sellTicket();

            if (t != null) {

                System.out.println(name + " đã bán vé " + t.getId());

            }

            try {
                Thread.sleep(300);
            } catch (Exception e) {
                return;
            }
        }
    }
}