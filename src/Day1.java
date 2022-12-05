import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        File input = new File("src/inputs/input.txt");
        Scanner in = new Scanner(input);

        int cur = 0;
        int max = 0;


        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (s.equals("")) {
                max = Math.max(cur, max);
                cur = 0;
            } else {
                int i = Integer.parseInt(s);
                cur += i;
            }
        }

        System.out.println(max);
    }

    public static void part2() throws FileNotFoundException {
        File input = new File("src/inputs/input.txt");
        Scanner in = new Scanner(input);

        int cur = 0;
        int max = 0;

        int[] maxes = new int[3];

        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (s.equals("")) {
                for (int i = 0; i < 3; i ++) {
                    if (cur > maxes[i]) {
                        int temp = cur;
                        cur = maxes[i];
                        maxes[i] = temp;
                    }
                }
                cur = 0;
            } else {
                int i = Integer.parseInt(s);
                cur += i;
            }
        }


        System.out.println(maxes[0] + maxes[1] + maxes[2]);
    }
}
