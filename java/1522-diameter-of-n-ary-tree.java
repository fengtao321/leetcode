import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    int max = 0;

    public int diameter(Node root) {
        if (root == null)
            return 0;

        dfs(root);
        return max;
    }

    private int dfs(Node node) {
        if (node.children.size() == 0)
            return 0;

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (Node child : node.children) {
            int height = 1 + dfs(child);
            heap.add(height);

            if (heap.size() == 3) {
                heap.poll();
            }
        }

        int height1 = 0;
        int height2 = 0;
        if (heap.size() == 1) {
            height2 = heap.poll();
        } else {
            height1 = heap.poll();
            height2 = heap.poll();
        }

        if (height1 + height2 > max) {
            max = height1 + height2;
        }

        return height2;
    }
}