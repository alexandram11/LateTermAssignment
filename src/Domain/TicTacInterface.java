package Domain;

/**
 * TicTacInterface
 */
public interface TicTacInterface
{
    /**
     * Creates a new board
     */
    public void newGame();

    /**
     * Check if the move is legal
     *
     * @param i x-axis of chosen square
     * @param j y-axis of chosen square
     * @return True if the chosen square is empty and the game has not ended
     */
    public boolean canMakeMove(int i, int j);

    /**
     * Executes the move
     *
     * @param i      x-axis of chosen square
     * @param j      y-axis of chosen square
     * @param player the player who is making the move
     * @return True if the move is possible
     */
    public boolean makeMove(int i, int j, int player);

    /**
     * Change player
     *
     * @return The player who has to make a move as int
     */
    public int whosTurn();

    /**
     * Check if the player has won
     * @return True if the player fulfills any of the winning combinations
     */
    public boolean isVictory();

    /**
     * Check if a player is victorious or the number of moves equals the board size
     *
     * @return True if a player has won or there are no possible moves left
     */
    public boolean gameOver();

    /**
     * @return The player who won
     */
    public int playerWhoWon();

    /**
     * @return The board as a string
     */
    public String displayBoard();
}
