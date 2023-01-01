import java.util.TreeMap;

class MyCalendar {
    TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        int startPre = calendar.lowerKey(end);
        if (calendar.get(startPre) > start) {
            return false;
        }

        if (calendar.get(start) == null || calendar.get(start) < end) {
            calendar.put(start, end);
        }

        return true;

    }
}