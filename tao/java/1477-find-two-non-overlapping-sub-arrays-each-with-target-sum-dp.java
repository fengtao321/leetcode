class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int sum = arr[0];
        int left = 0;
        int right = 0;

        int[] dp = new int[arr.length];

        while (left < arr.length && right < arr.length) {
            if (sum == target) {
                dp[left] = right - left + 1;
            }

            if (sum < target) {
                if (right == arr.length - 1)
                    break;
                right++;
                sum = sum + arr[right];
            } else {
                if (left == arr.length - 1)
                    break;

                left++;
                if (right <= left) {
                    right = left;
                    sum = arr[left];
                } else {
                    sum = sum - arr[left - 1];
                }

            }
        }

        // System.out.println(Arrays.toString(dp));

        // create 2 arrays, one is from left to right, the other is from right to left.
        // For example: [1,1,1,2,2,2,4,4], we get dp as [0, 4,0,3,0,2,0,0], from the dp
        // we create leftArray [2, 2, 2, 2, 2, 0, 0, 0]
        // rightArray [0, 0, 0, 0, 4, 3, 2, 2]

        // which means at index 0, we can use the min sub array[4,4] from right, but
        // none from left
        // at index 4, we can use the min sub array[4,4] from right of 4, amd
        // min sub array [2,2,2,2] from left side,

        int[] start = new int[arr.length];
        for (int i = 0; i < dp.length - 1; i++) {

            int index = dp[i] + i;
            if (index < dp.length && dp[i] > 0 && (start[index] > dp[i] || start[index] == 0)) {
                start[index] = dp[i];
            }

            if (i > 0 && start[i - 1] > 0 && (start[i] == 0 || start[i] > start[i - 1])) {
                start[i] = start[i - 1];
            }
        }

        int[] end = new int[arr.length];
        for (int i = dp.length - 1; i >= 0; i--) {

            if (i - 1 >= 0 && dp[i] > 0)
                end[i] = dp[i];

            if (i < dp.length - 1 && end[i + 1] > 0 && (end[i] == 0 || end[i] > end[i + 1])) {
                end[i] = end[i + 1];
            }
        }

        // System.out.println(Arrays.toString(start));
        // System.out.println(Arrays.toString(end));

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if (start[i] > 0 && end[i] > 0) {
                ans = Math.min(ans, start[i] + end[i]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}