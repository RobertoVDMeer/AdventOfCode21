import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;


public class Main {
  public static void main(String args[]) throws FileNotFoundException {
//    day1(false);
//    day2(false);
//    day3(false);
    day4(false);
  }

  public static void day1(boolean challengeA) {
//    int[] readings = getInputAsInt("./src/data/day1-sample.txt");
    int[] readings = getInputAsInt("./src/data/day1-input.txt");
    int count = 0;
    int pointerAndLookback = challengeA ? 1 : 3;

    for (int i = pointerAndLookback; i < readings.length; i++) {
      count += readings[i] > readings[i - pointerAndLookback] ? 1 : 0;
    }
    System.out.println(Arrays.toString(readings));
    System.out.println(count);
  }

  public static void day2(boolean challengeA) {
//    String[] commands = getInputAsString("./src/data/day2-sample.txt");
    String[] commands = getInputAsString("./src/data/day2-input.txt");
    int pos = 0;
    int depth = 0;
    int aim = 0;

    for (int i = 0; i <= commands.length - 2; i += 2) {
      if (challengeA) {
        if (commands[i].equals("forward")) pos += Integer.parseInt(commands[i + 1]);
        if (commands[i].equals("down")) depth += Integer.parseInt(commands[i + 1]);
        if (commands[i].equals("up")) depth -= Integer.parseInt(commands[i + 1]);
        if (depth < 0) depth = 0;
      } else {
        if (commands[i].equals("forward")) {
          int change = Integer.parseInt(commands[i + 1]);
          pos += change;
          if (aim != 0) depth += aim * change;
        }
        if (commands[i].equals("down")) aim += Integer.parseInt(commands[i + 1]);
        if (commands[i].equals("up")) aim -= Integer.parseInt(commands[i + 1]);
        if (depth < 0) depth = 0;
      }
    }

    System.out.println(Arrays.toString(commands));

    System.out.println(pos * depth);
  }

  public static void day3(boolean challengeA) {
//    String[] input = getInputAsString("./src/data/day3-sample.txt");
    String[] input = getInputAsString("./src/data/day3-input.txt");
    System.out.println(Arrays.toString(input));

    if (challengeA) {
      int[][] countMatrix = new int[input[0].length()][2];
      String gammaBin = "";
      String epsilonBin = "";
      int gamma = 0;
      int epsilon = 0;

      // count the bits and put in multi array
      for (String row : input) {
        for (int i = 0; i < row.length(); i++) {
          countMatrix[i][Character.getNumericValue(row.charAt(i))]++;
        }
      }
      // now build the gammaBin string based on most common bits
      for (int[] num : countMatrix) {
        gammaBin += num[0] > num[1] ? "0" : "1";
        epsilonBin += num[0] < num[1] ? "0" : "1";
      }

      gamma = Integer.parseInt(gammaBin, 2);

      epsilon = Integer.parseInt(epsilonBin, 2);

      System.out.println(gamma * epsilon);
    } else {
      int oxygen = 0;
      int co2 = 0;

      ArrayList<String> tempZeros = new ArrayList<>();
      ArrayList<String> tempOnes = new ArrayList<>();
      ArrayList<String> oxygenReadings = new ArrayList<>();
      ArrayList<String> co2Readings = new ArrayList<>();

      // fill initial arrayLists
      for (String row : input) {
        String firstBit = row.substring(0, 1);
        if (firstBit.equals("0")) {
          tempZeros.add(row);
        } else {
          tempOnes.add(row);
        }
      }

      if (tempZeros.size() > tempOnes.size()) {
        oxygenReadings.addAll(tempZeros);
        co2Readings.addAll(tempOnes);
      } else {
        oxygenReadings.addAll(tempOnes);
        co2Readings.addAll(tempZeros);
      }
      tempZeros.clear();
      tempOnes.clear();

      // now iterate over the readings until single left
      int currBit = 1;
      while (oxygenReadings.size() > 1 || co2Readings.size() > 1) {
        if (oxygenReadings.size() > 1) {
          for (String reading : oxygenReadings) {
            if (reading.substring(currBit, currBit + 1).equals("0")) tempZeros.add(reading);
            else tempOnes.add(reading);
          }
          oxygenReadings.clear();
          if (tempOnes.size() >= tempZeros.size()) oxygenReadings.addAll(tempOnes);
          else oxygenReadings.addAll(tempZeros);
          tempOnes.clear();
          tempZeros.clear();
        }
        if (co2Readings.size() > 1) {
          for (String reading : co2Readings) {
            if (reading.substring(currBit, currBit + 1).equals("0")) tempZeros.add(reading);
            else tempOnes.add(reading);
          }
          co2Readings.clear();
          if (tempOnes.size() < tempZeros.size()) co2Readings.addAll(tempOnes);
          else co2Readings.addAll(tempZeros);
          tempOnes.clear();
          tempZeros.clear();
        }
        currBit++;
      }

      oxygen = Integer.parseInt(oxygenReadings.get(0), 2);
      co2 = Integer.parseInt(co2Readings.get(0), 2);
      System.out.println(oxygenReadings.size());
      System.out.println(oxygenReadings.get(0));
      System.out.println(co2Readings.size());
      System.out.println(co2Readings.get(0));
      System.out.println(oxygen);
      System.out.println(co2);
      System.out.println(oxygen * co2);

    }
  }

