import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {
    int[][] primGrid;

    public int minCostConnectPoints(int[][] points) {
        int size = points.length;
        primGrid = new int[size][size];
        int[] dist = new int[size];
        Arrays.fill(dist, -1);
        dist[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // to, weight
        HashSet<Integer> visited = new HashSet<>();
        // init
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                primGrid[i][j] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            }
        }

        prim(0, pq, visited);
        int ans = 0;
        while (!pq.isEmpty()) {
            int[] to = pq.poll();

            if (visited.contains(to[0]))
                continue;

            ans += to[1];
            prim(to[0], pq, visited);

            if (visited.size() == size) {
                return ans;
            }
        }
        return ans;

    }

    private void prim(int i, PriorityQueue<int[]> pq, HashSet<Integer> visited) {
        visited.add(i);

        for (int j = 0; j < primGrid[i].length; j++) {
            if (visited.contains(j))
                continue;
            pq.add(new int[] { j, primGrid[i][j] });
        }
    }
}
