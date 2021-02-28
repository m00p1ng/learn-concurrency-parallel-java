public class MergeSort {
    private int[] nums;
    private int[] tempArray;

    public MergeSort(int[] nums) {
        this.nums = nums;
        tempArray = new int[nums.length];
    }

    public void parallelMergeSort(int low, int high, int numOfThread) {
        if (numOfThread <= 1) {
            mergeSort(low, high);
            return;
        }

        int middleIndex = (low + high) / 2;

        Thread leftSorter = mergeSortParallel(low, middleIndex, numOfThread);
        Thread rightSorter = mergeSortParallel(middleIndex + 1, high, numOfThread);
        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        merge(low, middleIndex, high);
    }

    private Thread mergeSortParallel(int low, int high, int numOfThread) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads / 2);
            }
        };
    }

    public void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }

        int middle = (low + high) / 2;

        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);
    }

    public void showResult() {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i] + " ");
        }
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = nums[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while ((i <= middle) && (j <= high)) {
            if (tempArray[i] <= tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }

            k++;
        }

        while (i <= middle) {
            nums[k] = tempArray[i];
            k++;
            i++;
        }

        while (j <= high) {
            nums[k] = tempArray[j];
            k++;
            j++;
        }
    }

    public boolean isSorted() {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
