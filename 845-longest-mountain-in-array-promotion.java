class Solution {

    public int longestMountain(int[] arr) {
        int max = 0;
        int i = 0;
        while (i < arr.length) {
            int counter = 1;
            boolean isIncrease = true;
            int lastI = i;
            int j = i + 1;
            for (; j < arr.length; j++) {
                if (counter == 1 && arr[j] < arr[j - 1]) {
                    break;
                }

                if (!isIncrease && (arr[j] > arr[j - 1])) {
                    i = j - 1;
                    break;
                }

                if (arr[j] == arr[j - 1]) {
                    i = j;
                    break;
                }

                if (isIncrease) {
                    counter++;
                    if (arr[j] < arr[j - 1]) {
                        isIncrease = false;
                        max = Math.max(max, counter);
                    }
                } else {
                    counter++;
                    max = Math.max(max, counter);
                }
            }
            if (j == arr.length) {
                return max;
            }

            if (lastI == i) {
                i++;
            }
        }

        return max;
    }
}