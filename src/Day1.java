import java.util.Arrays;

public class Day1 extends Base {
  public static void solve(CHALLENGE challenge) {
//    int[] readings = getInputAsInt("./src/data/day1-sample.txt");
    int[] readings = Base.getInputAsInt("./src/data/day1-input.txt");
    int count = 0;
    int pointerAndLookback = (challenge == CHALLENGE.A)? 1 : 3;

    for (int i = pointerAndLookback; i < readings.length; i++) {
      count += readings[i] > readings[i - pointerAndLookback] ? 1 : 0;
    }
    System.out.println(Arrays.toString(readings));
    System.out.println(count);
  }
}
