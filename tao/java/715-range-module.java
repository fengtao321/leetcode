import java.util.TreeMap;

class RangeModule {
    TreeMap<Integer, Integer> rangeRecord; // left, right

    public RangeModule() {
        rangeRecord = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        // combine the overlap range
        int start = left;
        int end = right;
        // think about the case, [2,3) in the record, and will add [3,4), can not use
        // lowerKey
        Integer startPre = rangeRecord.floorKey(right); // start point is less than right
        while (startPre != null) { // end point greater than left
            int endPre = rangeRecord.get(startPre);
            if (endPre < left) // think about the case, [2,3) in the record, and will add [3,4), can not use <=
                break;

            rangeRecord.remove(startPre);
            start = Math.min(start, startPre);
            end = Math.max(end, endPre);
            startPre = rangeRecord.lowerKey(right);
        }
        rangeRecord.put(start, end);
    }

    public boolean queryRange(int left, int right) {
        Integer startPre = rangeRecord.floorKey(left);
        if (startPre != null && rangeRecord.get(startPre) >= right) {
            return true;
        }
        return false;
    }

    public void removeRange(int left, int right) {
        Integer startPre = rangeRecord.lowerKey(right);
        while (startPre != null) {
            Integer endPre = rangeRecord.get(startPre);
            if (endPre <= left)
                return;

            rangeRecord.remove(startPre);
            // first case, previous start less than left
            if (startPre < left) {
                rangeRecord.put(startPre, left);
            }
            // second case, previous start greater or equal to left
            if (endPre > right) {
                rangeRecord.put(right, endPre);
            }
            startPre = rangeRecord.lowerKey(right);
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */