package Session06.Ex2;

import java.util.LinkedList;
import java.util.Queue;


public class TicketPool {

    private String roomName;
    private Queue<String> tickets = new LinkedList<>();
    private int nextTicketNumber = 1;

    public TicketPool(String roomName, int initialTickets) {
        this.roomName = roomName;

        for (int i = 0; i < initialTickets; i++) {
            tickets.add(generateTicket());
        }
    }

    private String generateTicket() {
        return roomName + "-" + String.format("%03d", nextTicketNumber++);
    }

    // Bán vé
    public synchronized String sellTicket(String counterName) {

        while (tickets.isEmpty()) {
            try {
                System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String ticket = tickets.poll();
        return ticket;
    }

    // Nhà cung cấp thêm vé
    public synchronized void addTickets(int count) {

        for (int i = 0; i < count; i++) {
            tickets.add(generateTicket());
        }

        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);

        notifyAll(); // đánh thức tất cả quầy đang chờ
    }
}