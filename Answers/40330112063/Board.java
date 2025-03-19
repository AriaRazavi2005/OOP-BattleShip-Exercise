import java.util.Random;
import java.util.Scanner;

class Board {
    private int size;
    private char[][] grid;
    private Random random = new Random();

    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '~'; // Assuming '~' represents water
            }
        }
    }

    public int getSize() {
        return size;
    }

    public char[][] getGrid() {
        return grid;
    }

    public char getCellStatus(int row, int col) {
        return grid[row][col];
    }

    public void setCellStatus(int row, int col, char status) {
        grid[row][col] = status;
    }


    public void placeShipRandomly(Ship ship) {
        boolean horizontal = random.nextBoolean();
        int row, col;

        while (true) {
            row = random.nextInt(size);
            col = random.nextInt(size);
            if (isValidPlacement(row, col, ship.getSize(), horizontal,ship)) {
                placeShip(ship, row, col, horizontal);
                break;
            }
        }
    }
    public void placeShipsManually(Ship ship, Scanner scanner) {
        boolean placed = false;
        while (!placed) {
            System.out.println("Enter coordinates for " + ship.getName() + " (size: " + ship.getSize() + ")");

            // Get starting row
            System.out.print("Enter starting row (A-" + (char) ('A' + size - 1) + "): ");
            String rowInput = scanner.nextLine().toUpperCase();
            if (!Utils.isValidRowInput(rowInput, size)) {
                System.out.println("Invalid row input. Please try again.");
            } else {
                int row = Utils.rowCharToIndex(rowInput.charAt(0));

                // Get starting column
                System.out.print("Enter starting column (1-" + size + "): ");
                String colInput = scanner.nextLine();
                if (!Utils.isValidColInput(colInput, size)) {
                    System.out.println("Invalid col input. Please try again.");
                } else {
                    int col = Integer.parseInt(colInput) - 1;

                    // Get orientation
                    System.out.print("Enter orientation (h or v): ");
                    String orientation = scanner.nextLine().toLowerCase();
                    if (!isValidOrientation(orientation)) {
                        System.out.println("Invalid orientation input. Please try again.");
                    } else {
                        boolean horizontal = orientation.startsWith("h");

                        if (isValidPlacement(row, col, ship.getSize(), horizontal, ship)) {
                            placeShip(ship, row, col, horizontal);
                            placed = true;
                        } else {
                            System.out.println("Invalid placement. Please try again.");
                        }
                    }
                }
            }
        }
    }
    private boolean isValidOrientation(String orientation) {
        return orientation.equals("h") || orientation.equals("v");
    }


    private boolean isValidPlacement(int row, int col, int size, boolean horizontal, Ship ship) {
        if (!isWithinBounds(row, col, size, horizontal)) {
            return false;
        }
        return isNotOverlap(row, col, size, horizontal);
    }

    private boolean isWithinBounds(int row, int col, int size, boolean horizontal) {
        if (horizontal) {
            if (col + size > this.size)
                return false;
        } else{
            if(row + size > this.size)
                return false;
        }
        return true;
    }

    private boolean isNotOverlap(int row, int col, int size, boolean horizontal) {
        for (int i = 0; i < size; i++) {
            int currentRow, currentCol;
            if (horizontal) {
                currentRow = row;
                currentCol = col + i;
            } else {
                currentRow = row + i;
                currentCol = col;
            }
            if (grid[currentRow][currentCol] != '~') {
                return false;
            }
        }
        return true;
    }

    private void placeShip(Ship ship, int row, int col, boolean horizontal) {
        for (int i = 0; i < ship.getSize(); i++) {
            int currentRow, currentCol;
            if (horizontal) {
                currentRow = row;
                currentCol = col + i;
            } else {
                currentRow = row + i;
                currentCol = col;
            }
            grid[currentRow][currentCol] = 'S';
        }
    }
    public boolean fireAt(Board opponentBoard, Coordinate coordinate, Board trackingBoard) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        if (row < 0 || row >= size || col < 0 || col >= size) {
            System.out.println("Invalid coordinates. Shot missed the board!");
            return false;
        }
        if (trackingBoard.getCellStatus(row, col) != '~') {
            System.out.println("You have already fired at this coordinate!");
            return false;
        }
        char targetCell = opponentBoard.getCellStatus(row, col);

        if (targetCell == 'S') {
            System.out.println("It's a Hit!");
            opponentBoard.setCellStatus(row, col, 'X');
            trackingBoard.setCellStatus(row, col, 'X');
            setCellStatus(row, col, 'X');
            return true;

        } else {
            System.out.println("You Missed!");
            opponentBoard.setCellStatus(row, col, 'O');
            trackingBoard.setCellStatus(row, col, 'O');
            setCellStatus(row, col, 'O');

            return false;
        }


    }


    public void displayBoard() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
    public boolean isShipAt(int row, int col) {
        return grid[row][col] == 'S';
    }

}