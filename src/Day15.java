import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;


public class Day15 {
    public static void main(String[] args) throws FileNotFoundException {
        //Load in the data
        Scanner in = new Scanner(new File("src/inputs/input15.txt"));

        int[][] sources = new int[32][2];
        int[][] beacons = new int[32][2];

        int i = 0;
        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(" ");
            sources[i][0] = Integer.parseInt(s[2].substring(2, s[2].length()-1));
            sources[i][1] = Integer.parseInt(s[3].substring(2, s[3].length()-1));

            beacons[i][0] = Integer.parseInt(s[8].substring(2, s[8].length()-1));
            beacons[i][1] = Integer.parseInt(s[9].substring(2));

            i++;
        }

        part1(sources, beacons);
        part2(sources, beacons);
    }

    //Assumes sources and beacons are the same length
    public static void part1(int[][] sources, int[][]beacons) {
        final int targetRow = 2_000_000;
        //Integer arrays as [start, amt]
        ArrayList<int[]> coverage = new ArrayList<>();

        for (int i = 0; i < sources.length; i++) {
            int dist = Math.abs(sources[i][0] - beacons[i][0]) + Math.abs(sources[i][1] - beacons[i][1]);
            int squaresOnTargetRow = dist - Math.abs(sources[i][1] - targetRow);

            if (squaresOnTargetRow >= 0) {
                coverage.add(new int[]{sources[i][0] - squaresOnTargetRow, squaresOnTargetRow * 2 + 1});
            }
        }

        // Sort in order of x coordinates
        coverage.sort(Comparator.comparingInt(o -> o[0]));

        ArrayList<int[]> simpleCoverage = new ArrayList<>();

        int[] arr1 = coverage.get(0);
        //Remove overlaps
        for (int i = 1; i < coverage.size(); i++) {
            int[] arr2 = coverage.get(i);
            int overlap = arr1[0] + arr1[1] - arr2[0];

            if (overlap >= 0) {
                if (arr2[0] + arr2[1] > arr1[0] + arr1[1]) {
                    arr1[1] = arr1[1] + arr2[1] - overlap;
                }
            } else {
                simpleCoverage.add(arr1);
                arr1 = arr2;
            }
        }
        //Add the last spot
        simpleCoverage.add(arr1);
        int count = 0;
        //Sum the number of squares
        for (int[] arr : simpleCoverage) {
            count += arr[1];

            //Make sure not to add the same beacon more than once
            HashSet<Integer> beaconMarks = new HashSet<>();

            //Subtract any slots with beacons in them
            for (int[] beacon : beacons) {
                if (beacon[0] >= arr[0] && beacon[0] < arr[0] + arr[1] && beacon[1] == targetRow && !beaconMarks.contains(beacon[0])) {
                    count--;
                    beaconMarks.add(beacon[0]);
                }
            }
        }
        System.out.println(count);
    }

    public static void part2(int[][] sources, int[][] beacons) {
        //Creating this new way to store the data (unnecessary but easier)
        // In [] for x, y, r
        int[][] zones = new int[32][3];

        for (int i = 0; i < sources.length; i++) {
            int dist = Math.abs(sources[i][0] - beacons[i][0]) + Math.abs(sources[i][1] - beacons[i][1]);
            zones[i] = new int[] {sources[i][0], sources[i][1], dist};
        }

        long res = -1;
        //Check only the points on the borders of zones
        for (int[] zone : zones) {
            int x = zone[0];
            int y = zone[1];
            int r = zone[2] + 1;
            for (int i = 0; i <= r; i++) {
                if (res >= 0) { break; }
                //Top Left
                if (!isInZone(x - i, y - (r-i), zones)) {
                    res = 4000000L * (x-i) + y - (r-i);
                    break;
                }
                //Bottom Left
                if (!isInZone(x - i, y + (r-i), zones)) {
                    res = 4000000L * (x-i) + y + (r-i);
                    break;
                }
                //Top Right
                if (!isInZone(x + i, y - (r-i), zones)) {
                    res = 4000000L * (x+i) + y - (r-i);
                    break;
                }
                //Bottom Right
                if (!isInZone(x + i, y + (r-i), zones)) {
                    res = 4000000L * (x+i) + y + (r-i);
                    break;
                }
            }
        }
        System.out.println(res);
    }

    private static boolean isInZone(int x, int y, int[][] zones) {
        if (y < 0 || y > 4_000_000 || x < 0 || x > 4_000_000) { return true; }
        for (int[] zone : zones) {
            int dist = Math.abs(x - zone[0]) + Math.abs(y - zone[1]);
            if (dist <= zone[2]) {
                return true;
            }
        }
        return false;
    }
}
