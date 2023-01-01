import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    int[][] heights;

    public int minimumEffortPath(int[][] heights) {
        this.heights = heights;
        int m = heights.length;
        int n = heights[0].length;

        int[][] visited = init(m, n);
        visited[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.diff - b.diff);
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.i == m - 1 && node.j == n - 1) {
                return node.diff;
            }

            addNodeToQueue(node.i - 1, node.j, node, pq, visited);
            addNodeToQueue(node.i + 1, node.j, node, pq, visited);
            addNodeToQueue(node.i, node.j - 1, node, pq, visited);
            addNodeToQueue(node.i, node.j + 1, node, pq, visited);

            // printPriorityQueue(pq);
        }
        return 0;

    }

    private void addNodeToQueue(int i, int j, Node node, PriorityQueue<Node> pq, int[][] visited) {
        if (i >= 0 && i < visited.length && j >= 0 && j < visited[0].length) {
            int diff = Math.max(node.diff, Math.abs(heights[node.i][node.j] - heights[i][j]));
            if (visited[i][j] == -1 || visited[i][j] > diff) {
                pq.add(new Node(diff, i, j));
                visited[i][j] = diff;
            }
        }
    }

    private int[][] init(int m, int n) {
        int[][] visited = new int[m][n];
        for (int[] a : visited) {
            Arrays.fill(a, -1);
        }
        return visited;
    }

    private void printPriorityQueue(PriorityQueue<Node> pq) {
        System.out.println("----------------");
        pq.forEach(node -> System.out.print("[" + node.diff + "," + node.i + "," + node.j + "]" + "->"));
        System.out.println("");
    }
}

class Node {
    int i;
    int j;
    int diff;

    Node(int diff, int i, int j) {
        this.i = i;
        this.j = j;
        this.diff = diff;
    }
}