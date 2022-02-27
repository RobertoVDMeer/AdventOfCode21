import java.util.*;

public class Day11 extends Base {
  static String[] input = getInputAsString("./src/data/day11-sample.txt");
  //  static String[] input = getInputAsString("./src/data/day11-input.txt");
  static int[][] matrix;

  public static void solve(CHALLENGE challenge) {
    System.out.println(Arrays.toString(input));
    setMatrix();
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
}
