import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    HashMap<Integer, ArrayList<Integer>> sourceMap = new HashMap<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequisite = prerequisites[i];
            if (!updateSourceMap(prerequisite[0], prerequisite[1])) {
                return false;
            }
        }

        return true;
    }

    private boolean updateSourceMap(int key, int value) {
        if (key == value)
            return false;

        ArrayList<Integer> valList = sourceMap.get(value);

        if (valList != null && valList.indexOf(key) > -1) {
            return false;
        }

        ArrayList<Integer> keyList = sourceMap.getOrDefault(key, new ArrayList<Integer>());

        if (valList != null) {
            keyList.addAll(valList);
        }

        if (valList == null && keyList.indexOf(value) < 0) {
            keyList.add(value);
        }

        sourceMap.put(key, keyList);
        return true;

    }
}