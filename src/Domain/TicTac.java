package Domain;

import com.sun.org.apache.xpath.internal.axes.PredicatedNodeTest;

public class TicTac {
    int board[][];

    public TicTac() {
        board = new int[3][3];
    }

    public String DisplayBoard() {
        String out = "";
        for (int i = 0; i < 3; i++) {
            out += "\n";
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0)
                    out += " ";
                else if (board[i][j] == 1)
                    out += "X";
                else
                    out += "O";
                out += "|";
            }
        }
        return out;
    }
}
