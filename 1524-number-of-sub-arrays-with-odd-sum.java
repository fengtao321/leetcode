class Solution {
    public int numOfSubarrays(int[] arr) {
        int[] oddList = new int[arr.length + 1];
        int[] evenList = new int[arr.length + 1];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                oddList[i + 1] = oddList[i];
                evenList[i + 1] = evenList[i] + 1;
            } else {
                evenList[i + 1] = oddList[i];
                oddList[i + 1] = evenList[i] + 1;
            }
            sum += oddList[i + 1];
            sum %= 1000000007;
        }

        // System.out.println(Arrays.toString(oddList));
        // System.out.println(Arrays.toString(evenList));
        return sum;
    }
}
