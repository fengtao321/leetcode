import java.util.ArrayList;

class Solution {
    char[] sArray;

    public int minFlips(String s) {

        sArray = s.toCharArray();

        ArrayList<Integer> start0 = new ArrayList<>();
        ArrayList<Integer> start1 = new ArrayList<>();

        for (int i = 0; i < sArray.length; i++) {
            if ((i & 1) == 1) {
                if (sArray[i] == '0') {
                    start0.add(i);
                } else {
                    start1.add(i);
                }
            } else {
                if (sArray[i] == '0') {
                    start1.add(i);
                } else {
                    start0.add(i);
                }
            }
        }

        return Math.min(brutalForce(start0), brutalForce(start1));

    }

    private int brutalForce(ArrayList<Integer> placeToSwitch) {
        if (placeToSwitch.size() == 0)
            return 0;
        // printArrayList(placeToSwitch);
        int ans = placeToSwitch.size();
        int sum = ans;

        int last = sArray[sArray.length - 1];
        if (placeToSwitch.get(placeToSwitch.size() - 1) == sArray.length - 1) {
            last = last == '0' ? '1' : '0';
        }

        int j = 0;
        for (int i = 0; i < sArray.length && j < placeToSwitch.size(); i++) {
            if (sArray[i] == last) {
                sum++;
            }

            last = last == '0' ? '1' : '0';

            if (i == placeToSwitch.get(j)) {
                sum--;
                ans = Math.min(ans, sum);
                j++;
            }
        }
        return ans;
    }

    private void printArrayList(ArrayList<Integer> object) {
        object.forEach(value -> System.out.print(value + "->"));
        System.out.println("");
    }
}