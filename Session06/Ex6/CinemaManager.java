package Session06.Ex6;
import java.util.*;
import java.util.concurrent.*;

public class CinemaManager {

    private List<TicketPool> rooms = new ArrayList<>();

    private ExecutorService executor;

    private List<BookingCounter> counters = new ArrayList<>();

    public void start(int roomCount, int ticketsPerRoom, int counterCount) {

        executor = Executors.newCachedThreadPool();

        for (int i = 0; i < roomCount; i++) {

            char room = (char)('A' + i);

            rooms.add(new TicketPool("" + room, ticketsPerRoom));
        }

        TicketPool[] pools = rooms.toArray(new TicketPool[0]);

        for (int i = 1; i <= counterCount; i++) {

            BookingCounter c =
                    new BookingCounter("Quầy " + i, pools);

            counters.add(c);

            executor.submit(c);
        }

        executor.submit(new TicketSupplier(pools));

        executor.submit(new TimeoutManager());

        executor.submit(new DeadlockDetector());

        System.out.println("Hệ thống đã khởi động.");
    }

    public void pause() {

        for (BookingCounter c : counters)
            c.pause();

        System.out.println("Đã tạm dừng bán vé.");
    }

    public void resume() {

        for (BookingCounter c : counters)
            c.resume();

        System.out.println("Đã tiếp tục hoạt động.");
    }

    public void statistics() {

        System.out.println("\n=== THỐNG KÊ ===");

        int revenue = 0;

        for (TicketPool p : rooms) {

            int sold = p.soldCount();

            revenue += sold * 250000;

            System.out.println(
                    "Phòng " + p.getRoomName() +
                            ": Đã bán " + sold +
                            "/" + p.capacity()
            );
        }

        System.out.println("Doanh thu: " + revenue + " VNĐ");
    }

    public void shutdown() {

        executor.shutdownNow();

        System.out.println("Hệ thống đã dừng.");
    }
}