package leetcode.day05;

import java.util.*;

// Count Mentions
// Topic: Simulation, Priority Queue, Sorting
// Time: O(E log E + U)
// Space: O(U)

public class CountMentions_Events {

    // Helper class (instead of record)
    private static class OfflineUser {
        int returnTimestamp;
        int userId;

        OfflineUser(int returnTimestamp, int userId) {
            this.returnTimestamp = returnTimestamp;
            this.userId = userId;
        }
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {

        int[] ans = new int[numberOfUsers];
        boolean[] online = new boolean[numberOfUsers];
        Arrays.fill(online, true);

        Queue<OfflineUser> offlineQueue =
                new PriorityQueue<>(Comparator.comparingInt(o -> o.returnTimestamp));

        int allMentionsCount = 0;

        events.sort(
                Comparator.comparingInt((List<String> e) -> Integer.parseInt(e.get(1)))
                        .thenComparing(e -> e.get(0), Comparator.reverseOrder())
        );

        for (List<String> event : events) {
            String type = event.get(0);
            int timestamp = Integer.parseInt(event.get(1));

            // Bring users back online
            while (!offlineQueue.isEmpty() &&
                    offlineQueue.peek().returnTimestamp <= timestamp) {
                online[offlineQueue.poll().userId] = true;
            }

            if (type.equals("MESSAGE")) {
                String mentions = event.get(2);

                if (mentions.equals("ALL")) {
                    allMentionsCount++;
                } else if (mentions.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (online[i]) ans[i]++;
                    }
                } else {
                    for (int id : getUserIds(mentions)) {
                        ans[id]++;
                    }
                }

            } else { // OFFLINE
                int userId = Integer.parseInt(event.get(2));
                online[userId] = false;
                offlineQueue.offer(new OfflineUser(timestamp + 60, userId));
            }
        }

        // Apply ALL mentions
        for (int i = 0; i < numberOfUsers; i++) {
            ans[i] += allMentionsCount;
        }

        return ans;
    }

    private List<Integer> getUserIds(String s) {
        List<Integer> ids = new ArrayList<>();
        for (String part : s.split(" ")) {
            ids.add(Integer.parseInt(part.substring(2)));
        }
        return ids;
    }
}
