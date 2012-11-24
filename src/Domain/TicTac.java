package Domain;

/**
 * Crated by: Team Lethargic
 * <p/>
 * This program lets two users play a 2 player game of TicTacToe
 * <p/>
 * Date: Jun 19, 2008
 * Time: 11:35:13 PM
 */

public class TicTac implements TicTacInterface
{
    int board[][];
    public static final int boardSize = 3;
    public static final int player1 = 1;
    public static final int player2 = 2;
    private int turnCount;
    private int playerWhoWon;

    /**
     * Initializes the board
     */
    public TicTac()
    {
        board = new int[boardSize][boardSize];
        turnCount = 0;
        playerWhoWon = 0;
    }

    /**
     * Creates a new board
     */
    @Override
    public void newGame()
    {
        turnCount = 0;
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
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
    public boolean canMakeMove(int i, int j)
    {
        return i < boardSize && i >= 0 &&
                j < boardSize && j >= 0 &&
                turnCount < boardSize * boardSize &&
                board[i][j] == 0 &&
                !isVictory();
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
    public boolean makeMove(int i, int j, int player)
    {
        if (canMakeMove(i, j) && player == whosTurn())
        {
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
    public int whosTurn()
    {
        return (turnCount % 2) + 1;
    }

    /**
     * Checks possible victory combinations
     *
     * @return True if any of possible winning combinations are fulfilled
     */
    private boolean checkDiagonalVictory()
    {
        boolean hasVictory = true;
        for (int i = 0; i + 1 < boardSize; )
            for (int j = 0; j + 1 < boardSize; )
                if (board[i][j] != board[++i][++j])
                    hasVictory = false;
        if (board[0][0] != 0 && hasVictory)
            return true;

        hasVictory = true;
        for (int i = 0; i + 1 < boardSize; )
            for (int j = boardSize - 1; j > 0; )
                if (board[i][j] != board[++i][--j])
                    hasVictory = false;

        if (board[0][boardSize - 1] == 0 && hasVictory)
            hasVictory = false;

        return hasVictory;
    }

    private boolean checkHorizontalVictory()
    {
        for (int i = 0; i < boardSize; i++)
            if (board[i][0] != 0 &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2])
                return true;
        return false;
    }

    private boolean checkVerticalVictory()
    {
        for (int i = 0; i < boardSize; i++)
            if (board[0][i] != 0 &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i])
                return true;
        return false;
    }

    /**
     * Check if the player has won
     * @return True if the player fulfills any of the winning combinations
     */
    @Override
    public boolean isVictory()
    {
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
    public boolean gameOver()
    {
        return isVictory() || turnCount == boardSize * boardSize;
    }

    /**
     * @return The player who won
     */
    @Override
    public int playerWhoWon()
    {
        return playerWhoWon;
    }

    /**
     * @return The board as a string
     */
    @Override
    public String displayBoard()
    {
        String out = "";
        for (int i = 0; i < boardSize; )
        {
            for (int j = 0; j < boardSize; j++)
                out += "[" + (board[i][j] == player1 ? "X" : board[i][j] == player2 ? "O" : " ") + "]";
            if (++i != boardSize)
                out += "\n";
        }
        return out;
    }
}
