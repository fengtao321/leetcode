import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    HashMap<Integer, ArrayList<Integer>> sourceMap = new HashMap<>();
    HashSet<Integer> currHeaders = new HashSet<>(); // how many courses that do not have prerequisite? use HashSet to
                                                    // check if the course is already added the prerequisite list, other
                                                    // wise, will run out of memory with arrayList, as the complexity of
                                                    // list.contains is O(n)

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            currHeaders.add(i);
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preCourse = prerequisites[i][1];
            ArrayList<Integer> list = sourceMap.getOrDefault(course, new ArrayList<>());
            list.add(preCourse);
            sourceMap.put(course, list);

            currHeaders.remove(course);
        }

        ArrayList<Integer> headers = convertSetToList(currHeaders); // can not use hashset here, as we need the sequence
                                                                    // of courses

        int currSize = 0;
        while (currSize < headers.size()) {
            currSize = headers.size();

            for (Integer course : sourceMap.keySet()) {
                bfs(course, headers);
            }
        }

        return headers.size() == numCourses ? convertListToArray(headers) : new int[0];
    }

    private ArrayList<Integer> convertSetToList(HashSet<Integer> currHeaders) {
        ArrayList<Integer> headers = new ArrayList<>();
        for (Integer course : currHeaders) {
            headers.add(course);
        }

        return headers;
    }

    private int[] convertListToArray(ArrayList<Integer> headers) {
        int[] ans = new int[headers.size()];

        for (int i = 0; i < headers.size(); i++) {
            ans[i] = headers.get(i);
        }

        return ans;
    }

    private void bfs(int course, ArrayList<Integer> headers) {
        if (currHeaders.contains(course))
            return;

        ArrayList<Integer> preCourses = sourceMap.get(course);
        ArrayList<Integer> preCoursesNew = new ArrayList<>();
        ;

        for (int i = 0; i < preCourses.size(); i++) {
            int preCourse = preCourses.get(i);
            if (!currHeaders.contains(preCourse)) {
                preCoursesNew.add(preCourse);
            }
        }

        if (preCoursesNew.size() == 0) {
            headers.add(course);
            currHeaders.add(course);
        } else {
            sourceMap.put(course, preCoursesNew);
        }

        return;
    }
}