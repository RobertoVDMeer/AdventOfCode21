import java.text.MessageFormat;
import java.util.*;

public class Day11 extends Base {
      static String[] input = getInputAsString("./src/data/day11-sample.txt");
//  static String[] input = getInputAsString("./src/data/day11-input.txt");

  enum positionGrid {NW, N, NE, W, C, E, SW, S, SE}

  static HashMap<positionGrid, positionGrid> nextPosition = new HashMap<>();
  static int[][] matrix;
  static int flashes;

  public static void solve(CHALLENGE challenge) {
    flashes = 0;
    System.out.println(Arrays.toString(input));
    int steps = 100;
    setMatrix();
    setNextPositionMap();

    for (int i = 0; i < steps; i++) {
      raiseEnergy();
      checkFlashes();
    }
    printMatrix();

    System.out.println(MessageFormat.format("Flashes after {0} steps: {1}"
        , steps, flashes));
  }

  private static void raiseEnergy() {
    for (int[] r : matrix) {
      for (int c = 0; c < r.length; c++) {
        r[c]++;
      }
    }
  }

  private static void checkFlashes() {
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[r].length; c++) {
        if (matrix[r][c] > 9) {
          flashes++;
          matrix[r][c] =0;
          flashSplash(r, c, positionGrid.C);
        }
      }
    }
  }

  private static void flashSplash(int r, int c, positionGrid pos) {
    if (pos != positionGrid.C
        && r >= 0 && r < matrix.length
        && c >= 0 && c < matrix[r].length) {

      if (matrix[r][c] != 0) {
        matrix[r][c]++;
      }

      if (matrix[r][c] > 9) {
        flashes++;
        matrix[r][c] = 0;
        flashSplash(r, c, positionGrid.C);
      }
    }

    switch (pos) {
      case C:
        flashSplash(r - 1, c - 1, nextPosition.get(pos));
        break;
      case NW:
      case SW:
      case N:
      case S:
        flashSplash(r, c + 1, nextPosition.get(pos));
        break;
      case W:
        flashSplash(r, c + 2, nextPosition.get(pos));
        break;
      case E:
      case NE:
        flashSplash(r + 1, c - 2, nextPosition.get(pos));
        break;
      case SE:
        // this is the last one, showing the case for sanity
    }
  }

  private static void setMatrix() {
    // array length for rows, StringLength for cols
    matrix = new int[input.length][input[0].length()];

    for (int r = 0; r < input.length; r++) {
      for (int c = 0; c < input[r].length(); c++) {
        matrix[r][c] = Integer.parseInt(input[r], c, c + 1, 10);
      }
    }
  }

  private static void setNextPositionMap() {
    nextPosition.put(positionGrid.C, positionGrid.NW);
    nextPosition.put(positionGrid.NW, positionGrid.N);
    nextPosition.put(positionGrid.N, positionGrid.NE);
    nextPosition.put(positionGrid.NE, positionGrid.W);
    nextPosition.put(positionGrid.W, positionGrid.E);
    nextPosition.put(positionGrid.E, positionGrid.SW);
    nextPosition.put(positionGrid.SW, positionGrid.S);
    nextPosition.put(positionGrid.S, positionGrid.SE);
    nextPosition.put(positionGrid.SE, positionGrid.C);
  }

  private static void printMatrix() {
    for (int[] r : matrix) {
      System.out.println(Arrays.toString(r));
    }
    System.out.println("-------------");
  }
}
