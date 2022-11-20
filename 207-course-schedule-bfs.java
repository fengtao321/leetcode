import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    HashMap<Integer, ArrayList<Integer>> sourceMap = new HashMap<>();
    HashSet<Integer> record = new HashSet<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashSet<Integer> headers = new HashSet<>(); // how many courses that do not have prerequisite?
        HashSet<Integer> headersNext = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            headers.add(i);
            record.add(i);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preCourse = prerequisites[i][1];
            ArrayList<Integer> list = sourceMap.getOrDefault(course, new ArrayList<>());
            list.add(preCourse);
            headers.remove(course);
            record.remove(course);
            sourceMap.put(course, list);
        }

        while (headers.size() > 0) {
            for (Integer course : sourceMap.keySet()) {
                if (!dfs(course, headers, headersNext))
                    return false;
            }

            headers = headersNext;
            headersNext = new HashSet<>();
        }

        return record.size() == numCourses;
    }

    private boolean dfs(int preCourse, HashSet<Integer> headers, HashSet<Integer> headersNext) {
        // null if this map contains no mapping for the key.
        if (headers.contains(preCourse))
            return true;

        ArrayList<Integer> list = sourceMap.get(preCourse);
        ArrayList<Integer> listCut = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int course = list.get(i);
            if (!headers.contains(course)) {
                listCut.add(course);
            }
        }

        if (listCut.size() == 0) {
            headersNext.add(preCourse);
            record.add(preCourse);
        } else {
            sourceMap.put(preCourse, listCut);
        }

        return true;
    }
}