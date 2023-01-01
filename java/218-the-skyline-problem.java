import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeMap<Integer, Integer> leftMap = new TreeMap<>();
        TreeMap<Integer, Integer> rightMap = new TreeMap<>();
        List<Integer> nodes = new ArrayList<>();

        List<List<Integer>> ans = new ArrayList<>();

        for (int[] building : buildings) {
            int height = building[2];
            int left = building[0];
            int right = building[1];
            nodes.add(left);
            nodes.add(right);

            // most right side node
            if (rightMap.ceilingKey(left) == null) {
                if (leftMap.get(left) < height)
                    leftMap.put(left, height);
                rightMap.put(right, height);
                continue;
            }

            // if left key higher than previous height, or the left most node
            Integer preLKey = leftMap.lowerKey(left);
            if (preLKey == null || leftMap.get(preLKey) < height) {
                leftMap.put(left, height);
            }

            rightMap.remove(left);

            Integer preRKey = rightMap.lowerKey(right);
            while (preRKey != null && rightMap.get(preRKey) <= height) {
                rightMap.remove(preRKey);
                preRKey = rightMap.lowerKey(right);
            }
            rightMap.put(right, height);
        }

        // printHashMap(leftMap);
        // System.out.println("====");
        // printHashMap(rightMap);

        Collections.sort(nodes);

        int prev = -1;
        for (int node : nodes) {
            if (node == prev || (!leftMap.containsKey(node) && !rightMap.containsKey(node)))
                continue;
            prev = node;
            List<Integer> list = new ArrayList<>();
            if (leftMap.containsKey(node)) {
                list.add(node);
                list.add(leftMap.get(node));
            } else {
                Integer nextKeyLeft = leftMap.higherKey(node);
                Integer nextKeyRight = rightMap.higherKey(node);
                list.add(node);
                if ((nextKeyLeft != null && nextKeyLeft < nextKeyRight)
                        || (nextKeyLeft == null && nextKeyRight == null)) {
                    list.add(0);
                } else {
                    list.add(rightMap.get(nextKeyRight));
                }
            }
            ans.add(list);
        }

        return ans;
    }

    private void printHashMap(TreeMap<Integer, Integer> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: " + v);
            System.out.println("");
        });
    }
}