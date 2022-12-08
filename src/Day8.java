import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input8.txt"));
        int[][] trees = new int[99][99];

        //Load the trees into an array for use by both parts
        int rowi = 0;
        while (in.hasNextLine()) {
            String l = in.nextLine();

            for (int i = 0; i < l.length(); i++) {
                 trees[rowi][i] = l.charAt(i) - 48;
            }
            rowi++;
        }

        //Doing the actual problem
        part1(trees);
        part2(trees);

    }

    public static void part1(int[][] trees) {
        //Boolean array to track which trees have been counted
        boolean[][] visible = new boolean[99][99];

        int count = 0;

        //Tracking the vertical heights of trees
        int[] vertBuffer = new int[99];
        Arrays.fill(vertBuffer, -1);

        //Need to do two 'sweeps' of the forest
        //TL -> BR
        for (int row = 0; row < 99; row++) {
            int max = -1;
            for (int col = 0; col < 99; col++) {
                int val = trees[row][col];
                if (!visible[row][col]) {
                    if (val > max || val > vertBuffer[col]) {
                        count++;
                        visible[row][col] = true;
                    }
                }

                vertBuffer[col] = Math.max(vertBuffer[col], val);
                max = Math.max(max, val);
            }
        }

        //BR -> TL
        Arrays.fill(vertBuffer, -1);
        for (int row = 98; row >=0; row--) {
            int max = -1;
            for (int col = 98; col >= 0; col--) {
                int val = trees[row][col];
                if (!visible[row][col]) {
                    if (val > max || val > vertBuffer[col]) {
                        count++;
                        visible[row][col] = true;
                    }
                }

                vertBuffer[col] = Math.max(vertBuffer[col], val);
                max = Math.max(max, val);
            }
        }
        System.out.println(count);
    }

    public static void part2(int[][] trees) {
        int best = 0;

        for (int row = 0; row < 99; row++) {
            for (int col = 0; col < 99; col++) {
                best = Math.max(getScenicScore(row, col, trees), best);
            }
        }

        System.out.println(best);
    }

    private static int getScenicScore(int row, int col, int[][] trees) {
        int orig = trees[row][col];

        int left = 1;
        int right = 1;
        int up = 1;
        int down = 1;

        for (int i = col-1; i > 0 && trees[row][i] < orig; i--) left++;

        for (int i = col+1; i < 98 && trees[row][i] < orig; i++) right++;

        for (int i = row+1; i < 98 && trees[i][col] < orig; i++) down++;

        for (int i = row-1; i > 0 && trees[i][col] < orig; i--) up++;

        return left * right * up * down;
    }
}
