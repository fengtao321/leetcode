import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    List<String> ans = new ArrayList<>();
    char[][] board;

    public List<String> findWords(char[][] board, String[] words) {
        WordNode head = this.createWordTree(words);
        this.board = board;

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[0].length; j++) {
                WordNode node = head;
                findWord(i, j, node);
            }
        }

        return this.ans;
    }

    public void findWord(int i, int j, WordNode node) {
        if (node.next.containsKey("$")) {
            WordNode endNode = node.next.get("$");
            ans.add(endNode.val);
            this.trimTreeBranch(endNode, "$"); // trim the branch
        }

        if (i < 0 || i >= this.board.length || j < 0 || j >= this.board[0].length
                || !node.next.containsKey("" + this.board[i][j])) {
            return;
        }

        String key = "" + this.board[i][j];
        node = node.next.get(key);
        this.board[i][j] = '*';
        this.findWord(i, j + 1, node);
        this.findWord(i, j - 1, node);
        this.findWord(i + 1, j, node);
        this.findWord(i - 1, j, node);
        this.board[i][j] = key.charAt(0);
    }

    private void trimTreeBranch(WordNode node, String key) {
        WordNode preNode = node.prev;
        preNode.next.remove(key);
        if (preNode.next.size() < 1) {
            trimTreeBranch(preNode, preNode.val);
        }
    }

    private WordNode createWordTree(String[] words) {
        WordNode head = new WordNode("", null);

        for (String word : words) {
            WordNode node = head;
            for (int i = 0; i < word.length(); i++) {
                String key = "" + word.charAt(i);
                if (!node.next.containsKey(key)) {
                    node.next.put(key, new WordNode(key, node));
                }
                node = node.next.get(key);
            }
            node.next.put("$", new WordNode(word, node));
        }
        return head;
    }
}

class WordNode {
    String val;
    HashMap<String, WordNode> next = new HashMap<>();
    WordNode prev;

    WordNode(String val, WordNode prev) {
        this.val = val;
        this.prev = prev;
    }
}