import java.util.Arrays;
import java.util.HashMap;

public class Day4 extends Base{
  public static void solve(CHALLENGE challenge) {
//    String[] input = getInputAsString("./src/data/day4-sample.txt");
    String[] input = Base.getInputAsString("./src/data/day4-input.txt");

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
                if (challenge == CHALLENGE.A) {
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
    if(challenge == CHALLENGE.A) {
      System.out.println(winningNum * sum);
    } else {
      System.out.println(winningNum * cardScores.get(winningCard));
    }
  }
}
