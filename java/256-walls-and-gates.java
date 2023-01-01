import java.util.HashSet;
import java.util.LinkedList;

class Solution {
    LinkedList<int[]> gates = new LinkedList<>();
    HashSet<String> paths = new HashSet<>();

    int[][] rooms;
    int rowNum;
    int colNum;

    public void wallsAndGates(int[][] rooms) {
        this.rooms = rooms;
        rowNum = rooms.length;
        colNum = rooms[0].length;

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                switch (rooms[i][j]) {
                    case 0:
                        gates.add(new int[] { i, j });
                        break;
                    case -1:
                        break;
                    default:
                        paths.add(getGateKey(i, j));
                        break;
                }
            }
        }

        int counter = 0;
        while (gates.size() > 0 && !paths.isEmpty()) {
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
        if (i < 0 || j < 0 || i == rowNum || j == colNum || !paths.contains(getGateKey(i, j)))
            return;

        rooms[i][j] = counter;
        gates.addFirst(new int[] { i, j });
        paths.remove(getGateKey(i, j));
    }

    private String getGateKey(int i, int j) {
        return i + "-" + j;
    }
}