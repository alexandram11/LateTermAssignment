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


    public void testNewGame() throws Exception {
        TicTac t = new TicTac();
        assertTrue(t.makeMove(2, 0, TicTac.player1));
        t.NewGame();
        assertTrue(t.makeMove(2, 0, TicTac.player1));
        t.NewGame();
        assertFalse(t.makeMove(2,0, TicTac.player2));
        t.NewGame();
        for (int i=0; i<TicTac.boardSize; i++){
            for(int j=0; j<TicTac.boardSize; j++)   {
                assertTrue(t.canMakeMove(i,j));
            }
        }
        t.NewGame();
        assertFalse(t.checkDiagonalVictory());
        assertFalse(t.checkHorizontalVictory());
        assertFalse(t.checkHorizontalVictory());
        }


    /**
     * check the empty board, no O nor X from players
     *
     * @throws Exception test fails
     */
    public void testDisplayEmptyBoard() throws Exception {
        TicTac t = new TicTac();
        String s = t.displayBoard();
        assertFalse(s.contains("O"));
        assertFalse(s.contains("X"));
        System.out.println(s);
    }


    public void testDisplayFullBoard() throws Exception {
        TicTac t = new TicTac();
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertTrue(t.makeMove(0, 2, TicTac.player2));
        assertTrue(t.makeMove(0, 1, TicTac.player1));
        assertTrue(t.makeMove(1, 1, TicTac.player2));
        assertTrue(t.makeMove(1, 2, TicTac.player1));
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertTrue(t.makeMove(2, 0, TicTac.player1));
        assertTrue(t.makeMove(2, 1, TicTac.player2));
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        String s = t.displayBoard();

        s = s.trim();
        int xCount = 0, oCount = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == 'X')
                xCount++;
            else if (s.charAt(i) == 'O')
                oCount++;
        }
        assertTrue(xCount == 5);
        assertTrue(oCount == 4);
        System.out.println(s);
    }

    /**
     * test whether chosen move is legal
     *
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
    }

    /**
     * test whose turn it is
     *
     * @throws Exception if the wrong player wants to
     */
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

    /**
     * tests the victory diagonal
     *
     * @throws Exception
     */
    public void testCheckDiagonalVictory() throws Exception {
        TicTac t = new TicTac();
        assertFalse(t.checkDiagonalVictory());
        assertTrue(t.makeMove(1, 1, TicTac.player1));
        assertFalse(t.checkDiagonalVictory());
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertFalse(t.checkDiagonalVictory());
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertFalse(t.checkDiagonalVictory());
        assertTrue(t.makeMove(2, 0, TicTac.player2));
        assertFalse(t.checkDiagonalVictory());
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertTrue(t.checkDiagonalVictory());
        System.out.println(t.displayBoard());

        TicTac a = new TicTac();
        assertFalse(a.checkDiagonalVictory());
        assertTrue(a.makeMove(1, 1, TicTac.player1));
        assertFalse(a.checkDiagonalVictory());
        assertTrue(a.makeMove(1, 0, TicTac.player2));
        assertFalse(a.checkDiagonalVictory());
        assertTrue(a.makeMove(2, 0, TicTac.player1));
        assertFalse(a.checkDiagonalVictory());
        assertTrue(a.makeMove(0, 0, TicTac.player2));
        assertFalse(a.checkDiagonalVictory());
        assertTrue(a.makeMove(0, 2, TicTac.player1));
        assertTrue(a.checkDiagonalVictory());
        System.out.println(a.displayBoard());
    }

    public void testCheckVerticalVictory() throws Exception {
        TicTac t = new TicTac();
        assertFalse(t.checkVerticalVictory());
        assertTrue(t.makeMove(1, 1, TicTac.player1));
        assertFalse(t.checkVerticalVictory());
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertFalse(t.checkVerticalVictory());
        assertTrue(t.makeMove(0, 1, TicTac.player1));
        assertFalse(t.checkVerticalVictory());
        assertTrue(t.makeMove(2, 0, TicTac.player2));
        assertFalse(t.checkVerticalVictory());
        assertTrue(t.makeMove(2, 1, TicTac.player1));
        assertTrue(t.checkVerticalVictory());
        System.out.println(t.displayBoard());
    }

    public void testCheckHorizontalVictory() throws Exception {
        TicTac t = new TicTac();
        assertFalse(t.checkHorizontalVictory());
        assertTrue(t.makeMove(2, 0, TicTac.player1));
        assertFalse(t.checkHorizontalVictory());
        assertTrue(t.makeMove(1, 1, TicTac.player2));
        assertFalse(t.checkHorizontalVictory());
        assertTrue(t.makeMove(2, 1, TicTac.player1));
        assertFalse(t.checkHorizontalVictory());
        assertTrue(t.makeMove(1, 2, TicTac.player2));
        assertFalse(t.checkHorizontalVictory());
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertTrue(t.checkHorizontalVictory());
        System.out.println(t.displayBoard());
    }


    public static Test suite() {
        return new TestSuite(TicTacTest.class);
    }
}
