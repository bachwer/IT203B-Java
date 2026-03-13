package Session06.Ex6;
import java.util.*;

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

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {

            if (!t.isSold()) {
                t.sell();
                return t;
            }
        }

        return null;
    }

    public synchronized void addTickets(int count) {

        int start = tickets.size() + 1;

        for (int i = 0; i < count; i++) {

            String id = roomName + "-" + String.format("%03d", start + i);

            tickets.add(new Ticket(id, roomName));
        }

        System.out.println("Đã thêm " + count + " vé vào phòng " + roomName);
    }

    public int soldCount() {

        int count = 0;

        for (Ticket t : tickets)
            if (t.isSold())
                count++;

        return count;
    }

    public int capacity() {
        return tickets.size();
    }

    public String getRoomName() {
        return roomName;
    }
}