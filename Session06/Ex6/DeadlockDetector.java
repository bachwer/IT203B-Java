package Session06.Ex6;
import java.lang.management.*;

public class DeadlockDetector implements Runnable {

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                return;
            }

            ThreadMXBean bean = ManagementFactory.getThreadMXBean();

            long[] ids = bean.findDeadlockedThreads();

            if (ids != null) {

                System.out.println("Phát hiện DEADLOCK!");

                ThreadInfo[] infos = bean.getThreadInfo(ids);

                for (ThreadInfo info : infos)
                    System.out.println(info);
            }
        }
    }
}