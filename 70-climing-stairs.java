import java.util.HashMap;

class Solution {
    HashMap<Integer, Integer> sourceMap = new HashMap<>();

    public int climbStairs(int n) {
        if (sourceMap.get(n) != null) {
            return sourceMap.get(n);
        }

        if (n < 3) {
            sourceMap.put(n, n);
            return n;
        }

        int numOfWays = climbStairs(n - 1) + climbStairs(n - 2);
        sourceMap.put(n, numOfWays);
        return numOfWays;
    }
}