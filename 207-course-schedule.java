import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    HashMap<Integer, ArrayList<Integer>> sourceMap = new HashMap<>(); // we can't use the HashSet here, when use
                                                                      // for(Integer course: set) to get course, we can
                                                                      // not change set

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preCourse = prerequisites[i][1];
            ArrayList<Integer> list = sourceMap.getOrDefault(course, new ArrayList<>());
            list.add(preCourse);
            sourceMap.put(course, list);
        }

        // sourceMap.forEach((k, v) -> {
        // System.out.println("");
        // System.out.print("key: " + k + " value: ");
        // printList(v);
        // System.out.println("");
        // });

        for (Integer course : sourceMap.keySet()) {
            if (!checkSourceMap(numCourses, sourceMap.get(course))) {
                return false;
            }

        }

        return true;
    }

    private void printList(ArrayList<Integer> object) {
        object.forEach(value -> System.out.print(value + "->"));
    }

    private boolean checkSourceMap(int numCourses, ArrayList<Integer> prerequisiteList) {
        // null if this map contains no mapping for the key.
        if (prerequisiteList == null || prerequisiteList.size() == 0)
            return true;

        if (numCourses == 0)
            return false;

        int i = 0;
        while (i < prerequisiteList.size()) {
            int preCourse = prerequisiteList.get(i);
            if (!checkSourceMap(numCourses - 1, sourceMap.get(preCourse)))
                return false;
            prerequisiteList.remove(i);
        }

        return true;
    }
}