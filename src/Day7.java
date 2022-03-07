import java.text.MessageFormat;
import java.util.Arrays;

public class Day7 extends Base {
//  static int[] input = getInputAsCSVInt("./src/data/day7-sample.txt");
      static int[] input = getInputAsCSVInt("./src/data/day7-input.txt");

  public static void solve(CHALLENGE challenge) {
    Arrays.sort(input);
    System.out.println(Arrays.toString(input));
    int lowestTotal = 0;
    int lowestPos = 0;
    boolean pointerWentLeft = true;
    int pointer = input.length / 2;

    while(true) {
     int currTotal = 0;
     for(int i = 0; i < input.length; i++){
       if(challenge == CHALLENGE.A) {
         currTotal += Math.abs(input[pointer] - input[i]);
       } else {
         currTotal += calculateExponentCost(Math.abs(input[pointer]), Math.abs(input[i]));
       }
     }
      System.out.println(currTotal);
     if (lowestTotal == 0) {
       lowestTotal = currTotal;
       lowestPos = input[pointer];
       while(input[pointer] == input[--pointer]);
     } else if(currTotal < lowestTotal) {
       lowestTotal = currTotal;
       if (pointerWentLeft) {
         while(input[pointer] == input[--pointer]);
       } else {
         while(input[pointer] == input[++pointer]);
       }
     } else {
       while(input[pointer] == input[++pointer]);
       if(!pointerWentLeft) break;
       // skip over the last lowest before going left
       while(input[pointer] == input[++pointer]);
       pointerWentLeft = false;
     }
    }
    System.out.println(MessageFormat.format("pos {0} cost least for {1} fuel"
    , lowestPos, lowestTotal));
  }

  private static int calculateExponentCost(int to, int from) {
    int result = 0;
    int raise = 1;
    if (to > from) {
      while(to != from++) result += raise++;
    } else if (to < from) {
      while(to != from--) result += raise++;
    }
    return result;
  }
}
