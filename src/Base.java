import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Base {
  enum CHALLENGE {A, B}

  public static void main(String args[]) throws FileNotFoundException {
//    Day1.solve(CHALLENGE.B);
//    Day2.solve(CHALLENGE.B);
//    Day3.solve(CHALLENGE.B);
//    Day4.solve(CHALLENGE.B);
      Day7.solve(CHALLENGE.B);
//    Day10.solve(CHALLENGE.B);
//    Day11.solve(CHALLENGE.B);
  }

  public static int[] getInputAsInt(String filePath) {
    String[] input = getInputAsString(filePath);
    return convertInputToInt(input);
  }

  public static int[] getInputAsCSVInt(String filePath) {
    String[] input = getInputAsString(filePath)[0].split(",");

    return convertInputToInt(input);
  }

  public static String[] getInputAsString(String filePath) {
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

  public static int[] convertInputToInt(String[] input) {
    int[] ints = new int[input.length];
    for (int i = 0; i < input.length; i++) {
      ints[i] = Integer.parseInt(input[i]);
    }
    return ints;
  }
}

