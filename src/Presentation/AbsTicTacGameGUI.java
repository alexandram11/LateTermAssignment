package Presentation;

import Domain.TicTacInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
    ImageIcon xIcon;
    ImageIcon oIcon;
    ImageIcon emptyIcon;

    public AbsTicTacGameGUI() {
        oIcon = getOIcon();
        xIcon = getXIcon();
        emptyIcon = getEmptyIcon();
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

    public ImageIcon getOIcon() {
        if (oIcon == null) {
            BufferedImage img = new BufferedImage(48, 48, BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, 48, 48);
            g.setColor(Color.BLACK);
            g.fillOval(0, 0, 48, 48);
            g.setColor(Color.white);
            g.fillOval(9, 9, 30, 30);
            oIcon = new ImageIcon(img);
        }
        return oIcon;
    }

    public ImageIcon getXIcon() {
        if (xIcon == null) {
            BufferedImage img = new BufferedImage(48, 48, BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, 48, 48);
            for (int k = 0; k < 4; k++) {
                g.setColor(Color.BLACK);
                g.drawLine(48 - k, 48, 0, k);
                g.setColor(Color.BLACK);
                g.drawLine(0, 48 - k, 48 - k, 0);
            }
            xIcon = new ImageIcon(img);
        }
        return xIcon;
    }

    public ImageIcon getEmptyIcon() {
        if (emptyIcon == null) {
            BufferedImage img = new BufferedImage(48, 48, BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, 48, 48);
            emptyIcon = new ImageIcon(img);
        }
        return emptyIcon;
    }

    public TicTacInterface getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(TicTacInterface gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void setxIcon(ImageIcon xIcon) {
        this.xIcon = xIcon;
    }

    public void setoIcon(ImageIcon oIcon) {
        this.oIcon = oIcon;
    }

    public void setEmptyIcon(ImageIcon emptyIcon) {
        this.emptyIcon = emptyIcon;
    }
}
