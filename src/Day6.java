import java.lang.reflect.Array;
import java.util.Arrays;

public class Day6 extends Base {
//  static String[] input = getInputAsString("./src/data/day6-sample.txt");
  static String[] input = getInputAsString("./src/data/day6-input.txt");

  final static byte BIRTHING = 0;
  final static byte RESTING = 6;
  final static byte BORN = 8;

  static long[] school = new long[9];
  static long totalFish = 0;

  public static void solve(CHALLENGE challenge) {
    System.out.println(Arrays.toString(input));
    int days = 0;

    setSchool();

    if(challenge == CHALLENGE.A) days = 80;
    else days = 256;

    for(int day = 0; day < days; day++) passDay();

    System.out.println(totalFish);
  }

  private static void passDay() {
    // Every day, pop out the BIRTHING, shift everything left, then add the
    // BIRTHING to RESTING and BORN
    long births = school[BIRTHING];
    totalFish += births;

    for (int prev = 0, curr = 1; curr < school.length; prev++, curr++) {
      school[prev] = school[curr];
    }
    school[RESTING] += births;
    school[BORN] = births;
  }

  private static void setSchool() {
    String[] strings = input[0].split(",");
    for (int i = 0; i < strings.length; i++) {
      school[Integer.parseInt(strings[i])]++;
      totalFish++;
    }
  }
}
