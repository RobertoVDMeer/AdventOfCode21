import java.util.*;

public class Day10 extends Base {
  public static void solve(CHALLENGE challenge) {
    String[] input = getInputAsString("./src/data/day10-sample.txt");
//    String[] input = getInputAsString("./src/data/day10-input.txt");
    System.out.println(Arrays.toString(input));

    HashMap<Character,Character> legalClosers = new HashMap<>();
    legalClosers.put(')', '('); legalClosers.put(']', '[');
    legalClosers.put('}', '{'); legalClosers.put('>', '<');

    HashMap<Character, Integer> scoreValues = new HashMap<>();
    if (challenge == CHALLENGE.A) {
      scoreValues.put(')', 3);
      scoreValues.put(']', 57);
      scoreValues.put('}', 1197);
      scoreValues.put('>', 25137);
    } else {
      scoreValues.put(')', 1);
      scoreValues.put(']', 2);
      scoreValues.put('}', 3);
      scoreValues.put('>', 4);
    }

    Stack<Character> openerStack = new Stack<>();
    long score = 0;

    for (String row : input) {
      Iterator<Integer> c = row.chars().iterator();
      corrupt_line:
      while(c.hasNext()) {
        char car = (char)c.next().intValue();

        if(!legalClosers.containsKey(car)) {
          openerStack.add(car);
        } else {
          if(openerStack.peek() == legalClosers.get(car)) {
            openerStack.pop();
          } else {
            score += scoreValues.get(car);
            openerStack.clear();
            break corrupt_line;
          }
        }
      }
      // This is now a legal, but unfinished line, e.g. Challenge B
      System.out.println(openerStack.size());
    }

    System.out.println(score);
  }
}
