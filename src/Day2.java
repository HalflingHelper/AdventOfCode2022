import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        File input = new File("src/inputs/input02.txt");
        Scanner in = new Scanner(input);

        int score = 0;

        while (in.hasNextLine()) {
            String l = in.nextLine();
            char b = l.charAt(0);
            char a   = l.charAt(2);

            if (a == 'X') { //Rock
                score += 1;
                if (b == 'A') { //Draw to rock
                    score += 3;
                }  else if (b == 'C') { //Lose to Scissors
                    score += 6;
                }

            } else if (a == 'Y') { //Paper
                score += 2;
                if (b == 'A') { //Beats rock
                    score += 6;
                } else if (b == 'B') { //Draws paper
                    score += 3;
                }
            } else if (a == 'Z') { //Scissors
                score += 3;
                if (b == 'C') { //Draw scissors
                    score += 3;
                } else if (b == 'B') { //Beat paper
                    score += 6;
                }
            }
        }

        System.out.println(score);
    }
    public static void part2() throws FileNotFoundException {
        File input = new File("src/inputs/input02.txt");
        Scanner in = new Scanner(input);

        int score = 0;

        while (in.hasNextLine()) {
            String l = in.nextLine();
            char b = l.charAt(0);
            char a   = l.charAt(2);

            if (a == 'X') {
                if (b == 'B') {
                    score += 1;
                }  else if (b == 'C') {
                    score += 2;
                } else if (b == 'A') {
                    score += 3;
                }

            } else if (a == 'Y') {
                score += 3;
                if (b == 'A') {
                    score += 1;
                } else if (b == 'B') {
                    score += 2;
                } else if (b == 'C') {
                    score += 3;
                }
            } else if (a == 'Z') {
                score += 6;
                if (b == 'C') {
                    score += 1;
                } else if (b == 'B') {
                    score += 3;
                } else if (b == 'A') {
                    score += 2;
                }
            }
        }

        System.out.println(score);
    }
}
