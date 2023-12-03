package leetcode;

import java.util.*;

public class DesignTwitter355 {

    class Twitter {

        Map<Integer, Set<Integer>> followerMap = new HashMap<>();
        Map<Integer, List<Tweet>> tweetMap = new HashMap<>();
        int count = 0;

        public class Tweet {

            public int count;
            public int id;

            public Tweet(int count, int id) {
                this.count = count;
                this.id = id;
            }
        }

        public Twitter() {}

        public void postTweet(int userId, int tweetId) {
            followerMap.putIfAbsent(userId, new HashSet<>());
            followerMap.get(userId).add(userId);
            tweetMap.putIfAbsent(userId, new ArrayList<>());
            tweetMap.get(userId).add(new Tweet(count, tweetId));
            count++;
        }

        public List<Integer> getNewsFeed(int userId) {
            Set<Integer> followeeSet = followerMap.getOrDefault(
                userId,
                new HashSet<>()
            );

            PriorityQueue<Tweet> minHeap = new PriorityQueue<>((a, b) ->
                a.count - b.count
            );

            for (int followeeId : followeeSet) {
                List<Tweet> tweets = tweetMap.getOrDefault(
                    followeeId,
                    new ArrayList<>()
                );

                for (int i = tweets.size() - 1; i >= 0; i--) {
                    Tweet tweet = tweets.get(i);
                    if (minHeap.size() == 10) {
                        if (minHeap.peek().count < tweet.count) {
                            minHeap.poll();
                        } else {
                            break;
                        }
                    }
                    minHeap.offer(tweet);
                }
            }

            List<Integer> result = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                result.add(0, minHeap.poll().id);
            }
            return result;
        }

        public void follow(int followerId, int followeeId) {
            followerMap.putIfAbsent(followerId, new HashSet<>());
            followerMap.get(followerId).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            followerMap.putIfAbsent(followerId, new HashSet<>());
            followerMap.get(followerId).remove(followeeId);
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
}
