import java.util.Arrays;

public class Day2 extends Base{
  public static void solve(CHALLENGE challenge) {
//    String[] commands = getInputAsString("./src/data/day2-sample.txt");
    String[] commands = Base.getInputAsString("./src/data/day2-input.txt");
    int pos = 0;
    int depth = 0;
    int aim = 0;

    for (int i = 0; i <= commands.length - 2; i += 2) {
      if (challenge == CHALLENGE.A) {
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
}
