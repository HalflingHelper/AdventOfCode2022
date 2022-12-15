import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day14 {
    static int boundary = 0;

    //Sand source is 500, 0
    public static void main(String[] args) throws FileNotFoundException
    {
        part1();
        Scanner in = new Scanner(new File("src/inputs/input14.txt"));
        HashSet<Integer> collision = new HashSet<>();


        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] coords = s.split(" -> ");

            String[] start = coords[0].split(",");
            String[] end;
            for (int i = 1; i < coords.length; i++) {
                end = coords[i].split(",");

                //Add an edge
                addEdge(start, end, collision);

                start = end;
            }
        }


        //Actually simulate the sand grains
        int count = 0;
        int grainX = 500;
        int grainY = 0;

        int bound = boundary + 2;

        while (!collides(500, 0, collision)) {
            //Check for collision directly below
            if (!collides(grainX, grainY + 1, collision)) {
                grainY++;
            }
            //Check below left
            else if (!collides(grainX - 1, grainY + 1, collision)) {
                grainX--;
                grainY++;
            }
            //Check below right
            else if (!collides(grainX + 1, grainY + 1, collision)) {
                grainX++;
                grainY++;
            } else {
                count++;
                collision.add(hashPos(grainX, grainY));
                grainX = 500;
                grainY = 0;
            }

            if (grainY==bound) {
                collision.add(hashPos(grainX, grainY));
                collision.add(hashPos(grainX, grainY-1));
                count++;
                grainX = 500;
                grainY = 0;
            }
        }

        System.out.println(count);

    }

    public static void part1() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/inputs/input14.txt"));
        HashSet<Integer> collision = new HashSet<>();


        while (in.hasNextLine()) {
            String s = in.nextLine();
            String[] coords = s.split(" -> ");

            String[] start = coords[0].split(",");
            String[] end;
            for (int i = 1; i < coords.length; i++) {
                end = coords[i].split(",");

                //Add an edge
                addEdge(start, end, collision);

                start = end;
            }
        }


        //Actually simulate the sand grains
        int count = 0;
        int grainX = 500;
        int grainY = 0;

        while (grainY <= boundary) {
            //Check for collision directly below
            if (!collides(grainX, grainY + 1, collision)) {
                grainY++;
            }
            //Check below left
            else if (!collides(grainX - 1, grainY + 1, collision)) {
                grainX--;
                grainY++;
            }
            //Check below right
            else if (!collides(grainX + 1, grainY + 1, collision)) {
                grainX++;
                grainY++;
            } else {
                count++;
                collision.add(hashPos(grainX, grainY));
                grainX = 500;
                grainY = 0;
            }
        }

        System.out.println(count);
    }

    public static void addEdge(String[] start, String[] end, HashSet<Integer> collision) {
        int sx = Integer.parseInt(start[0]);
        int sy = Integer.parseInt(start[1]);
        int ex = Integer.parseInt(end[0]);
        int ey = Integer.parseInt(end[1]);

        int dx = Integer.compare(ex - sx, 0);
        int dy = Integer.compare(ey - sy, 0);

        for (; (sx != ex + dx || dx == 0) && (sy != ey + dy || dy == 0); sx+=dx, sy+=dy) {
            collision.add(hashPos(sx, sy));
        }

        if (ey > boundary) { boundary = ey; }
    }

    public static boolean collides(int x, int y, HashSet<Integer> collision) {
        return collision.contains(hashPos(x, y));
    }

    public static int hashPos(int x, int y) {
        return y + (x+y)*(x+y+1)/2;
    }
}

