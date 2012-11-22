package test;

/**
 * Created by IntelliJ IDEA.
 * User: hannes
 * Date: Jun 19, 2008
 * Time: 11:35:13 PM
 * To change this template use File | Settings | File Templates.
 */

import Domain.TicTac;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Example for Test Driven Development
 */
public class TicTacTest extends TestCase {

    public TicTacTest(String name) {
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

    /**
     * check the empty board, no O nor X from players
     * @throws Exception test fails
     */
    public void testDisplayEmptyBoard() throws Exception {
        TicTac t = new TicTac();
        String s = t.displayBoard();
        assertFalse(s.contains("O"));
        assertFalse(s.contains("X"));
        System.out.println(s);
    }

    /**
     * test whether move is legal
     * @throws Exception if chosen index is out of bounds.
     */
    public void testCanMakeMove() throws Exception {
        TicTac t = new TicTac();
        assertTrue(t.canMakeMove(0, 0));
        assertFalse(t.canMakeMove(-1, 0));
        assertFalse(t.canMakeMove(0, 3));
        assertTrue(t.canMakeMove(2, 2));
        assertFalse(t.canMakeMove(3, 0));
        assertFalse(t.canMakeMove(0, -1));
        assertFalse(t.canMakeMove(1, 3));

        //assertFalse(t.canMakeMove(0,0));
    }

    public void testWhosTurn() throws Exception {
        TicTac t = new TicTac();
        assertTrue(t.whosTurn() == TicTac.player1);
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertTrue(t.whosTurn() == TicTac.player2);
        assertFalse(t.whosTurn() == TicTac.player1);
    }

    public void testMakingAMove() throws Exception {
        TicTac t = new TicTac();
        assertTrue(t.canMakeMove(0, 0));
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertFalse(t.makeMove(0, 1, TicTac.player1)); // not his turn
        assertFalse(t.canMakeMove(0, 0));
        assertFalse(t.makeMove(0, 0, TicTac.player1));
        assertFalse(t.makeMove(0, 0, TicTac.player2));
        assertTrue(t.makeMove(0, 1, TicTac.player2));
        System.out.println(t.displayBoard());
    }


    public static Test suite() {
        return new TestSuite(TicTacTest.class);
    }
}
