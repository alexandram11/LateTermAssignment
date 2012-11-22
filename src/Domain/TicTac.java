package Domain;

/**
 * Crated by: Team Lethargic
 *
 * This program lets two users play a 2 player game of TicTacToe
 *
 */

public class TicTac {
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
    public void NewGame() {
        turnCount = 0;
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 0;
    }

    // Checks if any there is any legal moves left
    public boolean canMakeMove(int i, int j) {
        return i < 3 && i >= 0 &&
                j < 3 && j >= 0 &&
                turnCount < 9 &&
                board[i][j] == 0 &&
                !hasVictory();
    }

    // Lets the player makes a move
    public boolean makeMove(int i, int j, int player) {
        if (canMakeMove(i, j) && player == whosTurn()) {
            board[i][j] = player;
            if (hasVictory())
                playerWhoWon = player;
            else
                turnCount++;
            return true;
        }
        return false;
    }

    // Returns the player who has to make a move
    public int whosTurn() {
        return (turnCount % 2) + 1;
    }

    // Checks possible victory combinations
    public boolean checkDiagonalVictory() {
        if (board[1][1] != 0)
            if ((board[2][2] == board[1][1] && board[1][1] == board[0][0]) || (board[2][0] == board[1][1] && board[1][1] == board[0][2]))
                return true;

        return false;
    }

    public boolean checkHorizontalVictory() {
        for (int i = 0; i < 3; i++)
            if (board[i][0] != 0 &&
                    board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2])
                return true;
        return false;
    }

    public boolean checkVerticalVictory() {
        for (int i = 0; i < 3; i++)
            if (board[0][i] != 0 &&
                    board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i])
                return true;
        return false;
    }

    public boolean hasVictory() {
        return checkDiagonalVictory() ||
                checkHorizontalVictory() ||
                checkVerticalVictory();
    }

    // Returns the player who is victorious
    public int playerWhoWon() {
        return playerWhoWon;
    }

    // Prints the board on the screen
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
