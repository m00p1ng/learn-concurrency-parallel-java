import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        SequentialSum sequentialSum = new SequentialSum();

        numOfProcessors = Runtime.getRuntime().availableProcessors();

        int[] nums = new int[100000000];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        long start = System.currentTimeMillis();
        System.out.println(sequentialSum.sum(nums));
        long end = System.currentTimeMillis();
        System.out.println("SequentialSum takes " + (end - start) + "ms");



        start = System.currentTimeMillis();
        ParallelSum parallelSum = new ParallelSum(numOfProcessors);
        System.out.println(parallelSum.sum(nums));
        end = System.currentTimeMillis();
        System.out.println("ParallelSum takes " + (end - start) + "ms");
    }
}
