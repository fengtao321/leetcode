import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
    int fromCity = -1;
    int min = Integer.MAX_VALUE;
    int[][] dp;
    int[][] bestNextCity;

    HashMap<Integer, List<Integer>> roadMap = new HashMap<>();
    HashMap<Integer, String> nameTranslator = new HashMap<>();

    String[] targetPath;

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        this.targetPath = targetPath;

        dp = initMatrix(n, targetPath.length);
        bestNextCity = initMatrix(n, targetPath.length);

        for (int i = 0; i < names.length; i++) {
            nameTranslator.put(i, names[i]);
            roadMap.put(i, new ArrayList<Integer>());
        }

        for (int[] road : roads) {
            roadMap.get(road[0]).add(road[1]);
            roadMap.get(road[1]).add(road[0]);
        }

        for (int i = 0; i < n; i++) {
            dfs(i, 0);

            if (dp[i][0] < min) {
                min = dp[i][0];
                fromCity = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < targetPath.length; i++) {
            ans.add(fromCity);
            fromCity = bestNextCity[fromCity][i];
        }
        return ans;
    }

    private int dfs(int from, int counter) {
        if (counter == targetPath.length) {
            return 0;
        }

        if (dp[from][counter] > -1) {
            return dp[from][counter];
        }

        int dist = targetPath[counter].equals(nameTranslator.get(from)) ? 0 : 1;

        for (int to : roadMap.get(from)) {
            int tempDist = dist + dfs(to, counter + 1);
            if (dp[from][counter] == -1 || tempDist < dp[from][counter]) {
                dp[from][counter] = tempDist;
                bestNextCity[from][counter] = to;
            }
        }

        return dp[from][counter];
    }

    private int[][] initMatrix(int n, int m) {
        int[][] matrix = new int[n][m];
        for (int[] a : matrix) {
            Arrays.fill(a, -1);
        }
        return matrix;
    }

}