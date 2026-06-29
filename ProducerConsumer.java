import java.util.concurrent.*;

public class ProducerConsumer {
    public static void main(String[] args) {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    q.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int val = q.take();
                    System.out.println("Consumed: " + val);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        producer.start();
        consumer.start();
    }
}