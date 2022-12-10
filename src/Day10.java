/* Advent of Code Day 10: Cathode-Ray Tube
 *
 * Today was a relatively quick and fun problem. For both parts, it was just a matter of stepping through the input and
 * making the required changes to the clock and register values, and then doing something based on that (either writing
 * to the screen, or incrementing the total signal strength value). This means the code for both parts is very similar,
 * and I could have probably done both parts in a single pass, and applying both the sigStr() and getPixels() method at
 * the same time. I left it all split up though, so it is what it is.
 *
 * Personal Stats:
 *   ----Part 1-----   ----Part 2-----
 *       Time   Rank       Time   Rank
 *   00:15:12   2496   00:30:51   2345
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.print("Part 1: ");
        part1();
        System.out.println("Part 2: ");
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input10.txt"));

        int res = 0;

        int clock = 0;
        //Impossible to remember that the register starts at 1 🙃
        int X = 1;

        while (in.hasNextLine()) {
            String s = in.next();

            if (s.equals("noop")) {
                res += sigStr(clock, X);
                clock++;
            } else {
                res += sigStr(clock, X);
                clock++;
                res += sigStr(clock, X);
                clock++;

                // Changing the register after the clock increments
                int arg = in.nextInt();
                X += arg;
            }
            in.nextLine();
        }
        System.out.println(res);
    }

    public static int sigStr(int clock, int reg) {
        if (clock <= 220 && (clock - 20) % 40 == 0) {
            return clock * reg;
        }

        return 0;
    }

    public static void part2() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input10.txt"));

        StringBuilder screen = new StringBuilder();

        int clock = 0;
        int X = 1;

        while (in.hasNextLine()) {
            String s = in.next();

            if (s.equals("noop")) {
                screen.append(getPixels(clock, X));
                clock++;
            } else {
                screen.append(getPixels(clock, X));
                clock++;
                screen.append(getPixels(clock, X));
                clock++;

                X += in.nextInt();
            }
            in.nextLine();
        }
        System.out.println(screen);
    }

    public static String getPixels(int clock, int reg) {
        String res = ".";

        if (Math.abs(clock % 40 - reg) <= 1) {
            res = "#";
        }

        if ((clock + 1) % 40 == 0) {
            res += "\n";
        }
        return res;
    }
}
