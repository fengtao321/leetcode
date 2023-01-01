import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class Solution {
    public int[][] merge(int[][] intervals) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int[] interval : intervals) {
            treeMap.put(interval[0], treeMap.getOrDefault(interval[0], 0) + 1);
            treeMap.put(interval[1], treeMap.getOrDefault(interval[1], 0) - 1);
        }

        List<int[]> list = new ArrayList<>();
        int sum = 0;
        int start = -1;
        for (int key : treeMap.keySet()) {
            sum += treeMap.get(key);

            if (sum == 0 && start < 0) {
                list.add(new int[] { key, key });
                continue;
            }

            if (sum > 0 && start < 0) {
                start = key;
                continue;
            }

            if (sum == 0) {
                list.add(new int[] { start, key });
                start = -1;
            }
        }

        return list.toArray(new int[list.size()][2]);
    }
}