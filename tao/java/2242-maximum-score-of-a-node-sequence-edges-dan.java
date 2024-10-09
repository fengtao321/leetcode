import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int maximumScore(int[] scores, int[][] edges) {
        Map<Integer, PriorityQueue<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {

            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new PriorityQueue<>((a, b) -> scores[a] - scores[b]));
            if (!graph.containsKey(edge[1]))
                graph.put(edge[1], new PriorityQueue<>((a, b) -> scores[a] - scores[b]));

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);

            if (graph.get(edge[0]).size() > 3)
                graph.get(edge[0]).poll();

            if (graph.get(edge[1]).size() > 3)
                graph.get(edge[1]).poll();
        }

        int max = -1;

        for (int[] edge : edges) {

            // have at most 3 edges
            for (int n1 : graph.get(edge[0])) {

                // same here. at most 3
                for (int n2 : graph.get(edge[1])) {

                    // neighbor 1 and 2 cannot be the same
                    // neighbor 1 cannot form a path to edge[1]
                    // neighbor 2 cannot form a path to edge[0]
                    if (n1 != n2 && n1 != edge[1] && n2 != edge[0])
                        max = Math.max(max, scores[n1] + scores[n2] + scores[edge[0]] + scores[edge[1]]);
                }
            }
        }

        return max;
    }

}

/*
 * 
 * 
 * - loop through all edge pairs.
 * - for each pair
 * - get max from node1
 * - get max from node2
 * 
 * you almost have a max path.
 * 
 * complications #1
 * 
 * - node1 and node2 share a neighbor -> you can only pick 1
 * 
 * complication #2
 * 
 * - neighbor of node1 is an edge of node2
 * - neighbor of node2 is an edge of node1
 * 
 */