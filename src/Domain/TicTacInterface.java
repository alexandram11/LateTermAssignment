package Domain;

/**
 * //TODO Comment everything.
 */
public interface TicTacInterface {
    public void newGame();

    public boolean canMakeMove(int i, int j);

    public boolean makeMove(int i, int j, int player);

    public int whosTurn();

    public boolean isVictory();

    public boolean gameOver();

    public int playerWhoWon();

    public String displayBoard();
}
