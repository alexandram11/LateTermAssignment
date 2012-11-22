package Domain;

import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;

public class TicTac {
    int board[][];
    public static final int boardSize = 3;
    public static final int player1 = 1;
    public static final int player2 = 2;
    private int turnCount;
    private int playerWhoWon;

    public TicTac() {
        board = new int[boardSize][boardSize];
        turnCount = 0;
        playerWhoWon = 0;
    }

    public void NewGame() {
        turnCount = 0;
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 0;
    }

    public boolean canMakeMove(int i, int j) {
        return i < 3 && i >= 0 &&
                j < 3 && j >= 0 &&
                turnCount < 9 &&
                board[i][j] == 0 &&
                !hasVictory();
    }

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

    public int whosTurn() {
        return (turnCount % 2) + 1;
    }

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

    public int playerWhoWon() {
        return playerWhoWon;
    }

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
