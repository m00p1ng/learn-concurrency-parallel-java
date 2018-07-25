import java.util.ArrayList;
import java.util.List;

class Processor {

    private List<Integer> list = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
           while(true) {
               if(list.size() == LIMIT) {
                   System.out.println("Waiting for removing item from the list");
                   lock.wait();
               } else {
                   System.out.println("Adding: " + value);
                   list.add(value);
                   value++;
                   lock.notify();
               }

               Thread.sleep(500);
           }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while(true) {
                if(list.size() == BOTTOM) {
                    System.out.println("Waiting for adding items to the list...");
                    lock.wait();
                } else {
                    System.out.println("Removed: " + list.remove(--value));
                    lock.notify();
                }

                Thread.sleep(500);
            }
        }
    }
}

public class Main {

    static Processor processor = new Processor();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finished...");
    }
}
