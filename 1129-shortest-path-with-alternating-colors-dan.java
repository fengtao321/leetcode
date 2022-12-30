import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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

        // node, color
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][2];

        for (Map.Entry<Integer, HashSet<Integer>> entry : graph[0].entrySet()) {
            int node = entry.getKey();
            for (int color : entry.getValue()) {
                queue.offer(new int[] { node, color });
            }
        }

        int level = 0;
        while (!queue.isEmpty()) {

            level++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] tuple = queue.poll();
                int node = tuple[0];
                int color = tuple[1];

                if (ans[node] == -1)
                    ans[node] = level;

                if (visited[node][color])
                    continue;
                visited[node][color] = true;

                for (Map.Entry<Integer, HashSet<Integer>> entry : graph[node].entrySet()) {
                    int nextNode = entry.getKey();
                    for (int nextColor : entry.getValue()) {
                        if (!visited[nextNode][nextColor] && color != nextColor)
                            queue.offer(new int[] { nextNode, nextColor });
                    }
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