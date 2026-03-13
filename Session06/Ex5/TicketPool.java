package Session06.Ex5;
import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int capacity) {

        this.roomName = roomName;

        for (int i = 1; i <= capacity; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    // giữ vé
    public synchronized Ticket holdTicket(boolean vip) {

        for (Ticket t : tickets) {

            if (!t.isSold() && !t.isHeld()) {

                long expiry = System.currentTimeMillis() + 5000;

                t.hold(vip, expiry);

                return t;
            }
        }

        return null;
    }

    // thanh toán
    public synchronized boolean sellHeldTicket(Ticket ticket) {

        if (ticket != null && ticket.isHeld() && !ticket.isSold()) {

            ticket.sell();
            return true;
        }

        return false;
    }

    // trả vé khi hết hạn
    public synchronized void releaseExpiredTickets() {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (t.isHeld() && !t.isSold()
                    && t.getHoldExpiryTime() < now) {

                System.out.println(
                        "TimeoutManager: Vé "
                                + t.getTicketId()
                                + " hết hạn giữ, đã trả lại kho"
                );

                t.release();
            }
        }
    }
}