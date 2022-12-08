import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        /*
        These methods are both the same, and are also terribly inefficient because they do every comparison in the
        current input characters to check if there are any duplicates. That said, that code ultimately was easier to write
        that trying to keep a running total of duplicate numbers, which I couldn't be bothered to do at 3 AM.
        */
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input6.txt"));
        String dataStream = in.nextLine();

        char[] cur = new char[4];

        for (int i = 0; i < dataStream.length(); i++) {

            char c = dataStream.charAt(i);
            cur[i%4] = c;

            if (i >= 3) {
                boolean dup = false;
                for (int j = 0; j < 4; j++) {
                    for (int k = j+1; k< 4; k++) {
                        if (cur [k] == cur[j]) {
                            dup = true;
                        }
                    }
                }

                if (!dup) {
                    System.out.println(i+1);
                    return;
                }
            }

        }

    }

    public static void part2() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input6.txt"));
        String dataStream = in.nextLine();

        char[] cur = new char[14];

        for (int i = 0; i < dataStream.length(); i++) {

            char c = dataStream.charAt(i);
            cur[i%14] = c;


            if (i >= 13) {
                boolean dup = false;
                for (int j = 0; j < 14; j++) {
                    for (int k = j+1; k< 14; k++) {
                        if (cur [k] == cur[j]) {
                            dup = true;
                        }
                    }
                }

                if (!dup) {
                    System.out.println(i+1);
                    return;
                }
            }

        }

    }
}
