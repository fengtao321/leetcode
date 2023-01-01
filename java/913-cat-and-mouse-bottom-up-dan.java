import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    int[][][] dp;
    int n;
    int[][] graph;

    public final static int MOUSE_TURN = 1;
    public final static int CAT_TURN = 2;
    public final static int MOUSE_WIN = 1;
    public final static int CAT_WIN = 2;
    public final static int HOLE = 0;

    public int catMouseGame(int[][] graph) {
        this.graph = graph;
        this.n = graph.length;
        this.dp = new int[n][n][3];

        Queue<State> queue = new LinkedList<>();

        // let's set the known states
        for (int turn = 1; turn <= 2; turn++) {
            // pos start at 1 because 0 is forbidden for the cat
            for (int pos = 1; pos < n; pos++) {

                // mouse in pos 0 is always a win
                dp[0][pos][turn] = MOUSE_WIN;
                queue.offer(new State(0, pos, turn));

                // same position for both is a cat win, except in the hole
                dp[pos][pos][turn] = CAT_WIN;
                queue.offer(new State(pos, pos, turn));

            }
        }

        while (!queue.isEmpty()) {
            State currentState = queue.poll();

            // we know currentState is terminal (cat or mouse win)
            // let's populate states from there

            int currentStateResult = this.dp[currentState.mousePos][currentState.catPos][currentState.turn];

            // let's get the states that transition in this current state
            for (State prevState : getPreviousStates(currentState)) {

                if (this.dp[prevState.mousePos][prevState.catPos][prevState.turn] > 0)
                    continue; // we already know the result here

                if (currentStateResult == prevState.turn) {
                    // either have a mouse WIN and prev state it was mouse turn
                    // or have cat win and prev state is cat turn.
                    // in this case, player will pick victory because of optimal play
                    this.dp[prevState.mousePos][prevState.catPos][prevState.turn] = currentStateResult;
                    queue.offer(prevState);
                } else if (this.areAllNextStateLosing(prevState)) {
                    // either a mouse win and prev state is cat turn
                    // or a cat win and prv state is mouse turn
                    // so we check if a player can avoid defeat in other moves. If it cannot, we set
                    // losing result
                    this.dp[prevState.mousePos][prevState.catPos][prevState.turn] = currentStateResult;
                    queue.offer(prevState);
                }

            }
        }

        return this.dp[1][2][1]; // initial pos is mouse 1, cat 2, cat's turn
    }

    private List<State> getPreviousStates(State currentState) {
        List<State> prevStates = new ArrayList<>();

        if (currentState.turn == MOUSE_TURN) {

            // if in current state it's mouse's turn, then in prev state, it was cat's turn
            // so the cat's position changed between prev state and this state
            // need to find where the cat could have come from

            for (int pos : graph[currentState.catPos]) {
                if (pos == HOLE)
                    continue; // forbidden for cat

                prevStates.add(new State(currentState.mousePos, pos, CAT_TURN));
            }

        } else {

            for (int pos : graph[currentState.mousePos]) {

                prevStates.add(new State(pos, currentState.catPos, MOUSE_TURN));
            }
        }

        return prevStates;
    }

    private boolean areAllNextStateLosing(State currentState) {
        boolean result = true;
        if (currentState.turn == MOUSE_TURN) {

            for (int pos : graph[currentState.mousePos]) {
                if (this.dp[pos][currentState.catPos][CAT_TURN] != CAT_WIN)
                    return false;
            }
        } else {

            for (int pos : graph[currentState.catPos]) {
                if (pos == HOLE)
                    continue;
                if (this.dp[currentState.mousePos][pos][MOUSE_TURN] != MOUSE_WIN)
                    return false;
            }
        }
        return result;
    }

}

class State {
    int mousePos;
    int catPos;
    int turn; // 1 mouse, 2 cat

    public State(int mousePos, int catPos, int turn) {
        this.mousePos = mousePos;
        this.catPos = catPos;
        this.turn = turn;
    }
}