import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        TreeMap<Integer, Integer> flowerMap = new TreeMap<>();

        for (int[] flower : flowers) {
            int start = flower[0];
            int end = flower[1] + 1;

            NavigableMap<Integer, Integer> subMap = flowerMap.subMap(start, false, end, false);

            for (int key : subMap.keySet()) {
                flowerMap.put(key, flowerMap.get(key) + 1);
            }

            Integer startKey = flowerMap.floorKey(start);
            flowerMap.put(start, startKey == null ? 1 : flowerMap.get(startKey) + 1);

            int endKey = flowerMap.floorKey(end);
            if (endKey < end) {
                flowerMap.put(end, flowerMap.get(flowerMap.floorKey(end)) - 1);
            }
        }

        int[] ans = new int[persons.length];

        for (int i = 0; i < persons.length; i++) {
            Integer key = flowerMap.floorKey(persons[i]);
            ans[i] = key == null ? 0 : flowerMap.get(key);
        }

        return ans;
    }
}