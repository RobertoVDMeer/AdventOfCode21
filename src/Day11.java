import java.util.*;

public class Day11 extends Base {
  static String[] input = getInputAsString("./src/data/day11-sample.txt");
  //  static String[] input = getInputAsString("./src/data/day11-input.txt");

  enum checkDirection {NW, N, NE, W, C, E, SW, S, SE}
  static HashMap<checkDirection, checkDirection> nextDirection = new HashMap<>();
  static int[][] matrix;

  public static void solve(CHALLENGE challenge) {
    int flashes = 0;
    System.out.println(Arrays.toString(input));
    setMatrix();
    setNextDirection();
    printMatrix();
    raiseEnergy();
    printMatrix();
    flashes += checkFlashes();
    printMatrix();
    System.out.println(flashes);
  }

  private static void setNextDirection() {
    nextDirection.put(checkDirection.C, checkDirection.NW);
    nextDirection.put(checkDirection.NW, checkDirection.N);
    nextDirection.put(checkDirection.N, checkDirection.NE);
    nextDirection.put(checkDirection.NE, checkDirection.W);
    nextDirection.put(checkDirection.W, checkDirection.E);
    nextDirection.put(checkDirection.E, checkDirection.SW);
    nextDirection.put(checkDirection.SW, checkDirection.S);
    nextDirection.put(checkDirection.S, checkDirection.SE);
  }


  private static int checkFlashes() {
    int flashes = 0;
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[r].length; c++) {
        if (matrix[r][c] > 8) {
          flashes += flashFlood(r, c, checkDirection.C);
        }
      }
    }
    return flashes;
  }

  private static int flashFlood(int r, int c, checkDirection direction) {
    int flashes = 0;

    if (matrix[r][c] > 8) {
      flashes++;
      matrix[r][c] = -1;
    } else if (matrix[r][c] != -1) {
      matrix[r][c]++;
    }
    System.out.println(direction);
    switch (direction) {
      case C:
        if (r - 1 > 0 && c - 1 > 0) {
          flashFlood(r - 1, c - 1, nextDirection.get(direction));
        }
        break;
      case NW:
      case SW:
      case N:
      case S:
        if (c + 1 < matrix[r].length) {
          flashFlood(r, c + 1, nextDirection.get(direction));
        }
        break;
      case W:
        if (c + 2 < matrix[r].length) {
          flashFlood(r, c + 2, nextDirection.get(direction));
        }
        break;
      case E:
      case NE:
        if (r + 1 < matrix.length && c - 2 > 0) {
          flashFlood(r + 1, c - 2, nextDirection.get(direction));
        }
        break;
      case SE:
        // this is the last one, showing the case for sanity
    }

    return flashes;
  }

  private static void raiseEnergy() {
    for (int[] r : matrix) {
      for (int c = 0; c < r.length; c++) {
        r[c]++;
      }
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

  private static void printMatrix() {
    for (int[] r : matrix) {
      System.out.println(Arrays.toString(r));
    }
    System.out.println("-------------");
  }
}
