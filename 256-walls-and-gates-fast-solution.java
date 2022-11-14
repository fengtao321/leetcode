import java.util.HashSet;
import java.util.LinkedList;

class Solution {
    LinkedList<int[]> gates = new LinkedList<>();

    int[][] rooms;
    int rowNum;
    int colNum;

    public void wallsAndGates(int[][] rooms) {
        this.rooms = rooms;
        rowNum = rooms.length;
        colNum = rooms[0].length;

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (rooms[i][j] == 0) {
                    gates.add(new int[] { i, j });
                }
            }
        }

        int counter = 0;
        while (gates.size() > 0) {
            counter++;
            int length = gates.size();
            for (int index = 0; index < length; index++) {
                int[] room = gates.pollLast();
                updateRooms(room[0] + 1, room[1], counter);
                updateRooms(room[0] - 1, room[1], counter);
                updateRooms(room[0], room[1] + 1, counter);
                updateRooms(room[0], room[1] - 1, counter);
            }
        }
    }

    private void updateRooms(int i, int j, int counter) {
        if (i < 0 || j < 0 || i == rowNum || j == colNum || rooms[i][j] <= counter)
            return;

        rooms[i][j] = counter;
        gates.addFirst(new int[] { i, j });
    }
}