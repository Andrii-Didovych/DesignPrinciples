package behavioural.template;

public abstract class Game {

    protected int currentPlayer;
    protected final int numberOfPlayer;

    public Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public void run() {
        start();
        while (!haveWinner()) {
            takeTurn();
            System.out.println("Player " + getWinnerPlayer() + " wins");
        }
    }

    protected abstract void takeTurn();
    protected abstract String getWinnerPlayer();
    protected abstract boolean haveWinner();
    protected abstract void start();
}