  public static void day4(boolean challengeA) {
//    String[] input = getInputAsString("./src/data/day4-sample.txt");
    String[] input = getInputAsString("./src/data/day4-input.txt");

    // just taking precautionary measures here, I'm sure that squid
    // will be able to either hold more cards, or bigger cards next round,
    // 8 legged bastard :p (yes I know that's an octopus, but they don't play
    // bingo either now do they, wise-ass?)
    // make the cards 1 bigger to keep an index on pos 0 row / col
    final int GRID_SIZE = 6;
    final int CARDS = (input.length - 1) / (int) Math.pow((GRID_SIZE - 1), 2);
    int winningCard = 0;
    int winningNum = 0;
    int sum = 0;

    // for challenge B
    HashMap<Integer,Integer> cardScores = new HashMap();

    int[][][] cards = new int[CARDS][GRID_SIZE][GRID_SIZE];
    int[] draws = Arrays.stream(input[0].split(","))
        .mapToInt(Integer::parseInt)
        .toArray();

    // fill the cards
    int fillPointer = 1;
    for (int card = 0; card < CARDS; card++) {
      for (int row = 1; row < GRID_SIZE; row++) {
        for (int col = 1; col < GRID_SIZE; col++) {
          cards[card][row][col] = Integer.parseInt(input[fillPointer]);
          fillPointer++;
        }
      }
    }

    out_game:
    // play
    for (int i = 0; i < draws.length; i++) {
      for (int card = 0; card < CARDS; card++) {
        for (int row = 1; row < GRID_SIZE; row++) {
          for (int col = 1; col < GRID_SIZE; col++) {
            if (draws[i] == cards[card][row][col]) {
              cards[card][row][col] = -1;
              cards[card][row][0]++;
              cards[card][0][col]++;
              if (cards[card][row][0] == 5 || cards[card][0][col] == 5) {
                if (challengeA) {
                  System.out.println("Card " + (card + 1) + " bingo with number " + draws[i]);
                  winningNum = draws[i];
                  winningCard = card;
                  break out_game;
                } else {
                  if (!cardScores.containsKey(card)){
                    System.out.println("Card " + (card + 1) + " bingo with number " + draws[i]);
                    winningNum = draws[i];
                    winningCard = card;
                    sum = 0;
                    for (int r = 1; r < GRID_SIZE; r++) {
                      sum += Arrays.stream(cards[winningCard][r]).sum();
                    }
                    cardScores.put(card, sum);
                  }
                }
              }
            }
          }
        }
      }
    }

    // get the sum of all elements, skip the first row as it only indexed the hits
    // in cols, but the first cols does the same for rows
    for (int row = 1; row < GRID_SIZE; row++) {
      sum += Arrays.stream(cards[winningCard][row]).sum();
    }
    if(challengeA) {
      System.out.println(winningNum * sum);
    } else {
      System.out.println(winningNum * cardScores.get(winningCard));
    }
  }

  public static void dayX(boolean challengeA) {
//    int[] input = getInputAsInt("./src/data/dayx-sample.txt");
//    int[] input = getInputAsInt("./src/data/dayx-input.txt");
//    String[] input = getInputAsString("./src/data/dayx-sample.txt");
//    String[] input = getInputAsString("./src/data/dayx-input.txt");
//    System.out.println(Arrays.toString(input));
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

