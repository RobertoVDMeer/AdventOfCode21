import java.awt.*;
import java.util.*;

public class Day5 extends Base {
//  static String[] input = getInputAsString("./src/data/day5-sample.txt");
    static String[] input = getInputAsString("./src/data/day5-input.txt");

  static ArrayList<Point[]> inputAsPoints = new ArrayList<>();
  static Map<String, Integer> pointMap = new HashMap<>();
  static int totalOverlaps = 0;

  public static void solve(CHALLENGE challenge) {
    parseInputAsPoints();
    drawLines(challenge);
    System.out.println(totalOverlaps);
  }


  private static void drawLines(CHALLENGE challenge) {
    Iterator<Point[]> pIter = inputAsPoints.iterator();
    while (pIter.hasNext()) {
      Point[] p = pIter.next();

      if (challenge == CHALLENGE.A
          && p[0].x != p[1].x
          && p[0].y != p[1].y) continue;

      boolean lastOne;
      do {
        lastOne = p[0].equals(p[1]);
        Integer count = pointMap.get(p[0].toString());

        if (count != null) count++;
        else count = 1;
        if (count == 2) totalOverlaps++;

        pointMap.put(p[0].toString(), count);

        if (p[0].x < p[1].x) p[0].move(p[0].x + 1, p[0].y);
        if (p[0].x > p[1].x) p[0].move(p[0].x - 1, p[0].y);
        if (p[0].y < p[1].y) p[0].move(p[0].x, p[0].y + 1);
        if (p[0].y > p[1].y) p[0].move(p[0].x, p[0].y - 1);

      } while (!lastOne);
    }
  }

  private static void parseInputAsPoints() {
    for (int i = 0; i < input.length; i++) {
      if (input[i].equals("->")) {
        ArrayList<Point> pointPair = new ArrayList<>();
        // add the prev and next X and Y as points
        for (int p = i - 1; p < i + 2; p += 2) {
          String[] xy = input[p].split(",");
          pointPair.add(new Point(
              Integer.parseInt(xy[0])
              , Integer.parseInt(xy[1])
          ));
        }
        inputAsPoints.add(pointPair.toArray(Point[]::new));
        i++;
      }
    }
  }

}
