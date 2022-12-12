/* Day 12: Hill Climbing Algorithm
 *      --------Part 1--------   --------Part 2--------
 *          Time   Rank  Score       Time   Rank  Score
 *      08:54:01  19164      0   09:02:36  18361      0
 *
 * Today was just a pretty simple breadth first search. However, I forgot that the algorithm uses a deque, and so I used
 * a stack, and as a result I spent an hour staring and trying to find the problem. Once I realized that, part two took
 * almost no time at all, especially because map size and start and end positions got hardcoded
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws FileNotFoundException {
        //Load in the map
        Scanner in = new Scanner(new File("src/inputs/input12.txt"));
        char[][] map = new char[41][80];

        int i = 0;
        while (in.hasNextLine()) {
            map[i] = in.nextLine().toCharArray();
            i++;
        }

        System.out.println(part1(map));
        System.out.println(part2(map));
    }

    //Just a lil bfs
    public static int part1(char[][] map) {
        boolean[][] visited = new boolean[41][80];

        ArrayList<int[]> s = new ArrayList<>();
        s.add(new int[] {0, 20, 0});

        while (!s.isEmpty()) {
            int[] cur = s.remove(0);
            int x = cur[0];
            int y = cur[1];
            int step = cur[2];

            char c = map[y][x];
            if (c == 'S') { c = 'a'; }

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 80 && nx >= 0 && ny < 41 && ny >= 0 && !visited[ny][nx]) {
                    if (map[ny][nx] == 'E') {
                        if (c >= 'y') return step+ 1;
                    } else if (map[ny][nx] <= c + 1) {
                        s.add(new int[] {nx, ny, step + 1});
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return -1;
    }

    //Going backwards from E, still bfs
    public static int part2(char[][] map) {
        boolean[][] visited = new boolean[41][80];

        ArrayList<int[]> s = new ArrayList<>();
        s.add(new int[] {55, 20, 0});

        while (!s.isEmpty()) {
            int[] cur = s.remove(0);
            int x = cur[0];
            int y = cur[1];
            int step = cur[2];

            char c = map[y][x];
            if (c == 'E') { c = 'z'; }

            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 80 && nx >= 0 && ny < 41 && ny >= 0 && !visited[ny][nx]) {
                    if (map[ny][nx] == 'a' || map[ny][nx] == 'S') {
                        if (map[ny][nx] >= c - 1) return step + 1;
                    } else if (map[ny][nx] >= c - 1) {
                        s.add(new int[] {nx, ny, step + 1});
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return -1;
    }
}
