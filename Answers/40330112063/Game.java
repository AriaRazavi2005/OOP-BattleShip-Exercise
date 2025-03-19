import java.util.Scanner;

class Game {
    private Player player1;
    private Player player2;
    private int boardSize;
    private Scanner scanner;
    private Player currentPlayer;
    private boolean isAI;

    public Game(String player1Name,  int boardSize,Player player2, boolean isAI) {
        this.boardSize = boardSize;
        this.player1 = new Player(player1Name, boardSize);
        this.player2 = player2;
        this.scanner = new Scanner(System.in);
        this.currentPlayer = player1; // Player 1 starts first
        this.isAI = isAI;
    }
    public void start() {
        boolean playAgain;
        do {
            startGame();
            playAgain = askReplay();
        } while (playAgain);
    }

    public void startGame() {
        System.out.println("Welcome to Battleship!");

        // Place ships for both players
        System.out.println(player1.getName() + ", place your ships:");
        player1.placeShips(getPlayerChoice(player1.getName()));

        System.out.println(player2.getName() + ", place your ships:");
        player2.placeShips(getPlayerChoice(player2.getName()));

        // Game loop
        while (!isGameOver()) {
            System.out.println("\n" + currentPlayer.getName() + "'s turn:");
            currentPlayer.printTrackingBoard();

            String coordinate;
            if (currentPlayer == player1) {
                coordinate = getPlayerCoordinate(currentPlayer.getName());
            } else {
                if (isAI) {
                    coordinate = player2.makeMove(); // AI makes a move
                    System.out.println("AI Player 2 fired at " + coordinate);
                } else {
                    coordinate = getPlayerCoordinate(currentPlayer.getName());
                }
            }

            currentPlayer.takeTurn(getOpponent(currentPlayer), coordinate);
            switchTurns();

        }

        // Determine the winner
        Player winner;
        if (player1.hasLost()) {
            winner = player2;
        } else {
            winner = player1;
        }
        System.out.println("\nGame Over! " + winner.getName() + " wins!");
    }

    private boolean getPlayerChoice(String playerName) {
        System.out.println(playerName + ", do you want to place your ships randomly (1) or manually (2)?");
        String choiceInput = scanner.nextLine();
        int choice;

        if (choiceInput.equals("1")) {
            return true;
        } else if (choiceInput.equals("2")) {
            return false;
        } else {
            System.out.println("Invalid choice. Please enter 1 for random or 2 for manual.");
            return getPlayerChoice(playerName); // Recursive call to get valid input
        }


    }

    private String getPlayerCoordinate(String playerName) {
        System.out.print(playerName + ", enter coordinates to fire at (e.g., A5): ");
        return scanner.nextLine().toUpperCase();
    }

    private void switchTurns() {
        if (currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }

    }

    private boolean isGameOver() {
        return player1.hasLost() || player2.hasLost();
    }

    private Player getOpponent(Player player) {
        return (player == player1) ? player2 : player1;
    }
    private boolean askReplay() {
        System.out.println("Play again? (yes/no)");
        return scanner.next().equalsIgnoreCase("yes");
    }

    private void playGame() {
        System.out.println("Game logic here...");
    }
}