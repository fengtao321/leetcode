import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

// class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;

//     TreeNode() {
//     }

//     TreeNode(int val) {
//         this.val = val;
//     }

//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

class Solution {
    LinkedList<String> dir = new LinkedList<String>();

    public String getDirections(TreeNode root, int startValue, int destValue) {

        LinkedList<TreeNode> startPathList = new LinkedList<TreeNode>();
        startPathList = findPath(startPathList, root, startValue);
        // startPathList.forEach(node -> System.out.println(node.val));
        dir = new LinkedList<String>();
        LinkedList<TreeNode> destPathList = new LinkedList<TreeNode>();
        destPathList = findPath(destPathList, root, destValue);
        // destPathList.forEach(node -> System.out.println(node.val));
        String result = printPath(startPathList, destPathList);
        return result;
    }

    public String printPath(LinkedList<TreeNode> startPathList, LinkedList<TreeNode> destPathList) {
        StringBuilder ans = new StringBuilder();
        int index = 0;

        while (index < startPathList.size() & index < destPathList.size()
                && startPathList.get(index).val == destPathList.get(index).val) {
            index++;
        }

        for (int i = index; i < startPathList.size(); i++) {
            ans = ans.append("U");
        }

        for (int i = index; i < destPathList.size(); i++) {
            ans = ans.append(dir.get(i));
        }

        return ans.toString();
    }

    public LinkedList<TreeNode> findPath(LinkedList<TreeNode> pathList, TreeNode root, int value) {
        if (root == null)
            return null;
        pathList.add(root);
        if (root.val == value) {
            return pathList;
        } else {
            dir.add("L");
            List<TreeNode> leftList = findPath(pathList, root.left, value);
            if (leftList != null)
                return pathList;
            dir.pollLast();
            dir.add("R");
            List<TreeNode> rightList = findPath(pathList, root.right, value);
            if (rightList != null)
                return pathList;
            dir.pollLast();
        }
        pathList.pollLast();
        return null;
    }

}