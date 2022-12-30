import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//use set to keep visited node
class Solution {

    HashMap<Integer, HashSet<Integer>>[] graph;

    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;
        graph = new HashMap[n];
        for (int i = 0; i < n; i++)
            graph[i] = new HashMap<>();

        populateGraph(redEdges, 0);
        populateGraph(blueEdges, 1);

        // node, cost, color
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[][] visited = new boolean[n][2];

        for (Map.Entry<Integer, HashSet<Integer>> entry : graph[0].entrySet()) {
            int node = entry.getKey();
            for (int color : entry.getValue()) {
                pq.offer(new int[] { node, 1, color });
            }
        }

        while (!pq.isEmpty()) {
            int[] tuple = pq.poll();
            int node = tuple[0];
            int cost = tuple[1];
            int color = tuple[2];

            if (ans[node] == -1 || cost < ans[node])
                ans[node] = cost;

            if (visited[node][color])
                continue;
            visited[node][color] = true;

            for (Map.Entry<Integer, HashSet<Integer>> entry : graph[node].entrySet()) {
                int nextNode = entry.getKey();
                for (int nextColor : entry.getValue()) {
                    if (!visited[nextNode][nextColor] && color != nextColor)
                        pq.offer(new int[] { nextNode, cost + 1, nextColor });
                }
            }
        }

        return ans;
    }

    private void populateGraph(int[][] edges, int color) {
        for (int[] edge : edges) {
            if (!graph[edge[0]].containsKey(edge[1]))
                graph[edge[0]].put(edge[1], new HashSet<>());

            graph[edge[0]].get(edge[1]).add(color);// can have at most 1 color for each edge
        }
    }
}
