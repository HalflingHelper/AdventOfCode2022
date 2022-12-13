import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 *  Day 13: Distress Signal
 *
 *    --------Part 1--------   --------Part 2--------
 *        Time   Rank  Score       Time   Rank  Score
 *    14:55:25  25548      0   15:05:46  24360      0
 *
 * Today made me really wish I was in python instead of java. That said, it was fun to code the recursive solution until
 * I hit a wall related to the fact that I was trying to write compare() as a boolean method, which caused some issues
 * that I think were related to numbers being equal and things. Once I ironed out that kink, I found that I had a comparator
 * function ready to go for part 2, making that a very quick experience
 */

public class Day13 {
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("src/inputs/input13.txt"));

        int count = 0;
        int i = 1;

        //Keeping all of the strings in a list for part 2
        ArrayList<String> all = new ArrayList<>();
        //Divider packets
        all.add("[[2]]");
        all.add("[[6]]");

        while (in.hasNextLine()) {
            String s1 = in.nextLine();
            String s2 = in.nextLine();

            //Part 1 stuff
            if (compare(s1, s2) >= 0) {
                count += i;
            }

            //Adding to the list for part 2
            all.add(s1);
            all.add(s2);
            //Dividers in between lines
            if (in.hasNextLine()) { in.nextLine(); }
            i++;
        }

        System.out.println("Part 1 : " + count);

        //Have to reverse my comparator lmao
        all.sort((o1, o2) -> -1 * compare(o1, o2));

        int i1 = all.indexOf("[[2]]") + 1;
        int i2 = all.indexOf("[[6]]") + 1;
        System.out.println("Part 2 : "+ (i1 * i2));
    }

    //Returns 0 if equal, 1 is s1 is less, -1 if s2 is less
    public static int compare(String s1, String s2) {
        //Remove first and last list brackets, and convert to an array
        String[] a1 = toList(s1);
        String[] a2 = toList(s2);

        for (int i = 0; i < a1.length && i < a2.length; i++) {
            String v1 = a1[i];
            String v2 = a2[i];

            if (v1.equals("")) {
                if (v2.equals("")) continue;
                return 1;
            }

            if (v2.equals("")) { return -1; }

            if (v1.charAt(0) == '[' && v2.charAt(0) == '[') {
                int c = compare(v1, v2);
                if (c!= 0) return c;
            } else if (v1.charAt(0) == '[') {
                int c = compare(v1, "["+v2+"]");
                if (c!= 0) return c;
            } else if (v2.charAt(0) == '[') {
                int c = compare("[" + v1 + "]", v2);
                if (c!= 0) return c;
            } else {
                int i1 = Integer.parseInt(v1);
                int i2 = Integer.parseInt(v2);

                if (i2 < i1) { return -1; }
                if (i1 < i2) { return 1; }
            }
        }

        if (a1.length == a2.length) return 0;
        return a1.length < a2.length ? 1 : -1;
    }

    public static String[] toList(String s) {
        ArrayList<String> l = new ArrayList<>();
        int depth = 0;
        int start = 1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') { depth++;}
            else if (s.charAt(i) == ']') { depth--; }
            else if (depth == 1 && s.charAt(i) ==',') {
                l.add(s.substring(start, i));
                start = i+1;
            }
        }
        //Add the last element (avoiding last brace)
        l.add(s.substring(start, s.length()-1));
        return l.toArray(new String[0]);
    }
}
