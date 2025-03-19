import java.util.Random;

class AIPlayer extends Player {
    private Random random = new Random();
    public AIPlayer(String name, int boardSize) {
        super(name, boardSize);
    }


    @Override
    public String makeMove() {

        return "" + (char) ('A' + random.nextInt(getBoard().getSize())) + (random.nextInt(getBoard().getSize()) + 1);
    }
}
