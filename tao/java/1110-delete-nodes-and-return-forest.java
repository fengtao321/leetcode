import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    List<TreeNode> list = new ArrayList<TreeNode>();
    HashSet<Integer> delSet = new HashSet<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null)
            return list;

        for (int i = 0; i < to_delete.length; i++) {
            this.delSet.add(to_delete[i]);
        }

        list.add(root);
        delNodesRecursive(root);
        return list;
    }

    private TreeNode delNodesRecursive(TreeNode root) {
        if (delSet.isEmpty() || root == null)
            return root;

        if (!delSet.contains(root.val)) {
            root.left = delNodesRecursive(root.left);
            root.right = delNodesRecursive(root.right);
            return root;
        }

        delSet.remove(root.val);
        int rootIndex = list.indexOf(root);
        if (rootIndex > -1) {
            list.remove(rootIndex);
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left != null) {
            list.add(left);
            delNodesRecursive(left);
        }

        if (right != null) {
            list.add(right);
            delNodesRecursive(right);
        }

        return null;

    }
}