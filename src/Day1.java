import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws java.io.FileNotFoundException {
        File input = new File("input.txt");
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
