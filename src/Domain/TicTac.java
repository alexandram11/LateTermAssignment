package Domain;

import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;

public class TicTac {
    int board[][];
    public static final int boardSize = 3;
    public static final int player1 = 1;
    public static final int player2 = 2;
    private int turnCount;

    public TicTac() {
        board = new int[3][3];
        turnCount = 0;
    }

    public boolean canMakeMove(int i, int j) {
        return i < 3 && i >= 0 &&
                j < 3 && j >= 0 &&
                turnCount < 9 &&
                board[i][j] == 0;
    }

    public boolean makeMove(int i, int j, int player) {
        if (canMakeMove(i, j) && player == whosTurn()) {
            board[i][j] = player;
            turnCount++;
            return true;
        }
        return false;
    }

    public int whosTurn() {
        return (turnCount % 2) + 1;
    }

    public String displayBoard() {
        String out = "";
        for (int i = 0; i < 3; i++) {
            out += "\n";
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0)
                    out += "[ ]";
                else if (board[i][j] == 1)
                    out += "[X]";
                else
                    out += "[O]";
                out += "";
            }
        }
        return out;
    }
}
