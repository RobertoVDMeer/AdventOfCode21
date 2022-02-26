import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.*;

public class Day10 extends Base {
  static HashMap<Character, Integer> scoreValues = new HashMap<>();

  public static void solve(CHALLENGE challenge) {
//    String[] input = getInputAsString("./src/data/day10-sample.txt");
    String[] input = getInputAsString("./src/data/day10-input.txt");
    System.out.println(Arrays.toString(input));

    HashMap<Character, Character> legalClosers = new HashMap<>();
    legalClosers.put(')', '(');
    legalClosers.put(']', '[');
    legalClosers.put('}', '{');
    legalClosers.put('>', '<');

    if (challenge == CHALLENGE.A) {
      scoreValues.put(')', 3);
      scoreValues.put(']', 57);
      scoreValues.put('}', 1197);
      scoreValues.put('>', 25137);
    } else {
      scoreValues.put('(', 1);
      scoreValues.put('[', 2);
      scoreValues.put('{', 3);
      scoreValues.put('<', 4);
    }

    Stack<Character> openerStack = new Stack<>();
    long score = 0;
    ArrayList<Long> scoreList = new ArrayList<>();

    for (String row : input) {
      Iterator<Integer> c = row.chars().iterator();
      corrupt_line:
      while (c.hasNext()) {
        char car = (char) c.next().intValue();

        if (!legalClosers.containsKey(car)) {
          openerStack.add(car);
        } else {
          if (openerStack.peek() == legalClosers.get(car)) {
            openerStack.pop();
          } else {
            score += scoreValues.getOrDefault(car, 0);
            openerStack.clear();
            break corrupt_line;
          }
        }
      }
      // This is now a legal, but unfinished line, e.g. Challenge B
      if (challenge == CHALLENGE.B && openerStack.size() > 0) {
        scoreList.add(calculateCompletionScore(openerStack));
      }
    }

    if (challenge == CHALLENGE.A) {
      System.out.println(score);
    } else {
      scoreList.sort(null);
      int middle = scoreList.size() / 2;
      System.out.println(scoreList.get(middle));
    }
  }

  private static Long calculateCompletionScore(Stack<Character> openerStack) {
    long total = 0;

    while (!openerStack.isEmpty()) {
      Character opener = openerStack.pop();
      total = (5 * total) + scoreValues.get(opener);
    }
    return total;
  }
}
