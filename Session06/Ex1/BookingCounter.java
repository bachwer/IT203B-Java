package Session06.Ex1;

public class BookingCounter implements Runnable {

    private String counterName;
    private TicketPool firstRoom;
    private TicketPool secondRoom;

    public BookingCounter(String counterName, TicketPool firstRoom, TicketPool secondRoom) {
        this.counterName = counterName;
        this.firstRoom = firstRoom;
        this.secondRoom = secondRoom;
    }

    public void sellCombo() {

        synchronized (firstRoom) {

            String ticket1 = firstRoom.takeTicket();

            if (ticket1 != null)
                System.out.println(counterName + ": Đã lấy vé " + ticket1);

            try { Thread.sleep(100); } catch (Exception e) {}

            System.out.println(counterName + ": Đang chờ vé " + secondRoom.getRoomName() + "...");

            synchronized (secondRoom) {

                String ticket2 = secondRoom.takeTicket();

                if (ticket1 != null && ticket2 != null) {
                    System.out.println(counterName + " bán combo thành công: " + ticket1 + " & " + ticket2);
                } else {

                    if (ticket1 != null) firstRoom.returnTicket(ticket1);
                    if (ticket2 != null) secondRoom.returnTicket(ticket2);

                    System.out.println(counterName + ": Bán combo thất bại");
                }
            }
        }
    }

    @Override
    public void run() {
        sellCombo();
    }
}