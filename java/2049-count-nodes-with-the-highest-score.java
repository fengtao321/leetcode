import java.util.HashMap;

class Solution {
    public int countHighestScoreNodes(int[] parents) {
        HashMap<Integer, int[]> heightMap = new HashMap<>(); // index, [left, right]
        int total = parents.length;

        for (int i = 0; i < total; i++) {
            heightMap.put(i, new int[] { -1, -1 });
        }

        for (int i = 1; i < parents.length; i++) {
            int[] node = heightMap.get(parents[i]);
            if (node[0] == -1) {
                node[0] = i;
            } else {
                node[1] = i;
            }
        }
        int max = 0;
        int counter = 0;
        for (int i = 0; i < parents.length; i++) {
            int left = dfs(heightMap.get(i)[0], heightMap);
            int right = dfs(heightMap.get(i)[1], heightMap);
            int parent = total - left - right;
            if (left == 0 && right == 0)
                parent--;

            System.out.printf("left = %s, right = %s,parent = %s%n", left, right, parent);

            if (left == 0)
                left = 1;
            if (right == 0)
                right = 1;
            if (parent == 0)
                parent = 1;

            int multiple = left * right * parent;
            if (multiple > max) {
                max = multiple;
                counter = 1;
            } else if (multiple == max) {
                counter++;
            }

        }

        return counter;

    }

    private int dfs(int i, HashMap<Integer, int[]> heightMap) {
        if (i == -1)
            return 0;

        int left = dfs(heightMap.get(i)[0], heightMap);
        int right = dfs(heightMap.get(i)[1], heightMap);

        return left + right + 1;
    }
}
