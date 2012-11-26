package Presentation;

import Domain.TicTacInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Represnets the Gui for the TicTacTue, Implements all but the ActionListener.
 */
public abstract class AbsTicTacGameGUI extends JPanel implements ActionListener {
    protected JButton[][] btns = new JButton[3][3];
    protected JPanel upper;
    protected PlayerPanel lower;
    protected TicTacInterface gameLogic;
    protected int BoardSize = 3;

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

        GridLayout lMan = new GridLayout(BoardSize, BoardSize);
        upper = new JPanel(lMan);

        lower = new PlayerPanel(this);

        for (int i = 0; i < BoardSize; i++) {
            for (int j = 0; j < BoardSize; j++) {
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

    /**
     * Draws and returns the O icon.
     *
     * @return ImageIcon, The O icon used on the board.
     */
    public ImageIcon getOIcon() {
        if (oIcon == null) {
            int iconSize = 48;
            BufferedImage img = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, iconSize, iconSize);
            g.setColor(Color.BLACK);
            g.fillOval(0, 0, iconSize, iconSize);
            g.setColor(Color.white);
            g.fillOval(9, 9, 30, 30);
            oIcon = new ImageIcon(img);
        }
        return oIcon;
    }

    /**
     * Draws and returns the X icon.
     *
     * @return ImageIcon, The X icon used on the board.
     */
    public ImageIcon getXIcon() {
        if (xIcon == null) {
            int iconSize = 48;
            BufferedImage img = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, iconSize, iconSize);
            for (int k = 0; k < 4; k++) {
                g.setColor(Color.BLACK);
                g.drawLine(iconSize - k, iconSize, 0, k);
                g.setColor(Color.BLACK);
                g.drawLine(0, iconSize - k, iconSize - k, 0);
            }
            xIcon = new ImageIcon(img);
        }
        return xIcon;
    }

    /**
     * Draws and returns an emptyIcon
     *
     * @return ImageIcon, The Empty icon used on the board.
     */
    public ImageIcon getEmptyIcon() {
        if (emptyIcon == null) {
            int iconSize = 48;
            BufferedImage img = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_RGB);
            Graphics g = img.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, iconSize, iconSize);
            emptyIcon = new ImageIcon(img);
        }
        return emptyIcon;
    }

    /**
     * The the logic unit used by the the gui.
     *
     * @return The logic unit used by this gui, a class that implements the TicTacInterface,
     */
    public TicTacInterface getGameLogic() {
        return gameLogic;
    }

    /**
     * Used to dependency inject the logic into the gui.
     *
     * @param gameLogic The the logic unit used by the the gui.
     */
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
