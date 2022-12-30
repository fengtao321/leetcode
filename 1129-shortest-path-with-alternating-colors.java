import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;

        HashMap<Integer, Deque<Node>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayDeque<>());
        }

        for (int[] edge : redEdges) {
            map.get(edge[0]).add(new Node(edge[1], 0, -1));
        }

        for (int[] edge : blueEdges) {
            map.get(edge[0]).add(new Node(edge[1], 1, -1));
        }

        Deque<Node> qDeque = new ArrayDeque<>();
        qDeque.add(new Node(0, 0, 0));// red
        qDeque.add(new Node(0, 1, 0)); // blue

        while (!qDeque.isEmpty()) {
            Node from = qDeque.poll();
            Deque<Node> list = map.get(from.id);

            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                Node to = list.poll();
                if (to.color != from.color) {
                    to.path = from.path + 1;
                    qDeque.add(to);
                    if (ans[to.id] == -1 || ans[to.id] > to.path) {
                        ans[to.id] = to.path;
                    }
                } else {
                    list.add(to);
                }
            }
        }
        return ans;
    }
}

class Node {
    int id;
    int color; // 0 is red, 1 is blue
    int path;

    Node(int id, int color, int path) {
        this.id = id;
        this.color = color;
        this.path = path;
    }
}