import java.util.ArrayDeque;

class Solution {
    public double[] getCollisionTimes(int[][] cars) { // {startPosition, speed}
        double[] times = new double[cars.length]; // collides time
        times[cars.length - 1] = -1;
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int minSpeed = cars[cars.length - 1][1];
        for (int i = cars.length - 2; i >= 0; i--) {
            // if car is slow than the fleet
            if (cars[i][1] <= minSpeed) {
                minSpeed = cars[i][1];
                times[i] = -1;
                continue;
            }

            Integer j = stack.peekLast();
            while (j != null && times[j] > -1
                    && (cars[i][1] <= cars[j][1] || getCollisionTime(cars[i], cars[j]) > times[j])) {
                stack.pollLast();
                j = stack.peekLast();
            }

            times[i] = getCollisionTime(cars[i], cars[j]);
            stack.add(i);
        }

        return times;
    }

    private double getCollisionTime(int[] follower, int[] front) {
        return (double) (front[0] - follower[0]) / (double) (follower[1] - front[1]);
    }

}