import java.util.ArrayList;
import java.util.Arrays;

public class Day3 extends Base{
  public static void solve(CHALLENGE challenge) {
//    String[] input = getInputAsString("./src/data/day3-sample.txt");
    String[] input = Base.getInputAsString("./src/data/day3-input.txt");
    System.out.println(Arrays.toString(input));

    if (challenge == CHALLENGE.A) {
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
}
