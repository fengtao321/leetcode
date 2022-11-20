import java.util.HashMap;

class Solution {
    HashMap<Integer, Integer> sourceMap = new HashMap<>();

    public int minCostClimbingStairs(int[] cost) {
        sourceMap.put(cost.length - 1, cost[cost.length - 1]);
        sourceMap.put(cost.length - 2, cost[cost.length - 2]);

        for (int i = cost.length - 3; i >= 0; i--) {
            sourceMap.put(i, Math.min(sourceMap.get(i + 1), sourceMap.get(i + 2)) + cost[i]);
        }
        return Math.min(sourceMap.get(1), sourceMap.get(0));
    }
}