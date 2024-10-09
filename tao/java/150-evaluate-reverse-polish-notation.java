import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.add(stack.pollLast() + stack.pollLast());
                    break;
                case "-":
                    num = stack.pollLast();
                    stack.add(stack.pollLast() - num);
                    break;
                case "*":
                    stack.add(stack.pollLast() * stack.pollLast());
                    break;
                case "/":
                    num = stack.pollLast();
                    long val = stack.pollLast() / num;
                    num = val > 0 ? (int) Math.floor(val) : (int) Math.ceil(val);
                    stack.add(num);
                    break;
                default:
                    stack.add(Integer.parseInt(token));
            }
        }

        return stack.poll();
    }
}