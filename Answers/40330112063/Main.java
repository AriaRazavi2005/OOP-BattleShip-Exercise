import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.println("Do you want to play against another player (1) or AI (2)?");
        int opponentChoice = scanner.nextInt();
        scanner.nextLine();

        Player player2;
        boolean isAI = false;
        if (opponentChoice == 1) {
            System.out.println("Enter player 2 name: ");
            String player2Name = scanner.nextLine();
            player2 = new Player(player2Name, 10);
        } else {
            player2 = new AIPlayer("AI Player", 10);
            isAI = true;
        }

        Game game = new Game(player1Name, 10,player2,isAI);
        game.start();
    }
}