package Session06.Ex6;
public class TimeoutManager implements Runnable {

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return;
            }

            // kiểm tra vé giữ chỗ
        }
    }
}