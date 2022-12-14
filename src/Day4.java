import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }
    public static void part1() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input04.txt"));

        int count = 0;

        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] ss = s.split(",");

            int i1 = Integer.parseInt(ss[0].split("-")[0]);
            int i2 = Integer.parseInt(ss[0].split("-")[1]);
            int i3 = Integer.parseInt(ss[1].split("-")[0]);
            int i4 = Integer.parseInt(ss[1].split("-")[1]);

            //Second in first
            if (i3 >= i1 && i4 <= i2) {
                count ++;

            } else if (i1 >= i3 && i2 <= i4) { //First in second
                count ++;
            }
        }

        System.out.println(count);
    }

    public static void part2() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input04.txt"));

        int count = 0;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] ss = s.split(",");

            int i1 = Integer.parseInt(ss[0].split("-")[0]);
            int i2 = Integer.parseInt(ss[0].split("-")[1]);
            int i3 = Integer.parseInt(ss[1].split("-")[0]);
            int i4 = Integer.parseInt(ss[1].split("-")[1]);

            //Second in first
            if (i3 >= i1 && i3 <= i2 || i4 <= i2 && i4 >= i1) {
                count ++;
                //First in second

            } else if (i1 >= i3 && i1 <= i4 || i2 <= i4 && i2 >= i3) {
                count ++;
            }
        }

        System.out.println(count);
    }
}
