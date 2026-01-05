package leetcode.day20;
import java.util.*;

// LeetCode 2402: Meeting Rooms III
// Difficulty: Hard
// Topic: Priority Queue, Simulation
// Link: https://leetcode.com/problems/meeting-rooms-iii/

public class MeetingRoomsIII_2402 {

    public int mostBooked(int n, int[][] meetings) {

        // Sort meetings by start time
        Arrays.sort(meetings, (x, y) -> x[0] - y[0]);

        // Available rooms (min room index)
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }

        // Busy rooms: {endTime, roomIndex}
        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((x, y) -> {
            if (x[0] == y[0]) return Long.compare(x[1], y[1]);
            return Long.compare(x[0], y[0]);
        });

        int[] count = new int[n];
        long currentTime = 0;

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            int duration = end - start;

            currentTime = Math.max(currentTime, start);

            // Free rooms that are done before current time
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= currentTime) {
                availableRooms.offer((int) busyRooms.poll()[1]);
            }

            // If no room available, wait for earliest one
            if (availableRooms.isEmpty()) {
                long[] earliest = busyRooms.poll();
                currentTime = earliest[0];
                availableRooms.offer((int) earliest[1]);

                while (!busyRooms.isEmpty() && busyRooms.peek()[0] == currentTime) {
                    availableRooms.offer((int) busyRooms.poll()[1]);
                }
            }

            int room = availableRooms.poll();
            count[room]++;
            busyRooms.offer(new long[]{currentTime + duration, room});
        }

        int maxRoom = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[maxRoom]) {
                maxRoom = i;
            }
        }
        return maxRoom;
    }
}
