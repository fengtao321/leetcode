import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        TreeMap<Integer, Integer> flowerMap = new TreeMap<>();

        for (int[] flower : flowers) {
            int start = flower[0];
            int end = flower[1] + 1;

            flowerMap.put(start, flowerMap.getOrDefault(start, 0) + 1);
            flowerMap.put(end, flowerMap.getOrDefault(end, 0) - 1);
        }

        HashMap<Integer, Integer> personMap = new HashMap<>();

        int[] personsSort = Arrays.copyOf(persons, persons.length);
        Arrays.sort(personsSort);

        int i = 0;
        int counter = 0;
        for (int key : flowerMap.keySet()) {
            if (key >= personsSort[i]) {
                personMap.put(personsSort[i], counter);
                i++;
                while (i < personsSort.length && personsSort[i] == personsSort[i - 1]) {
                    i++;
                }
                if (i == personsSort.length)
                    break;
            }

            counter = counter + flowerMap.get(key);
        }

        int[] ans = new int[persons.length];

        for (i = 0; i < persons.length; i++) {
            ans[i] = personMap.get(persons[i]);
        }

        return ans;
    }

    private void printTreeMap(TreeMap<Integer, Integer> sourceMap) {
        System.out.println("==============");
        sourceMap.forEach((k, v) -> {
            System.out.print("key: " + k + " value: " + v);
            System.out.println("");
        });
    }
}