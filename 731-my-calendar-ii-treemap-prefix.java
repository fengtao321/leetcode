
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

class MyCalendarTwo {

    TreeMap<Integer, Integer> map;

    public MyCalendarTwo() {
        map = new TreeMap<>();
    }

    // each book is N log N (log N to insert and N to loop)
    public boolean book(int start, int end) {
        Integer counter = map.floorKey(start);
        if (counter == null) {
            counter = 0;
        }

        if (counter == 2) {
            return false;
        }

        NavigableMap<Integer, Integer> subMap = map.subMap(start, false, end, false);
        for (Integer v : subMap.values()) {
            if (v == 2)
                return false;
        }

        map.put(start, counter + 1);
        for (Map.Entry<Integer, Integer> entry : subMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue() + 1);
        }

        map.put(end, map.floorKey(end) - 1);

        return true;
    }
}