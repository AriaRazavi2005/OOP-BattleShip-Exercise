public class Utils {

    public static boolean isValidCoordinate(String coordinate, int boardSize) {
        if (coordinate == null || coordinate.length() < 2 || coordinate.length() > 3) {
            return false;
        }
        char rowChar = coordinate.charAt(0);
        if (!isValidRowChar(rowChar, boardSize)) {
            return false;
        }
        String colStr = coordinate.substring(1);
        return isValidColInput(colStr, boardSize);
    }

    private static boolean isValidRowChar(char rowChar, int boardSize) {
        return rowChar >= 'A' && rowChar < 'A' + boardSize;
    }
    public static boolean isValidColInput(String colInput, int boardSize) {
        if (colInput == null || colInput.isEmpty()) {
            return false;
        }

        if (!isNumeric(colInput)) {
            return false; // Check if colInput is a number
        }
        int col = Integer.parseInt(colInput);
        return col >= 1 && col <= boardSize;
    }

    public static int rowCharToIndex(char rowChar) {
        return rowChar - 'A';
    }
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    public static boolean isValidRowInput(String rowInput, int size) {
        if (rowInput == null || rowInput.length() != 1) return false;
        char rowChar = rowInput.charAt(0);
        return rowChar >= 'A' && rowChar < 'A' + size;
    }
    public static int[] parseCoordinate(String coordinate, int boardSize) {
        if (!isValidCoordinate(coordinate, boardSize)) {
            return null;
        }

        int row = rowCharToIndex(coordinate.charAt(0));
        int col = Integer.parseInt(coordinate.substring(1)) - 1;

        return new int[] {row, col};
    }
}