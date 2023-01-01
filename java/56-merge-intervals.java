import java.util.ArrayList;

class Solution {
    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> list = new ArrayList<>();
        int[] start = new int[10001]; //// [[1,4],[0,0]] won't work with one list
        int[] end = new int[10001];

        for (int[] interval : intervals) {
            start[interval[0]] += 1;
            end[interval[1]] -= 1;
        }

        int counter = 0;
        int left = -1;
        for (int i = 0; i < 10001; i++) {
            if (start[i] == 0 && end[i] == 0)
                continue;

            counter = counter + start[i] + end[i];

            if (start[i] > 0 && left < 0) {
                left = i;
            }

            if (counter == 0 && left >= 0) {
                list.add(new int[] { left, i });
                left = -1;
            }
        }

        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}