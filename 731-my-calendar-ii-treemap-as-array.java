import java.util.Map;
import java.util.TreeMap;

class MyCalendarTwo {

    TreeMap<Integer, Integer> map;

    public MyCalendarTwo() {
        map = new TreeMap<>();
    }

    // each book is N log N (log N to insert and N to loop)
    public boolean book(int start, int end) {
        // just make sure our start and end is in the treemap
        map.put(start, map.getOrDefault(start, 0));
        map.put(end, map.getOrDefault(end, 0));

        int rs = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            rs += entry.getValue();
            int key = entry.getKey();

            if (key >= start && key < end) {
                // do not add if it causes triple booking
                if (rs >= 2)
                    return false;
            }
            if (key > end)
                break;
        }

        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        return true;

    }
}