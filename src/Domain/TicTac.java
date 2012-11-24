package Domain;

/**
 * Crated by: Team Lethargic
 * <p/>
 * This program lets two users play a 2 player game of TicTacToe
 * <p/>
 * Date: Jun 19, 2008
 * Time: 11:35:13 PM
 */

public class TicTac implements TicTacInterface {
    int board[][];
    public static final int boardSize = 3;
    public static final int player1 = 1;
    public static final int player2 = 2;
    private int turnCount;
    private int playerWhoWon;

    // Initializes the board
    public TicTac() {
        board = new int[boardSize][boardSize];
        turnCount = 0;
        playerWhoWon = 0;
    }

    // Creates a new board
    @Override
    public void newGame() {
        turnCount = 0;
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 0;
        playerWhoWon = 0;
    }

    // Checks if any there is any legal moves left
    public boolean canMakeMove(int i, int j) {
        return i < boardSize && i >= 0 &&
                j < boardSize && j >= 0 &&
                turnCount < boardSize * boardSize &&
                board[i][j] == 0 &&
                !isVictory();
    }

    // Lets the player make a move
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

    // Returns the player who has to make a move
    @Override
    public int whosTurn() {
        return (turnCount % 2) + 1;
    }

    // Checks possible victory combinations
    public boolean checkDiagonalVictory() {
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

    public boolean checkHorizontalVictory() {
        for (int i = 0; i < boardSize; i++)
            if (board[i][0] != 0 &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2])
                return true;
        return false;
    }

    public boolean checkVerticalVictory() {
        for (int i = 0; i < boardSize; i++)
            if (board[0][i] != 0 &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i])
                return true;
        return false;
    }

    @Override
    public boolean isVictory() {
        return checkDiagonalVictory() ||
                checkHorizontalVictory() ||
                checkVerticalVictory();
    }


    @Override
    public boolean gameOver() {
        return isVictory() || turnCount == boardSize * boardSize;
    }

    // Returns the player who is victorious
    @Override
    public int playerWhoWon() {
        return playerWhoWon;
    }

    //Outputs the board as a string
    @Override
    public String displayBoard() {
        String out = "";
        for (int i = 0; i < boardSize; ) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0)
                    out += "[ ]";
                else if (board[i][j] == 1)
                    out += "[X]";
                else
                    out += "[O]";
                out += "";
            }
            if (++i != boardSize)
                out += "\n";
        }
        return out;
    }
}
