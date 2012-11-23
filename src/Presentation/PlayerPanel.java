package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * //TODO vantar að gera Tests og að commenta.
 */
public class PlayerPanel extends JPanel {
    protected JPanel[] playerPanels;
    protected JPanel center;

    protected JButton btn_newGame;

    protected JLabel[] names;
    protected JLabel[] points;
    protected JLabel[] winLabels;

    public PlayerPanel(ActionListener listener){
        playerPanels = new JPanel[2];
        names = new JLabel[2];
        points = new JLabel[2];
        winLabels = new JLabel[2];

        Font fSmall = new Font("Helvetica",Font.BOLD,12);
        Font fBig = new Font("Helvetica",Font.BOLD,15);

        for(int i = 0;i<2;i++){
            GridLayout temp = new GridLayout(3,1);
            //Player panels initialized and loaded with a GridLayout
            playerPanels[i]=new JPanel();
            playerPanels[i].setLayout(temp);
            playerPanels[i].setBackground(Color.BLACK);

            //winLabels initialized
            winLabels[i]= new JLabel("Player "+(i+1)+" WINS!");
            winLabels[i].setVerticalAlignment(AbstractButton.CENTER);
            winLabels[i].setHorizontalAlignment(AbstractButton.CENTER);
            //winLabel font and font-color set
            winLabels[i].setForeground(Color.WHITE);
            winLabels[i].setFont(fBig);
            //winLabels made invisible and loaded into each panel
            winLabels[i].setVisible(false);
            playerPanels[i].add(winLabels[i]);

            //name labels initialized
            names[i] = new JLabel("Player "+(i+1));
            names[i].setVerticalAlignment(AbstractButton.CENTER);
            names[i].setHorizontalAlignment(AbstractButton.CENTER);
            //name label font and font-color set
            names[i].setForeground(Color.WHITE);
            names[i].setFont(fSmall);
            //name labels loaded into each panel
            playerPanels[i].add(names[i]);

            //point labels initialized
            points[i] = new JLabel("0");
            points[i].setVerticalAlignment(AbstractButton.TOP);
            points[i].setHorizontalAlignment(AbstractButton.CENTER);
            //point label font and font-color set
            points[i].setForeground(Color.WHITE);
            points[i].setFont(fSmall);
            //point labels loaded into each panel
            playerPanels[i].add(points[i]);

        }

        //center panel initialized
        center = new JPanel();
        //new game button initialized and loaded with an action listener
        btn_newGame = new JButton("NEW GAME");
        btn_newGame.setVerticalTextPosition(AbstractButton.CENTER);
        btn_newGame.setHorizontalTextPosition(AbstractButton.CENTER);
        btn_newGame.setActionCommand("NEW GAME");
        btn_newGame.addActionListener(listener);

        center.add(btn_newGame);
        center.setBackground(Color.black);
        btn_newGame.setBackground(Color.WHITE);
        btn_newGame.setFocusable(false);

        BoxLayout bMan = new BoxLayout(this,BoxLayout.LINE_AXIS);
        setLayout(bMan);

        add(playerPanels[0]);
        add(center);
        add(playerPanels[1]);
    }

    /**
     * Tells the player panel that the given player has won the game
     * @param player zero-based index of the player
     */
    public void win(int player) {
        points[player].setText(String.valueOf(Integer.parseInt(points[player].getText())+1));
        winLabels[player].setVisible(true);
        playerPanels[Math.abs(player-1)].setBackground(Color.BLACK);
        endGame();
    }

    public void playerTurn(int player){
        playerPanels[Math.abs(player-1)].setBackground(Color.BLACK);
        playerPanels[player].setBackground(Color.BLUE);
    }

    private void endGame(){
        btn_newGame.setBackground(Color.WHITE);
        btn_newGame.setBorderPainted(true);
        btn_newGame.setEnabled(true);
        btn_newGame.setText("NEW GAME");
    }

    /**
     * Formats the interface for a new game
     */
    public void startNewGame(){
        for(int i = 0; i< 2 ; i++){
            winLabels[i].setVisible(false);
            playerPanels[i].setBackground(Color.BLACK);
        }
        btn_newGame.setBackground(Color.BLACK);
        btn_newGame.setBorderPainted(false);
        btn_newGame.setEnabled(false);
        btn_newGame.setText("! ! FIGHT ! !");
    }


}
