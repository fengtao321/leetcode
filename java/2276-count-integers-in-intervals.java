import java.util.TreeMap;

class CountIntervals {
    TreeMap<Integer, Integer> map;
    int counter;

    public CountIntervals() {
        map = new TreeMap<>();
        counter = 0;
    }

    public void add(int left, int right) {
        int leftLimit = left;
        int rightLimit = right;

        Integer floorKey = map.floorKey(right);

        while (floorKey != null && map.get(floorKey) >= left) {
            int floorValue = map.get(floorKey);
            if (floorValue >= right) {
                rightLimit = floorValue;
            }

            if (floorKey <= left) {
                leftLimit = floorKey;
            }

            counter = counter - (floorValue - floorKey + 1);
            map.remove(floorKey);

            floorKey = map.floorKey(right);
        }

        map.put(leftLimit, rightLimit);
        counter = counter + (rightLimit - leftLimit + 1);
    }

    public int count() {
        return counter;
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */