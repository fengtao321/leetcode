import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Deque<Pair> monoQueue = new ArrayDeque<>(); // height, indexFrom

        for (int i = 0; i < heights.length; i++) {
            Pair pair = monoQueue.peekLast();
            if (pair == null || pair.height < heights[i]) {
                monoQueue.add(new Pair(heights[i], i));
                continue;
            }

            if (pair.height == heights[i]) {
                continue;
            }

            int lastIndex = 0; // when update the pair, the index is at the beginning, that can use this height
            while (!monoQueue.isEmpty() && monoQueue.peekLast().height > heights[i]) {
                Pair lPair = monoQueue.pollLast();
                max = Math.max(max, lPair.height * (i - lPair.index));
                lastIndex = lPair.index;
            }
            monoQueue.add(new Pair(heights[i], lastIndex));
        }

        while (!monoQueue.isEmpty()) {
            Pair pair = monoQueue.pollLast();
            max = Math.max(max, pair.height * (heights.length - pair.index));
        }

        return max;
    }
}

class Pair {
    int height;
    int index;

    Pair(int height, int index) {
        this.height = height;
        this.index = index;
    }
}
