import java.util.Random;

public class Main {
    public static Random random = new Random();

    public static void main(String[] args) throws Throwable {
        int numOfThreads  = Runtime.getRuntime().availableProcessors();

        System.out.println("Number of threads/cores: " + numOfThreads);
        System.out.println("");

        int[] numbers1 = createRandomArray(100000000);
        int[] numbers2 = new int[numbers1.length];

        for(int i=0;i<numbers1.length;i++)
            numbers2[i] = numbers1[i];

        MergeSort parallelSorter = new MergeSort(numbers1);

        // run the algorithm and time how long it takes
        long startTime1 = System.currentTimeMillis();
        parallelSorter.parallelMergeSort(0, numbers1.length-1, numOfThreads);
        long endTime1 = System.currentTimeMillis();

        System.out.printf("Time taken for 10 000 000 elements parallel =>  %6d ms \n", endTime1 - startTime1);
        System.out.println("");

        startTime1 = System.currentTimeMillis();
        MergeSort sequentisalSorted = new MergeSort(numbers2);
        sequentisalSorted.mergeSort(0,numbers2.length-1);
        endTime1 = System.currentTimeMillis();

        System.out.printf("Time taken for 10 000 000 elements sequential =>  %6d ms \n", endTime1 - startTime1);
        System.out.println("");

    }

    public static int[] createRandomArray(int n) {

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(10000);
        }

        return a;
    }
}


