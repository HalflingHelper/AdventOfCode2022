import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        part1();
        part2();
    }

    public static void part1() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input3.txt"));

        int count = 0;

        while (in.hasNextLine()) {
            String s = in.nextLine();
            String s1 = s.substring(0, s.length() / 2);
            String s2 = s.substring(s.length() / 2);

            for (int i = 0; i < s.length() / 2; i++) {
                if (s2.indexOf(s1.charAt(i)) >= 0) {
                    int c = s1.charAt(i);

                    if ('a' <= c && c <= 'z') {
                        count += c - 96;
                    } else {
                        count += c - 64 + 26;
                    }
                    break;
                }
            }
        }

        System.out.println(count);
    }

    public static void part2() throws FileNotFoundException {
        Scanner in = new Scanner(new File("input3.txt"));

        int j = 2;
        int count = 0;

        String s1, s2, s3;

        while (in.hasNextLine()) {
            s1 = in.nextLine();
            s2 = in.nextLine();
            s3 = in.nextLine();
            
            for (int i = 0; i < s1.length(); i++) {
                char c = s1.charAt(i);
                if (s2.indexOf(c) >= 0 && s3.indexOf(c) >= 0) {
                    if ('a' <= c && c <= 'z') {
                        count += c - 96;
                    } else {
                        count += c - 64 + 26;
                    }
                    break;
                }
            }
            j++;
        }
        System.out.println(count);
    }
}

