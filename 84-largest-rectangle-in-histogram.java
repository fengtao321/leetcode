import java.util.LinkedList;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        LinkedList<int[]> dq = new LinkedList<>(); // height, indexFrom

        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];

            int[] top = dq.peekLast();
            if (top == null || top[0] < height) {
                dq.add(new int[] { height, i });
                continue;
            }

            if (top[0] == height)
                continue;

            int length = dq.size();
            int lastIndex = 0;
            for (int j = length - 1; j >= 0 && dq.peekLast()[0] > height; j--) {
                int[] list = dq.pollLast();
                lastIndex = list[1];
                max = Math.max(max, list[0] * (i - lastIndex));
            }

            dq.add(new int[] { height, lastIndex });
        }

        while (!dq.isEmpty()) {
            int[] list = dq.pollLast();
            max = Math.max(max, list[0] * (heights.length - list[1]));
        }

        return max;
    }

    private void printLinkedList(LinkedList<int[]> dq) {
        System.out.println("=============");
        dq.forEach(list -> System.out.println(list[0] + "->" + list[1]));

    }
}