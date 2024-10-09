import java.util.TreeMap;

class RangeModule {
    TreeMap<Integer, Integer> rangeRecord; // left, right

    public RangeModule() {
        rangeRecord = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = rangeRecord.lowerKey(left);
        Integer end = rangeRecord.floorKey(right);

        if (start != null && rangeRecord.get(start) >= left) {
            left = start;
        }

        if (end != null && rangeRecord.get(end) > right) {
            right = rangeRecord.get(end);
        }

        rangeRecord.subMap(left, false, right, true).clear();
        rangeRecord.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer startPre = rangeRecord.floorKey(left);
        if (startPre != null && rangeRecord.get(startPre) >= right) {
            return true;
        }
        return false;
    }

    public void removeRange(int left, int right) {
        Integer start = rangeRecord.lowerKey(left);
        Integer end = rangeRecord.lowerKey(right);

        if (end != null && rangeRecord.get(end) > right) {
            rangeRecord.put(right, rangeRecord.get(end));
        }

        if (start != null && rangeRecord.get(start) > left) {
            rangeRecord.put(start, left);
        }

        rangeRecord.subMap(left, true, right, false).clear();
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */