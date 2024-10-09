import java.util.List;

class Solution {
    public int findMinDifference(List<String> timePoints) {
        int size = 1440; // 24*60;
        int[] array = new int[size];
        int max = 0;
        for (String time : timePoints) {
            int hours = Integer.parseInt(time.substring(0, 2));
            int minutes = Integer.parseInt(time.substring(3)) + hours * 60;
            if (array[minutes] > 0)
                return 0;
            array[minutes] = 1;
            max = Math.max(max, minutes);
        }

        int i = 0;
        while (i < size && array[i] == 0) {
            i++;
        }

        int min = size - max + i;
        int last = i++;
        for (; i < size; i++) {
            if (array[i] > 0) {
                min = Math.min(min, i - last);
                last = i;
            }
        }

        return min;
    }
}