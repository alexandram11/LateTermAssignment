package Presentation;

import Domain.TicTacInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Sir.Thorgeir lap
 * Date: 22.11.2012
 * Time: 23:48
 *
 * @author Þorgeir Auðunn Karlsson.
 */
public abstract class AbsTicTacGameGUI extends JPanel implements ActionListener {
    protected JButton[][] btns = new JButton[3][3];
    protected JPanel upper;
    protected PlayerPanel lower;
    protected TicTacInterface gameLogic;

    //X icon O and an empty icon loaded
    ImageIcon xIcon = new ImageIcon("src/Presentation/img/x.png");
    ImageIcon oIcon = new ImageIcon("src/Presentation/img/o.png");
    ImageIcon emptyIcon = new ImageIcon("src/Presentation/img/empty.png");

    public AbsTicTacGameGUI() {
        BoxLayout cMan = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(cMan);

        GridLayout lMan = new GridLayout(3, 3);
        upper = new JPanel(lMan);

        lower = new PlayerPanel(this);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btns[i][j] = new JButton("", emptyIcon);
                btns[i][j].setVerticalTextPosition(AbstractButton.CENTER);
                btns[i][j].setHorizontalTextPosition(AbstractButton.CENTER);
                btns[i][j].setActionCommand("btn_" + i + "_" + j);
                btns[i][j].addActionListener(this);
                btns[i][j].setBackground(Color.WHITE);

                // Makes the buttons unfocusable i.e. gets rif of the border around the icon
                btns[i][j].setFocusable(false);
                upper.add(btns[i][j]);
            }
        }
        add(upper);
        add(lower);
    }

    public TicTacInterface getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(TicTacInterface gameLogic) {
        this.gameLogic = gameLogic;
    }

    public ImageIcon getxIcon() {
        return xIcon;
    }

    public void setxIcon(ImageIcon xIcon) {
        this.xIcon = xIcon;
    }

    public ImageIcon getoIcon() {
        return oIcon;
    }

    public void setoIcon(ImageIcon oIcon) {
        this.oIcon = oIcon;
    }

    public ImageIcon getEmptyIcon() {
        return emptyIcon;
    }

    public void setEmptyIcon(ImageIcon emptyIcon) {
        this.emptyIcon = emptyIcon;
    }
}
