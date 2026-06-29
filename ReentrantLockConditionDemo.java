import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionDemo {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static boolean ready = false;

    public static void main(String[] args) {

        Thread worker = new Thread(() -> {
            lock.lock();
            try {
                while (!ready) {
                    System.out.println("Worker is waiting...");
                    condition.await();
                }

                System.out.println("Worker started working.");

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                lock.unlock();
            }
        });

        Thread manager = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }

            lock.lock();
            try {
                ready = true;
                System.out.println("Manager gives signal.");
                condition.signal();
            } finally {
                lock.unlock();
            }
        });

        worker.start();
        manager.start();
    }
}