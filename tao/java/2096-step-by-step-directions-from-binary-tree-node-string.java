import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

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

    LinkedList<TreeNode> queue = new LinkedList<>();
    String startPath = "";
    String destPath = "";
    int startValue;
    int destValue;

    public String getDirections(TreeNode root, int startValue, int destValue) {
        String ans = "";

        this.startValue = startValue;
        this.destValue = destValue;

        getPath(root, new StringBuilder());

        int i = 0;
        while (i < startPath.length() && i < destPath.length() && startPath.charAt(i) == destPath.charAt(i)) {
            i++;
        }

        ans = destPath.substring(i, destPath.length());

        for (; i < startPath.length(); i++) {
            ans = "U" + ans;
        }

        return ans;
    }

    // we need to use StringBuilder here, to improve performance, string will be out
    // of memory
    private void getPath(TreeNode node, StringBuilder subString) {
        if (node == null || (startPath.length() > 0 && destPath.length() > 0))
            return;

        if (node.val == startValue) {
            startPath = subString.toString();
        }

        if (node.val == destValue) {
            destPath = subString.toString();
        }

        int length = subString.length();
        getPath(node.left, subString.append("L"));
        subString.deleteCharAt(length);
        getPath(node.right, subString.append("R"));
        subString.deleteCharAt(length);
    }
}
