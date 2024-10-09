import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;

class Solution {

    HashSet<CarNode> record = new HashSet<>();

    public int racecar(int target) {
        ArrayDeque<CarNode> queue = new ArrayDeque<>();
        queue.add(new CarNode(0, 1));
        int counter = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                CarNode node = queue.poll();

                int curr = node.last + node.speed;
                if (curr == target) {
                    return counter;
                }

                // instruction A
                CarNode nextInstructionA = new CarNode(node.speed + node.last, node.speed * 2);
                if (!record.contains(nextInstructionA)) {
                    queue.add(nextInstructionA);
                    record.add(nextInstructionA);
                }

                // instruction B
                CarNode nextInstructionB;
                if (node.speed > 0) {
                    nextInstructionB = new CarNode(node.last, -1);
                } else {
                    nextInstructionB = new CarNode(node.last, 1);
                }
                if (!record.contains(nextInstructionB)) {
                    queue.add(nextInstructionB);
                    record.add(nextInstructionB);
                }
            }

            counter++;
        }

        return counter;
    }
}

class CarNode {
    int last;
    int speed;

    CarNode(int last, int speed) {
        this.last = last;
        this.speed = speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(last, speed);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CarNode) {
            CarNode s = (CarNode) obj;
            return this.last == s.last && this.speed == s.speed;
        }

        return false;
    }
}