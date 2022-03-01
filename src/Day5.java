import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Day5 extends Base {
  static String[] input = getInputAsString("./src/data/day5-sample.txt");
//  static String[] input = getInputAsString("./src/data/day5-input.txt");

  public static void solve(CHALLENGE challenge) {
    System.out.println(Arrays.toString(input));

    ArrayList<Point[]> inputAsPoints = parseInputAsPoints();

    HashSet<Point> pointSet = new HashSet<>();
    int totalOverlaps = 0;

    totalOverlaps += fillPointSet(pointSet, input);

  }

  private static ArrayList<Point[]> parseInputAsPoints() {
    ArrayList<Point[]> points = new ArrayList<>();
    for (int i = 0; i < input.length; i++) {
      if(input[i].equals("->")) {
        ArrayList<Point> pointPair = new ArrayList<>();
        // add the prev and next X and Y as points
        for (int p = i - 1; p < i + 2; p += 2) {
          String[] xy = input[p].split(",");
          pointPair.add(new Point(
              Integer.parseInt(xy[0])
              , Integer.parseInt(xy[0])
          ));
          points.add(pointPair.toArray(Point[]::new));
        }
        i++;
      }
    }
    /*
    final int MOD_X_1 = 1;
    final int MOD_X_2 = 4;
    for (int i = 0; i < input.length; i++) {
      int mod = (i + 1) % 5;
      if (mod == MOD_X_1 || mod == MOD_X_2) {
        int x = Integer.parseInt(input[i]);
        int y = Integer.parseInt(input[i + 1]);
        points.add(new Point(x, y));
      }
    }

     */
    return points;
  }

  private static int fillPointSet(HashSet<Point> pointSet, String[] input) {
    int overlaps = 0;
    return overlaps;
  }
}
