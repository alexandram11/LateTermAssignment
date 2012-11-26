package Domain;

/**
 * Crated by: Team Lethargic
 * <p/>
 * This program lets two users play a 2 player game of TicTacToe
 * <p/><
 * Date: Jun 19, 2008
 * Time: 11:35:13 PM
 */

public class TicTac implements TicTacInterface {
    public static final int BOARDSIZE = 3;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;
    /**
     * Represents the board, each index represents a square on the board
     * and the value is either 0 for neither player occupy or the value of the player.
     */
    private int board[][];
    /**
     * Represents what turn it is.
     */
    private int turnCount;
    /**
     * Represents the player who won, it is only change if someone wins.
     */
    private int playerWhoWon;

    /**
     * Initializes the board
     */
    public TicTac() {
        board = new int[BOARDSIZE][BOARDSIZE];
        turnCount = 0;
        playerWhoWon = 0;
    }

    /**
     * Creates a new board
     */
    @Override
    public void newGame() {
        turnCount = 0;
        for (int i = 0; i < BOARDSIZE; i++)
            for (int j = 0; j < BOARDSIZE; j++)
                board[i][j] = 0;
        playerWhoWon = 0;
    }

    /**
     * Check if the move is legal
     *
     * @param i x-axis of chosen square
     * @param j y-axis of chosen square
     * @return True if the chosen square is empty and the game has not ended
     */
    @Override
    public boolean canMakeMove(int i, int j) {
        return i < BOARDSIZE && i >= 0 &&
                j < BOARDSIZE && j >= 0 &&
                board[i][j] == 0 &&
                !gameOver();
    }

    /**
     * Executes the move
     *
     * @param i      x-axis of chosen square
     * @param j      y-axis of chosen square
     * @param player the player who is making the move
     * @return True if the move is possible
     */
    @Override
    public boolean makeMove(int i, int j, int player) {
        if (canMakeMove(i, j) && player == whosTurn()) {
            board[i][j] = player;
            if (isVictory())
                playerWhoWon = player;
            turnCount++;
            return true;
        }
        return false;
    }

    /**
     * Change player
     *
     * @return The player who has to make a move as int
     */
    @Override
    public int whosTurn() {
        return (turnCount % 2) + 1;
    }

    /**
     * Checks possible victory combinations
     *
     * @return True if any of possible winning combinations are fulfilled
     */
    private boolean checkDiagonalVictory() {
        boolean hasVictory = true;
        for (int i = 0; i + 1 < BOARDSIZE; )
            for (int j = 0; j + 1 < BOARDSIZE; )
                if (board[i][j] != board[++i][++j])
                    hasVictory = false;
        if (board[0][0] != 0 && hasVictory)
            return true;

        hasVictory = true;
        for (int i = 0; i + 1 < BOARDSIZE; )
            for (int j = BOARDSIZE - 1; j > 0; )
                if (board[i][j] != board[++i][--j])
                    hasVictory = false;

        if (board[0][BOARDSIZE - 1] == 0 && hasVictory)
            hasVictory = false;

        return hasVictory;
    }

    private boolean checkHorizontalVictory() {
        for (int i = 0; i < BOARDSIZE; i++)
            if (board[i][0] != 0 &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2])
                return true;
        return false;
    }

    private boolean checkVerticalVictory() {
        for (int i = 0; i < BOARDSIZE; i++)
            if (board[0][i] != 0 &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i])
                return true;
        return false;
    }

    /**
     * Check if the player has won
     *
     * @return True if the player fulfills any of the winning combinations
     */
    @Override
    public boolean isVictory() {
        return checkDiagonalVictory() ||
                checkHorizontalVictory() ||
                checkVerticalVictory();
    }

    /**
     * Check if a player is victorious or the number of moves equals the board size
     *
     * @return True if a player has won or there are no possible moves left
     */
    @Override
    public boolean gameOver() {
        return turnCount == BOARDSIZE * BOARDSIZE || isVictory();
    }

    /**
     * @return The player who won
     */
    @Override
    public int playerWhoWon() {
        return playerWhoWon;
    }

    /**
     * @return The board as a string
     */
    @Override
    public String displayBoard() {
        String out = "";
        for (int i = 0; i < BOARDSIZE; ) {
            for (int j = 0; j < BOARDSIZE; j++)
                out += "[" + (board[i][j] == PLAYER1 ? "X" : board[i][j] == PLAYER2 ? "O" : " ") + "]";
            if (++i != BOARDSIZE)
                out += "\n";
        }
        return out;
    }
}
