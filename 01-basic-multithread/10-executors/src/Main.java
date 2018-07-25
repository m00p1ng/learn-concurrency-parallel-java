import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();


        for(int i = 0; i < 5; i++) {
            executorService.submit(new Worker());
        }
    }
}

class Worker implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
