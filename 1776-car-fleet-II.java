class Solution {
    public double[] getCollisionTimes(int[][] cars) { // {startPosition, speed}
        double[] times = new double[cars.length]; // collides time
        double[] positions = new double[cars.length]; // collides position
        times[cars.length - 1] = -1;

        int fleetIndex = cars.length - 1;

        for (int i = cars.length - 2; i >= 0; i--) {
            // if car is slow than the fleet
            if (cars[i][1] <= cars[fleetIndex][1]) {
                fleetIndex = i;
                times[i] = -1;
                continue;
            }

            int j = i + 1; // max j is the fleetIndex

            // at the collide time, does carI drive more distance than carJ?
            while (j < fleetIndex
                    && (cars[i][1] <= cars[j][1] || getollisionPosition(cars[i], times[j]) < positions[j])) {
                j++;
            }

            times[i] = getCollisionTime(cars[i], cars[j]);
            positions[i] = getollisionPosition(cars[i], times[i]);
        }

        // System.out.println(Arrays.toString(times));
        // System.out.println(Arrays.toString(positions));
        return times;
    }

    private double getollisionPosition(int[] car, double time) {
        return car[0] + time * car[1];
    }

    private double getCollisionTime(int[] follower, int[] front) {
        return (double) (front[0] - follower[0]) / (double) (follower[1] - front[1]);
    }

    // double time = (double) (front[0] - follower[0]) / (follower[1] - front[1]);
    // int timeInt = (int) Math.round(time*100000); <----- this is wrong, will
    // mislead to the wrong car
    // return (double) timeInt / 100000;
}