package Presentation;

import Domain.TicTac;
import Domain.TicTacInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This represents the GUI for the TicTac game.
 *
 */
public class TicTacGUI extends AbsTicTacGameGUI {
    public TicTacGUI(TicTacInterface Game) {
        super();
        gameLogic = Game;
    }

    /**
     * This method is to get the actions (clicks) from the user
     * @param e is the click coming in from user
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("NEW GAME")) {
            lower.startNewGame();
            cleanUp();
            gameLogic.newGame();
            lower.playerTurn(1);
        } else {
            JButton b = (JButton) e.getSource();
            int i = Integer.parseInt(e.getActionCommand().split("_")[1]);
            int j = Integer.parseInt(e.getActionCommand().split("_")[2]);
            int player = gameLogic.whosTurn();
            if (gameLogic.makeMove(i, j, player)) {
                lower.playerTurn(gameLogic.whosTurn());
                if (player == 1)
                    b.setIcon(xIcon);
                else
                    b.setIcon(oIcon);
                if (gameLogic.isVictory())
                    lower.win(player);
                if (gameLogic.gameOver())
                    lower.endGame();
            }
        }
    }

    /**
     * this method cleans the board after a game finishes
     */
    private void cleanUp() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btns[i][j].setIcon(emptyIcon);
            }
        }
    }

    /**
     * new frame created and displayed
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ButtonTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TicTacGUI gameGui = new TicTacGUI(new TicTac());
        gameGui.setOpaque(true); //content panes must be opaque
        frame.setContentPane(gameGui);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(350, 400);
        frame.setResizable(false);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
