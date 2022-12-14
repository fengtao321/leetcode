import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<Building> list = new ArrayList<>();
        for (int[] building : buildings) {
            list.add(new Building(building[0], -building[2]));
            list.add(new Building(building[1], building[2]));
        }
        Collections.sort(list);

        List<List<Integer>> ans = new ArrayList<>();
        Queue<Integer> heightHeap = new PriorityQueue<>(Collections.reverseOrder());
        heightHeap.add(0); // add the ground, if all buildings are pop out, still get 0;

        int lastHeight = 0;
        int currHeight = 0;
        for (Building building : list) {
            // left point
            if (building.height < 0) {
                heightHeap.add(-building.height);
            } else {
                heightHeap.remove(building.height);
            }

            currHeight = heightHeap.peek();
            if (currHeight != lastHeight) {
                ans.add(Arrays.asList(building.position, currHeight));
                lastHeight = currHeight;
            }
        }

        return ans;
    }
}

class Building implements Comparable<Building> {

    int position;
    int height;

    Building(int position, int height) {
        this.position = position;
        this.height = height;
    }

    @Override
    public int compareTo(Building that) {
        if (this.position < that.position || (this.position == that.position && this.height < that.height))
            return -1; // ascending order / minimum Heap
        return 1;
    }
}