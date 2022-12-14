import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {
    public static void main(String[] args) throws FileNotFoundException {
        Directory parent = loadDirectory("src/inputs/input07.txt");

        //Part one
        System.out.println("Part 1: " + parent.sumSizes(100000));
        //Part two
        int removeTarget = parent.getSize() - 40000000;
        System.out.println("Part 2: " + parent.bestDeletion(removeTarget));
    }
    public static Directory loadDirectory(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));

        //First line is hardcoded
        Directory activeDir = new Directory();
        Directory parent = activeDir;
        in.nextLine();

        while (in.hasNextLine()) {

            //$ number - add that file to the current directory
            if (in.hasNextInt()) {
                int i = in.nextInt();
                activeDir.addFile(i);

                in.nextLine();
                continue;
            }

            String s = in.nextLine();

            //$ cd - change the active parent directory to which next directories are added
            if (s.indexOf("$ cd") == 0) {
                String navType = s.substring(5);

                //$ cd ..
                if (navType.equals("..")) {
                    //The active directory becomes the parent
                    activeDir = activeDir.parent;
                } else {
                    //Create a new child directory and make it the new active directory
                    Directory child = new Directory();
                    activeDir.addDirectory(child);
                    activeDir = child;
                }
            }
        }

        return parent;
    }
}

class Directory {
    public Directory parent;
    ArrayList<Directory> subDirs;
    ArrayList<Integer> fileSizes;

    public Directory() {
        parent = null;
        subDirs = new ArrayList<>();
        fileSizes = new ArrayList<>();
    }

    public void addFile(int size) {
        fileSizes.add(size);
    }

    public void addDirectory(Directory d) {
        subDirs.add(d);
        d.setParent(this);
    }

    public int getSize() {
        int size = 0;

        for (Integer i : fileSizes) { size+=i; }
        for (Directory d : subDirs) { size += d.getSize(); }

        return size;
    }

    public void setParent(Directory d) {
        this.parent = d;
    }

    public int sumSizes(int max) {
        int thisSum = 0;
        int res = 0;
        //Return the value of this function for each of the children
        for (Directory dir : subDirs) {
            res += dir.sumSizes(max);
            thisSum += dir.getSize();
        }

        for (Integer size : fileSizes) {
            thisSum += size;
        }

        if (thisSum <= max) res += thisSum;


        return res;
    }

    public int bestDeletion(int removeTarget) {
        int best = Integer.MAX_VALUE;


        for (Directory dir : subDirs) {
            int s = dir.getSize();
            if (s < removeTarget) continue;

            if (s < best) {
                best = s;
            }

            int d = dir.bestDeletion(removeTarget);

            if (d < best && d >= removeTarget) {
                best = d;
            }
        }

        return best;
    }
}
