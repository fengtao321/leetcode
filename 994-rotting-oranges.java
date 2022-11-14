import java.util.HashSet;
import java.util.LinkedList;

class Solution {
    int[][] grid;
    int rowNum;
    int colNum;

    LinkedList<int[]> rottenOrange = new LinkedList<>();
    HashSet<String> freshOrange = new HashSet<>();

    public int orangesRotting(int[][] grid) {
        this.grid = grid;

        int time = 0;
        rowNum = grid.length;
        colNum = grid[0].length;

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                switch (grid[i][j]) {
                    case 2:
                        rottenOrange.add(new int[] { i, j });
                        break;
                    case 1:
                        freshOrange.add(i + "-" + j);
                        break;
                }
            }
        }
        // System.out.println(freshOrange.toString());
        // printLinkedList(rottenOrange);

        while (rottenOrange.size() > 0 && !freshOrange.isEmpty()) {
            int size = rottenOrange.size();
            for (int i = 0; i < size; i++) {
                int[] orangeIndex = rottenOrange.pollLast();
                orangesRotting(orangeIndex[0] - 1, orangeIndex[1]);
                orangesRotting(orangeIndex[0] + 1, orangeIndex[1]);
                orangesRotting(orangeIndex[0], orangeIndex[1] - 1);
                orangesRotting(orangeIndex[0], orangeIndex[1] + 1);
            }
            time = time + 1;
        }
        return freshOrange.isEmpty() ? time : -1;
    }

    private void orangesRotting(int i, int j) {
        if (i < 0 || i == rowNum || j < 0 || j == colNum || !freshOrange.contains(i + "-" + j))
            return;
        freshOrange.remove(i + "-" + j);
        rottenOrange.addFirst(new int[] { i, j });
    }

    private void printLinkedList(LinkedList<int[]> dq) {
        System.out.println("=============");
        dq.forEach(list -> System.out.println("[" + list[0] + "," + list[1] + "]"));

    }
}