package Session06.Ex5;
import java.util.Random;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool[] pools;

    private Random random = new Random();

    public BookingCounter(String name, TicketPool[] pools) {
        this.counterName = name;
        this.pools = pools;
    }

    @Override
    public void run() {

        while (true) {

            TicketPool pool = pools[random.nextInt(pools.length)];

            boolean vip = random.nextInt(5) == 0; // 20% VIP

            Ticket ticket = pool.holdTicket(vip);

            if (ticket != null) {

                System.out.println(counterName +
                        ": Đã giữ vé "
                        + ticket.getTicketId()
                        + (vip ? " (VIP)" : "")
                        + ". Vui lòng thanh toán trong 5s");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    return;
                }

                boolean paid = pool.sellHeldTicket(ticket);

                if (paid) {

                    System.out.println(counterName +
                            ": Thanh toán thành công vé "
                            + ticket.getTicketId());

                } else {

                    System.out.println(counterName +
                            ": Thanh toán thất bại "
                            + ticket.getTicketId());
                }

            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}