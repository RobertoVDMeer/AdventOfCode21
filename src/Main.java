import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
  public static void main(String args[]) throws FileNotFoundException {
    day1(false);
  }

  public static void day1(boolean challengeA) {
    int[] readings = getInputAsInt("./src/data/day1-sample.txt");
//    int[] readings = getInputAsInt("./src/data/day1-input.txt");
    int count = 0;
    int pointerAndLookback = challengeA ? 1 : 3;

    for (int i = 1; i < readings.length; i++) {
      count += readings[i] > readings[i - 1] ? 1 : 0;
    }
    System.out.println(Arrays.toString(readings));
    System.out.println(count);
  }

  private static int[] getInputAsInt(String filePath) {
    String[] input = getInputAsString(filePath);
    return convertInputToInt(input);
  }

  private static String[] getInputAsString(String filePath) {
    ArrayList<String> content = new ArrayList<String>();
    Scanner scanner = null;

    try {
      scanner = new Scanner(new File(filePath));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    while (scanner.hasNext()) {
      content.add(scanner.next());
    }
    return content.toArray(new String[content.size()]);
  }

  private static int[] convertInputToInt(String[] input) {
    int[] ints = new int[input.length];
    for (int i = 0; i < input.length; i++) {
      ints[i] = Integer.parseInt(input[i]);
    }
    return ints;
  }
}

