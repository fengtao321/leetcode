class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 1)
            return;

        int start = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            int x = nums[i + 1];
            int y = nums[i];

            if (y < x) {
                start = i;
                break;
            }
        }

        getMinNum(start, nums);
        quickSorting(start + 1, nums.length - 1, nums);
    }

    private void getMinNum(int start, int[] nums) {
        int min = Integer.MAX_VALUE;
        int index = start;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] > nums[start] && nums[i] < min) {
                min = nums[i];
                index = i;
            }
        }

        nums[index] = nums[start];
        nums[start] = min;
    }

    private void quickSorting(int low, int high, int[] nums) {
        if (low >= high)
            return;

        int pi = partition(low, high, nums);

        quickSorting(low, pi - 1, nums);
        quickSorting(pi + 1, high, nums);
    }

    private int partition(int low, int high, int[] nums) {
        int last = high;
        int pivot = low;

        while (pivot < last - 1) {
            if (nums[pivot] < nums[pivot + 1]) {
                swap(last, pivot + 1, nums);
                last--;
            } else {
                swap(pivot, pivot + 1, nums);
                pivot++;
            }
        }
        return pivot;
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}