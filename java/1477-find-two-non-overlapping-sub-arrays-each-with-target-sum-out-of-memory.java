import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Node {
    int length;
    int left;
    int right;

    Node(int length, int left, int right) {
        this.length = length;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int sum = arr[0];
        int left = 0;
        int right = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.length - a.length);

        while (left < arr.length && right < arr.length) {

            if (sum < target && right == (arr.length - 1)) // right reaches the end
                break;

            if (sum == target) {
                // System.out.println("left = " + left);
                // System.out.println("right = " + right);
                Node node = new Node(right - left + 1, left, right);
                pq.add(node);
            }

            if (right < left) {
                right = left;
                sum = arr[right];
                continue;
            }

            if (sum < target) {
                right++;
                sum = sum + arr[right];
            } else {
                sum = sum - arr[left];
                left++;
            }
        }

        if (pq.size() < 2)
            return -1;

        List<Node> arrayList = new ArrayList<Node>(pq);
        PriorityQueue<Integer> ans = new PriorityQueue<>((a, b) -> b - a);

        int max = arr.length;
        for (int i = 0; i < arrayList.size() - 1; i++) {
            Node leftN = arrayList.get(i);

            if (leftN.length >= max) {
                break;
            }

            for (int j = i + 1; j < arrayList.size(); j++) {
                Node rightN = arrayList.get(j);
                if (rightN.right < leftN.left || leftN.right < rightN.left) {
                    ans.add(leftN.length + rightN.length);
                    if (ans.size() > 2)
                        ans.poll();
                    max = (int) Math.ceil(ans.peek() / 2);
                    break;
                }
            }
        }

        return ans.poll() + ans.poll();
    }
}