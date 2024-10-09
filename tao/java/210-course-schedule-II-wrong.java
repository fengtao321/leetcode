import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

// 4
// [[1,0],[2,0],[3,1],[3,2]]
// when merge 2 lists, [1.0] and [2. 0] will be merged to [0, 1, 2], but [3,1] and [3, 2] may be merged as [2, 1, 3], then will return wrong answer 

class Solution {
    HashMap<Integer, ArrayList<Integer>> sourceMap = new HashMap<>();
    LinkedList<ArrayList<Integer>> records = new LinkedList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            ArrayList<Integer> list = sourceMap.getOrDefault(course, new ArrayList<Integer>());
            list.add(prerequisites[i][1]);
            sourceMap.put(course, list);
        }

        for (Integer course : sourceMap.keySet()) {
            updateCourseList(course);
        }

        while (records.size() > 1) {
            ArrayList<Integer> record1 = records.pollFirst();
            ArrayList<Integer> record2 = records.pollFirst();
            ArrayList<Integer> record3 = mergeLists(record1, record2);

            if (record3 == null)
                return new int[0]; // can not be merged

            records.addLast(record3);
        }

        ArrayList<Integer> courseList = records.peekFirst();

        if (courseList.size() != numCourses)
            return new int[0];

        int[] ans = new int[numCourses];
        for (int j = 0; j < numCourses; j++) {
            ans[j] = courseList.get(j);
        }

        return ans;
    }

    private void updateCourseList(Integer course) {
        ArrayList<Integer> list = sourceMap.get(course);
        list.add(course);
        records.add(list);
    }

    private ArrayList<Integer> mergeLists(ArrayList<Integer> record1, ArrayList<Integer> record2) {
        if (record1.isEmpty())
            return record2;
        if (record2.isEmpty())
            return record1;

        int num1 = record1.get(0);
        int num2 = record2.get(0);
        if (num1 == num2) {
            record1.remove(0);
            record2.remove(0);

            ArrayList<Integer> record3 = mergeLists(record1, record2);
            if (record3 != null)
                record3.add(0, num1);
            return record3;
        }

        if (record2.contains(num1) && record1.contains(num2)) {
            return null;
        }

        if (record2.contains(num1)) {
            record2.remove(0);
            ArrayList<Integer> record3 = mergeLists(record1, record2);
            if (record3 != null)
                record3.add(0, num2);
            return record3;
        }

        if (record1.contains(num2)) {
            record1.remove(0);
            ArrayList<Integer> record3 = mergeLists(record1, record2);
            if (record3 != null)
                record3.add(0, num1);
            return record3;
        }

        record1.remove(0);
        record2.remove(0);

        ArrayList<Integer> record3 = mergeLists(record1, record2);
        if (record3 != null) {
            record3.add(0, num1);
            record3.add(0, num2);
        }
        return record3;
    }
}