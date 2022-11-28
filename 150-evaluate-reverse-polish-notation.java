import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        int num = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.add(stack.poll() + stack.poll());
                    break;
                case "-":
                    num = stack.poll();
                    stack.add(stack.poll() - num);
                    break;
                case "*":
                    stack.add(stack.poll() * stack.poll());
                    break;
                case "/":
                    num = stack.poll();
                    stack.add(stack.poll() / num);
                    break;
                default:
                    stack.add(Integer.parseInt(token));
            }
        }

        return stack.poll();
    }
}