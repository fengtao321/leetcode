import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]); // start, end
        int roomIndex = 0;
        int meetingNum = 0;
        int[] roomCounter = new int[n];

        Deque<Integer> meetingsToStart = new ArrayDeque<>(); // interval of meetings that ready to start
        PriorityQueue<long[]> meetingsInProcess = new PriorityQueue<>((a, b) -> a[0] < b[0] ? -1 : 1);// timeToEnd,
                                                                                                      // meeting room;
        PriorityQueue<Integer> emptyRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            emptyRooms.add(i);
        }

        int meetingIndex = 0;
        long time = 0;
        while (meetingIndex < meetings.length || !meetingsToStart.isEmpty()) {
            while (meetingIndex < meetings.length && meetings[meetingIndex][0] <= time) {
                meetingsToStart.add(meetings[meetingIndex][1] - meetings[meetingIndex][0]);
                meetingIndex++;
            }

            while (!meetingsInProcess.isEmpty() && meetingsInProcess.peek()[0] <= time) {
                long[] meeting = meetingsInProcess.poll();
                emptyRooms.add((int) meeting[1]);
            }

            while (!emptyRooms.isEmpty() && !meetingsToStart.isEmpty()) {
                int room = emptyRooms.poll();
                long timeToEnd = time + meetingsToStart.poll();
                meetingsInProcess.add(new long[] { timeToEnd, room });
                roomCounter[room] += 1;

                if (roomCounter[room] > meetingNum || (roomCounter[room] == meetingNum && room < roomIndex)) {
                    roomIndex = room;
                    meetingNum = roomCounter[room];
                }
            }

            time = (!emptyRooms.isEmpty() && meetingIndex < meetings.length) ? meetings[meetingIndex][0]
                    : meetingsInProcess.peek()[0];

        }

        return roomIndex;
    }
}