import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ordering = new int[numCourses];
        int[] degree = new int[numCourses];

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++)
            graph.put(i, new ArrayList<>());

        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preCourse = prerequisites[i][1];
            graph.get(preCourse).add(course);
            degree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        int index = 0;
        while (queue.size() > 0) {
            int preCourse = queue.poll();
            ordering[index++] = preCourse;

            List<Integer> courses = graph.get(preCourse);
            for (int i = 0; i < courses.size(); i++) {
                int c = courses.get(i);
                degree[c] = degree[c] - 1;
                if (degree[c] == 0)
                    queue.add(c);
            }
        }
        return index == numCourses ? ordering : new int[0];
    }

}