import java.util.concurrent.locks.*;

public class ReaderWriterDemo {

    static int data = 0;
    static ReadWriteLock rw = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        Runnable reader = () -> {
            try {
                rw.readLock().lock();
                System.out.println(Thread.currentThread().getName() + " reading data = " + data);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                rw.readLock().unlock();
            }
        };

        Runnable writer = () -> {
            try {
                rw.writeLock().lock();
                data++;
                System.out.println(Thread.currentThread().getName() + " writing data = " + data);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                rw.writeLock().unlock();
            }
        };

        Thread r1 = new Thread(reader, "Reader-1");
        Thread r2 = new Thread(reader, "Reader-2");
        Thread w1 = new Thread(writer, "Writer-1");
        Thread r3 = new Thread(reader, "Reader-3");

        r1.start();
        r2.start();
        w1.start();
        r3.start();
    }
}