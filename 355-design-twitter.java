// Usually, these problems are approached differently. It’s a system design questions. It’s common practice to do more work on the write path (creating a tweet) versus read path (getting feed) since you want the feed to be fast and it doesn’t matter if creating a tweet is a bit slower. If you get this in an interview, the answer is to generate a cache of feeds while the user tweets or on a schedule or for top users. Then getNewsFeed(), you just read from cache. This operation needs to be really fast.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

class Twitter {
    HashMap<Integer, Account> database = new HashMap<>();
    int timeSlot = 1;

    class Account {
        LinkedList<int[]> post = new LinkedList<>(); // [timeslot, postId] inside ArrayList
        HashSet<Integer> followees = new HashSet<>();

        public void addPost(int tweetId) {
            int[] news = { timeSlot, tweetId };
            this.post.addFirst(news);
            timeSlot++;
        }

        public void follow(int id) {
            this.followees.add(id);
        }

        public void unfollow(int id) {
            this.followees.remove(id);
        }
    }

    public void postTweet(int userId, int tweetId) {
        Account userAccount = this.database.getOrDefault(userId, new Account());
        userAccount.addPost(tweetId);
        this.database.put(userId, userAccount);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new LinkedList<>();
        if (!this.database.containsKey(userId)) {
            return feed;
        }

        Account userAccount = this.database.get(userId);

        ArrayList<LinkedList<int[]>> lists = new ArrayList<>();
        lists.add(userAccount.post);

        userAccount.followees.forEach(id -> {
            Account followeeAccount = this.database.get(id);
            lists.add(followeeAccount.post);
        });

        int i = 0;
        while (i < (lists.size() - 1)) {
            LinkedList<int[]> mergedList = mergeTwoLists(lists.get(i), lists.get(i + 1));
            lists.add(mergedList);
            i = i + 2;
        }

        LinkedList<int[]> mergedList = lists.get(lists.size() - 1);
        int size = mergedList.size() > 10 ? 10 : mergedList.size();

        for (i = 0; i < size; i++) {
            int[] news = mergedList.get(i);
            feed.add(news[1]);
        }
        return feed;
    }

    private LinkedList<int[]> mergeTwoLists(LinkedList<int[]> listA, LinkedList<int[]> listB) {
        LinkedList<int[]> mergedList = new LinkedList<>();
        int i = 0;
        int j = 0;

        while (mergedList.size() < 10) {
            if (i == listA.size() || j == listB.size()) {
                LinkedList<int[]> listLeft = (i == listA.size()) ? listB : listA;
                int indexStart = (i == listA.size()) ? j : i;
                int indexEnd =  indexStart + 10 - mergedList.size();
                indexEnd = listLeft.size() <= indexEnd ? listLeft.size(): indexEnd;
                mergedList.addAll(listLeft.subList(indexStart, indexEnd));
                break;
            }

            int[] newsA = listA.get(i);
            int[] newsB = listB.get(j);
            if (newsA[0] > newsB[0]) {
                mergedList.addLast(newsA);
                i++;
            } else {
                mergedList.addLast(newsB);
                j++;
            }
        }

        return mergedList;
    }

    public void follow(int followerId, int followeeId) {
        if (!this.database.containsKey(followeeId)) {
            this.database.put(followeeId, new Account());
        }

        Account userAccount = this.database.getOrDefault(followerId, new Account());
        userAccount.follow(followeeId);
        this.database.put(followerId, userAccount);
    }

    public void unfollow(int followerId, int followeeId) {
        this.database.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */