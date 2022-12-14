/* Advent of code 2022 day 9! The first day in which I actually start commenting code :)
 *
 * This problem in retrospect wasn't too bad, the main challenge was figuring out how to
 * determine where the tail goes depending on its location relative to the head. As soon
 * as I figured out that, the rest of the solutions followed somewhat easily. Apart from
 * the second half of the problem forcing me to write more concise code for the solution
 * there isn't too much more to add.
 *
 * Personal Statistics
 *     ----Part 1-----   ----Part 2-----
 *         Time   Rank       Time   Rank
 *     00:32:34   4495   00:52:09   3526
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Part 1: ");
        part1();
        System.out.print("Part 2: ");
        part2();
    }

    //Generates a unique key for every coordinate x, y
    //Assumes that the coordinates don't exceed 30,000
    public static int getKey(int x, int y) {
        return 30_000*y + x;
    }

    //Returns the sign of the number n, 0, if the number is 0
    public static int sign(int n) {
        if (n==0) return 0;
        return n > 0 ? 1 : -1;
    }

    public static void part1() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input09.txt"));

        //Coordinates of the list's head and tail
        int headx = 0;
        int heady = 0;

        int tailx = 0;
        int taily = 0;

        HashSet<Integer> visited = new HashSet<>();

        int count = 0;

        while (in.hasNextLine()) {
            char dir = in.next().charAt(0);
            int amt = in.nextInt();
            in.nextLine();

            //I'm so sorry that this is how the code is written, if I redid this it would look more like part2,
            //just without the inner loop for each individual knot
            switch (dir) {
                case 'L':
                    for (int i = 0; i < amt; i++) {
                        headx--;
                        if (Math.abs(tailx - headx) > 1 || Math.abs(taily - heady) > 1) {
                            tailx += sign(headx - tailx);
                            taily += sign(heady - taily);
                        }
                        if (visited.add(getKey(tailx, taily))) count++;

                    }
                    break;
                case 'R':
                    for (int i = 0; i < amt; i++) {
                        headx++;
                        if (Math.abs(tailx - headx) > 1 || Math.abs(taily - heady) > 1) {
                            tailx += sign(headx - tailx);
                            taily += sign(heady - taily);
                        }
                        if (visited.add(getKey(tailx, taily))) count++;

                    }
                    break;
                case 'U':
                    for (int i = 0; i < amt; i++) {
                        heady--;
                        if (Math.abs(tailx - headx) > 1 || Math.abs(taily - heady) > 1) {
                            tailx += sign(headx - tailx);
                            taily += sign(heady - taily);
                        }
                        if (visited.add(getKey(tailx, taily))) count++;

                    }
                    break;
                case 'D':
                    for (int i = 0; i < amt; i++) {
                        heady++;
                        if (Math.abs(tailx - headx) > 1 || Math.abs(taily - heady) > 1) {
                            tailx += sign(headx - tailx);
                            taily += sign(heady - taily);
                        }
                        if (visited.add(getKey(tailx, taily))) count++;
                    }
                    break;
            }
        }
        System.out.println(count);
    }

    public static void part2() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input09.txt"));
        HashSet<Integer> visited = new HashSet<>();
        int count = 0;

        //Creating the rope at 0, 0
        int[][] knots = new int[10][2];
        for (int i= 0; i < 10; i++) {
            knots[i][0] = 0;
            knots[i][1] = 0;
        }

        while (in.hasNextLine()) {
            char c = in.next().charAt(0);
            //See, this way we don't need to write 4 separate for loops
            int[] dir = {0, 0};

            switch(c) {
                case 'U':
                    dir[1] = -1;
                    break;
                case 'L':
                    dir[0] = -1;
                    break;
                case 'R':
                    dir[0] = 1;
                    break;
                case 'D':
                    dir[1] = 1;
                    break;
            }

            int amt = in.nextInt();
            in.nextLine();

            for (int j = 0; j < amt; j++) {
                //Move the head
                knots[0][0] += dir[0];
                knots[0][1] += dir[1];

                for (int i = 1; i < 10; i++) {
                    if (Math.abs(knots[i][0] - knots[i-1][0]) > 1 || Math.abs(knots[i][1] - knots[i-1][1]) > 1) {
                        knots[i][0] += sign(knots[i-1][0] - knots[i][0]);
                        knots[i][1] += sign(knots[i-1][1] - knots[i][1]);
                    }
                }
                if (visited.add(getKey(knots[9][0], knots[9][1]))) count++;
            }
        }
        System.out.println(count);
    }
}
