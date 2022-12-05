import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

class MyCalendarTwo {
    TreeMap<Integer, Queue<Integer>> record;

    public MyCalendarTwo() {
        record = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Entry<Integer, Queue<Integer>> preBook = record.lowerEntry(end);
        TreeMap<Integer, Integer> bookedIntervals = new TreeMap<>();
        while (preBook != null) {
            int left = Math.min(preBook.getKey(), start);
            for (int preBookEnd : preBook.getValue()) {
                if (preBookEnd <= start)
                    break;

                bookedIntervals.put(left, bookedIntervals.getOrDefault(left, 0) + 1);
                int right = Math.min(preBookEnd, end);
                bookedIntervals.put(right, bookedIntervals.getOrDefault(right, 0) - 1);
            }

            preBook = record.lowerEntry(preBook.getKey());
        }

        printHashMap(bookedIntervals);
        int counter = 0;
        for (Integer value : bookedIntervals.values()) {
            counter = counter + value;
            if (value > 0 && counter > 1)
                return false;
        }

        Queue<Integer> queue = record.getOrDefault(start, new PriorityQueue<Integer>((a, b) -> b - a));
        queue.add(end);
        record.put(start, queue);
        return true;
    }

    private void printHashMap(TreeMap<Integer, Integer> sourceMap) {
        sourceMap.forEach((k, v) -> {
            System.out.println("");
            System.out.print("key: " + k + " value: " + v);
            System.out.println("");
        });
    }

}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */