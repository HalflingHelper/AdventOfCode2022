import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day5 {
    static int numRows = 8;
    static int numStacks = 0;

    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static  void part1() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input5.txt"));

        ArrayList<char[]> stacks = loadStacks(in);


        while (in.hasNextLine()) {
            //Parse through lines
            in.next();
            int amt = in.nextInt();
            in.next();
            int src = in.nextInt() - 1;
            in.next();
            int dest = in.nextInt() - 1;
            in.nextLine();

            int i = 0;

            //Stack up the chars that need to be inserted
            char[] cs = new char[amt];

            for (char[] arr : stacks) {
                if (arr[src] != 0) {
                    cs[i] = arr[src];
                    arr[src] = 0;
                    i++;
                    if (i>=cs.length) break;
                }
            }

            int insertHeight = -1;

            for (char[] arr : stacks) {
                if (arr[dest] == 0) {
                    insertHeight ++;
                }
            }

            i = 0;
            for (int j = insertHeight; i < cs.length; j--, i++) {
                if (j < 0) {
                    j++;
                    stacks.add(0, new char[numStacks]);
                }

                char[] css = stacks.get(j);
                css[dest] = cs[i];
            }
        }

        //Figure out the tops
        char[] res = new char[numStacks];

        for (char[] arr : stacks) {
            for (int i = 0; i < numStacks; i++) {
                if (res[i] == 0) res[i] = arr[i];
            }
        }

        System.out.println(Arrays.toString(res));
    }

    public static  void part2() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input5.txt"));

        ArrayList<char[]> stacks = loadStacks(in);


        while (in.hasNextLine()) {
            //Parse through lines
            in.next();
            int amt = in.nextInt();
            in.next();
            int src = in.nextInt() - 1;
            in.next();
            int dest = in.nextInt() - 1;
            in.nextLine();

            int i = 0;

            //Stack up the chars that need to be inserted
            char[] cs = new char[amt];

            for (char[] arr : stacks) {
                if (arr[src] != 0) {
                    cs[i] = arr[src];
                    arr[src] = 0;
                    i++;
                    if (i>=cs.length) break;
                }
            }

            int insertHeight = -1;

            for (char[] arr : stacks) {
                if (arr[dest] == 0) {
                    insertHeight ++;
                }
            }

            i--;
            for (int j = insertHeight; i >= 0; j--, i--) {
                if (j < 0) {
                    j++;
                    stacks.add(0, new char[numStacks]);
                }

                char[] css = stacks.get(j);
                css[dest] = cs[i];
            }
        }

        //Figure out the tops
        char[] res = new char[numStacks];

        for (char[] arr : stacks) {
            for (int i = 0; i < numStacks; i++) {
                if (res[i] == 0) res[i] = arr[i];
            }
        }

        System.out.println(Arrays.toString(res));
    }
    public static ArrayList<char[]> loadStacks(Scanner in) {
        String s = in.nextLine();
        numStacks = (s.length() + 1) / 4;

        //Number of rows is hardcoded :(
        ArrayList<char[]> res = new ArrayList<>();

        while (s.charAt(1) != '1') {
            char[] thisRow = new char[numStacks];

            for (int col = 0; col < numStacks; col++) {
                char c = s.charAt(col * 4 + 1);

                if (c == ' ') {
                    thisRow[col] = 0;
                } else {
                    thisRow[col] = c;
                }
            }
            res.add(thisRow);

            s = in.nextLine();
        }
        return res;
    }
}
