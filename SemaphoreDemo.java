import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    static Semaphore sem = new Semaphore(2);

    static class Person extends Thread {

        Person(String name) {
            super(name);
        }

        public void run() {
            try {
                System.out.println(getName() + " is waiting.");

                sem.acquire();

                System.out.println(getName() + " entered.");

                Thread.sleep(3000);

                System.out.println(getName() + " leaving.");

                sem.release();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {

        Person p1 = new Person("Person-1");
        Person p2 = new Person("Person-2");
        Person p3 = new Person("Person-3");
        Person p4 = new Person("Person-4");

        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}