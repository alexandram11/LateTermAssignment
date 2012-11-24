package test.Presentation;

/**
 * Created by: Team Lethargic
 *
 * Date: Jun 19, 2008
 * Time: 11:35:13 PM
 * To change this template use File | Settings | File Templates.
 */

import Domain.TicTac;
import Domain.TicTacInterface;
import Presentation.TicTacGUI;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * //TODO Vantar að bæta við testum til að prófa viðmót.
 * Example for Test Driven Development
 */
public class TicTacGuiTest extends TestCase {
    //The Implementation to use, for easy switching of impementations.
    TicTacInterface ticTacLogic = new TicTac();

    public TicTacGuiTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEmptyConstuctor() throws Exception {
        TicTac t = new TicTac();
    }

    public void testButtonIcons() throws Exception {
        TicTacGUI gui = new TicTacGUI(ticTacLogic);
        JButton button = new JButton(new ImageIcon("img/empty.png"));
        gui.actionPerformed(new ActionEvent(button, 1, "btn_1_1"));
        assertEquals(new ImageIcon("img/x.png").getImage(), ((ImageIcon) button.getIcon()).getImage());
        gui.actionPerformed(new ActionEvent(button, 1, "btn_1_2"));
        assertEquals(new ImageIcon("img/o.png").getImage(), ((ImageIcon) button.getIcon()).getImage());
    }

    public void testBoardEmptyIcons() throws Exception {
        TicTacGUI gui = new TicTacGUI(ticTacLogic);
        JPanel board = (JPanel)gui.getComponents()[0];
        for (Component c : board.getComponents())
            if (c.getClass() == JButton.class)
               assertEquals(((ImageIcon)((JButton)c).getIcon()).getImage(),new ImageIcon("img/empty.png").getImage());
    }

    public static Test suite() {
        return new TestSuite(TicTacGuiTest.class);
    }
}
