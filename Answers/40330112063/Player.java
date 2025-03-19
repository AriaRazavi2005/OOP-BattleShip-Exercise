import java.util.Random;
import java.util.Scanner;

class Player {
    private String name;
    private Board board;
    private Board trackingBoard;
    private Ship nabodgar;
    private Ship nuv;
    private Ship keshtijangi;
    private Ship nuvche;
    private Scanner scanner; // Removed as input handling should be in Game class
    private Random random = new Random();


    public Player(String name, int boardSize) {
        this.name = name;
        this.board = new Board(boardSize);
        this.trackingBoard = new Board(boardSize);

        // Create ships
        this.nabodgar = new Ship("nabodgar", 4);
        this.nuv = new Ship("nuv", 3);
        this.keshtijangi = new Ship("keshtijangi", 3);
        this.nuvche = new Ship("nuvche", 2);
        this.scanner = new Scanner(System.in);

    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public Board getTrackingBoard() {
        return trackingBoard;
    }

    public Ship getNabodgar() {
        return nabodgar;
    }

    public Ship getNuv() {
        return nuv;
    }

    public Ship getKeshtijangi() {
        return keshtijangi;
    }

    public Ship getNuvche() {
        return nuvche;
    }
    public void placeShips(boolean auto) {
        if (auto) {
            board.placeShipRandomly(nabodgar);
            board.placeShipRandomly(nuv);
            board.placeShipRandomly(keshtijangi);
            board.placeShipRandomly(nuvche);

        } else {
            board.placeShipsManually(nabodgar,scanner);
            board.placeShipsManually(nuv,scanner);
            board.placeShipsManually(keshtijangi,scanner);
            board.placeShipsManually(nuvche,scanner);


        }
    }

    public boolean takeTurn(Player opponent, String coordinate) {
        int[] coords = Utils.parseCoordinate(coordinate, board.getSize());
        if (coords == null) {
            System.out.println("Invalid coordinates. Try again.");
            return false;
        }
        int row = coords[0];
        int col = coords[1];

        Coordinate target = new Coordinate(row, col);
        return board.fireAt(opponent.getBoard(), target, trackingBoard);
    }

    public boolean hasLost() {
        return nabodgar.isSunk() && nuv.isSunk() && keshtijangi.isSunk() && nuvche.isSunk();
    }

    public void printTrackingBoard() {
        trackingBoard.displayBoard();
    }
    public String makeMove() {
        System.out.println("Basic player has no makeMove");
        return "A1";
    }


}