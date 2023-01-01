import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

// if the cost of edges is always 1, a regular bfs will be faster than using a priorityQueue (since we avoid the sorting overhead)
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
            map.get(edge[0]).add(new Node(edge[1], 0));
        }

        for (int[] edge : blueEdges) {
            map.get(edge[0]).add(new Node(edge[1], 1));
        }

        Deque<Node> qDeque = new ArrayDeque<>();
        qDeque.add(new Node(0, 0));// red
        qDeque.add(new Node(0, 1)); // blue

        int level = 1;
        int qDequeL = qDeque.size();
        while (qDequeL > 0) {
            Node from = qDeque.poll();
            Deque<Node> list = map.get(from.id);

            int listSize = list.size();
            for (int i = 0; i < listSize; i++) {
                Node to = list.poll();
                if (to.color != from.color) {
                    qDeque.add(to);
                    if (ans[to.id] == -1) {
                        ans[to.id] = level;
                    }
                } else {
                    list.add(to);
                }
            }

            qDequeL--;
            if (qDequeL == 0) {
                level++;
                qDequeL = qDeque.size();
            }
        }
        return ans;
    }
}

class Node {
    int id;
    int color; // 0 is red, 1 is blue

    Node(int id, int color) {
        this.id = id;
        this.color = color;
    }
}