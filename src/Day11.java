/*
 * Day 11: Monkey in the Middle
 *    --------Part 1--------   --------Part 2--------
 *        Time   Rank  Score       Time   Rank  Score
 *    00:26:34   1255      0   14:04:14  29660      0
 *
 * Today was certainly a day. I finished part 1 somewhat quickly but didn't have the brainpower to do part 2 at 1:00 AM,
 * so I left it for the daytime. Once I arrived at tracking every remainder concurrently, it was just a matter of trying
 * to implement it, and spending way too much time figuring out that the monkey business value was overflowing. My final
 * solution is somewhat messy, because the values of the test numbers are hardcoded, and the methods for part 1 and part
 * 2 use a lot of similar code that got rewritten. Additionally, I think I didn't need to create the MonkeyManager class
 * as returning just an ArrayList from loadMonkeyArray() could have worked just as well.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        //Part 1
        System.out.print("Part 1: ");
        MonkeyManager ms1 = loadMonkeyArray();
        for (int i = 0; i < 20; i++) {
            ms1.doRound1();
        }
        getMonkeyBusiness(ms1);

        //Part 2
        System.out.print("Part 2: ");
        MonkeyManager ms2 = loadMonkeyArray();
        for (int i = 0; i < 10_000; i++) {
            ms2.doRound2();
        }
        getMonkeyBusiness(ms2);

    }

    public static void getMonkeyBusiness(MonkeyManager ms) {
        long most = 0;
        long secondMost = 0;

        for (Monkey monkey : ms.monkeys) {
            if (monkey.numInspected > most) {
                long temp = most;
                most = monkey.numInspected;
                if (temp > secondMost) secondMost = temp;
            } else if (monkey.numInspected > secondMost) {
                secondMost = monkey.numInspected;
            }
        }
        System.out.println(most * secondMost);
    }

    public static MonkeyManager loadMonkeyArray() throws FileNotFoundException{
        Scanner in = new Scanner(new File("src/inputs/input11.txt"));
        MonkeyManager ms = new MonkeyManager();

        while (in.hasNextLine()) {
            in.nextLine();
            Monkey m = new Monkey();

            // Items
            ArrayList<int[]> items = new ArrayList<>();
            String s = in.nextLine();
            for (String n : s.split(":")[1].split(",")) {
                int[] item = new int[8];
                int i = Integer.parseInt(n.substring(1));
                for (int k = 0; k < 8; k++) {
                    item[k] = i;
                }
                items.add(item);
            }
            m.items = items;

            //Multiply, Square, and Modifier
            s = in.nextLine();
            m.multiply = s.contains("*");
            m.square = false;
            try {
                m.modifier = Integer.parseInt(s.substring(25));
            }
            catch (Exception e) { m.square = true; }

            //These are hardcoded, so we can skip the line entirely
            in.nextLine();
            m.trueDest = Integer.parseInt(in.nextLine().substring(29));
            m.falseDest = Integer.parseInt(in.nextLine().substring(30));

            ms.addMonkey(m);
            //Clearing the line of empty space in between monkeys
            if (in.hasNextLine())
                in.nextLine();
        }
        return ms;
    }
}

class MonkeyManager {
    static final int[] modBases = {13, 3, 7, 2, 19, 5, 11, 17};

    Monkey[] monkeys  = new Monkey[8];
    int count = 0;


    public void addMonkey(Monkey m) {
        monkeys[count] = m;
        count++;
    }

    public void doRound1() {
        for (int j = 0; j < monkeys.length; j++) {
            Monkey m = monkeys[j];
            while (!m.items.isEmpty()) {
                int[] worry = m.items.remove(0);
                m.numInspected++;

                int w = worry[0];
                worry[0] = m.doOperation(w) / 3;

                if (worry[0] % modBases[j] == 0) {
                    monkeys[m.trueDest].items.add(worry);
                } else {
                    monkeys[m.falseDest].items.add(worry);
                }
            }
        }
    }

    public void doRound2() {
        int[] worry;
        for (int j = 0; j < 8; j++) {
            Monkey m = monkeys[j];
            while (!m.items.isEmpty()) {
                m.numInspected++;
                worry = m.items.remove(0);

                for (int k = 0; k < 8; k++) {
                    worry[k] = m.doOperation(worry[k]) % modBases[k];
                }

                if (worry[j] == 0) {
                    monkeys[m.trueDest].items.add(worry);
                } else {
                    monkeys[m.falseDest].items.add(worry);
                }
            }
        }
    }
}


class Monkey {
    ArrayList<int[]> items;
    boolean multiply;
    boolean square;
    int modifier;
    int falseDest;
    int trueDest;
    long numInspected = 0;

    public int doOperation(int n) {
        if (square) return n*n;
        if (multiply) return n*modifier;
        return n+modifier;
    }
}


