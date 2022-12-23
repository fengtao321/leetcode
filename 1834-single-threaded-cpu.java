import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public int[] getOrder(int[][] tasks) {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            taskList.add(new Task(i, tasks[i][0], tasks[i][1]));
        }
        Collections.sort(taskList, (Task a, Task b) -> a.enqueueTime - b.enqueueTime);

        PriorityQueue<Task> pq = new PriorityQueue<>();
        int[] ans = new int[tasks.length];

        int taskI = 0;
        int ansI = 0;
        int time = 0;

        while (ansI < ans.length) {
            // add tasks less and equal to time to the queue
            while (taskI < tasks.length && taskList.get(taskI).enqueueTime <= time) {
                pq.add(taskList.get(taskI));
                taskI++;
            }

            if (pq.isEmpty()) {
                time = time + taskList.get(taskI).processingTime;
                continue;
            }

            Task task = pq.poll();
            time = time + task.processingTime;
            ans[ansI++] = task.id;
        }

        return ans;
    }
}

class Task implements Comparable<Task> {
    int id;
    int enqueueTime;
    int processingTime;

    Task(int id, int enqueueTime, int processingTime) {
        this.id = id;
        this.enqueueTime = enqueueTime;
        this.processingTime = processingTime;
    }

    @Override
    public int compareTo(Task o) {
        if (this.processingTime < o.processingTime || (this.processingTime == o.processingTime && this.id < o.id))
            return -1;
        return 1;
    }
}