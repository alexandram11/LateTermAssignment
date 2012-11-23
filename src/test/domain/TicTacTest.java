package test.domain;

/**
 * Created by: Team Lethargic
 *
 * Date: Jun 19, 2008
 * Time: 11:35:13 PM
 * To change this template use File | Settings | File Templates.
 */

import Domain.TicTac;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * //TODO Vantar að commenta fullt af þessum testum. (Hægt er að nota shift+ctr numpad'-' til að fá betri yfir sýn)
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
        t.newGame();
        assertTrue(t.makeMove(2, 0, TicTac.player1));
        t.newGame();
        assertFalse(t.makeMove(2, 0, TicTac.player2));
        t.newGame();
        for (int i = 0; i < TicTac.boardSize; i++) {
            for (int j = 0; j < TicTac.boardSize; j++) {
                assertTrue(t.canMakeMove(i, j));
            }
        }
        t.newGame();
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
        //System.out.println(s); //For debugging the test
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
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X')
                xCount++;
            else if (s.charAt(i) == 'O')
                oCount++;
        }
        assertTrue(xCount == 5);
        assertTrue(oCount == 4);
        //System.out.println(s);//For debugging the test
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

    //TODO Vantar comment.
    public void testMakingAMove() throws Exception {
        TicTac t = new TicTac();
        assertTrue(t.canMakeMove(0, 0));
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertFalse(t.makeMove(0, 1, TicTac.player1)); // not his turn
        assertFalse(t.canMakeMove(0, 0));
        assertFalse(t.makeMove(0, 0, TicTac.player1));
        assertFalse(t.makeMove(0, 0, TicTac.player2));
        assertTrue(t.makeMove(0, 1, TicTac.player2));
        //System.out.println(t.displayBoard()); //For debugging the test
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
        // System.out.println(t.displayBoard()); //For debugging the test

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
        //System.out.println(a.displayBoard()); //For debugging the test
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
        //System.out.println(t.displayBoard()); //For debugging the test
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
        //System.out.println(t.displayBoard());//For debugging the test
    }

    public void testPlayerWhoWon() throws Exception {
        TicTac t = new TicTac();
        assertTrue(t.playerWhoWon() == 0); // should give 0 if no won has won.
        assertTrue(t.makeMove(1, 1, TicTac.player1));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(2, 0, TicTac.player2));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertTrue(t.playerWhoWon() == 1);
        //System.out.println(t.displayBoard()); //Debugging use Tests
        t.newGame();
        assertTrue(t.makeMove(1, 2, TicTac.player1));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(2, 0, TicTac.player2));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(2, 1, TicTac.player1));
        assertTrue(t.playerWhoWon() == 0);
        assertTrue(t.makeMove(0, 0, TicTac.player2));
        assertTrue(t.playerWhoWon() == 2);
        //System.out.println(t.displayBoard()); //For debugging the test
    }

    public void testGameOver() throws Exception {
        TicTac t = new TicTac();
        assertFalse(t.gameOver());
        // Test if board is full
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(0, 2, TicTac.player2));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(0, 1, TicTac.player1));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(1, 1, TicTac.player2));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(1, 2, TicTac.player1));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(2, 0, TicTac.player1));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(2, 1, TicTac.player2));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertTrue(t.gameOver());
        // none full board endGame.
        t.newGame();
        assertTrue(t.makeMove(1, 1, TicTac.player1));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(2, 0, TicTac.player2));
        assertFalse(t.gameOver());
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertTrue(t.gameOver());
    }

    public void testMovesAfterGameOver() throws Exception {
        TicTac t = new TicTac();
        // Test if board is full
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertTrue(t.makeMove(0, 2, TicTac.player2));
        assertTrue(t.makeMove(0, 1, TicTac.player1));
        assertTrue(t.makeMove(1, 1, TicTac.player2));
        assertTrue(t.makeMove(1, 2, TicTac.player1));
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertTrue(t.makeMove(2, 0, TicTac.player1));
        assertTrue(t.makeMove(2, 1, TicTac.player2));
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertFalse(t.makeMove(0, 0, TicTac.player1));
        for (int i = 0; i < TicTac.boardSize; i++)
            for (int j = 0; j < TicTac.boardSize; j++) {
                assertFalse(t.makeMove(i, j, TicTac.player2));
                assertFalse(t.makeMove(i, j, TicTac.player1));
            }

        // Diagonal test
        t.newGame();
        assertTrue(t.makeMove(1, 1, TicTac.player1));
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertTrue(t.makeMove(2, 0, TicTac.player2));
        assertTrue(t.makeMove(0, 0, TicTac.player1));
        assertFalse(t.makeMove(2, 1, TicTac.player2));
        for (int i = 0; i < TicTac.boardSize; i++)
            for (int j = 0; j < TicTac.boardSize; j++) {
                assertFalse(t.makeMove(i, j, TicTac.player2));
                assertFalse(t.makeMove(i, j, TicTac.player1));
            }

        // Vertical test
        t.newGame();
        assertTrue(t.makeMove(1, 1, TicTac.player1));
        assertTrue(t.makeMove(0, 0, TicTac.player2));
        assertTrue(t.makeMove(1, 2, TicTac.player1));
        assertTrue(t.makeMove(0, 1, TicTac.player2));
        assertTrue(t.makeMove(2, 2, TicTac.player1));
        assertTrue(t.makeMove(0, 2, TicTac.player2));
        assertFalse(t.makeMove(2, 1, TicTac.player1));
        for (int i = 0; i < TicTac.boardSize; i++)
            for (int j = 0; j < TicTac.boardSize; j++) {
                assertFalse(t.makeMove(i, j, TicTac.player2));
                assertFalse(t.makeMove(i, j, TicTac.player1));
            }
        // Horizontal test
        t.newGame();
        assertTrue(t.makeMove(1, 1, TicTac.player1));
        assertTrue(t.makeMove(0, 0, TicTac.player2));
        assertTrue(t.makeMove(2, 1, TicTac.player1));
        assertTrue(t.makeMove(1, 0, TicTac.player2));
        assertTrue(t.makeMove(0, 1, TicTac.player1));
        for (int i = 0; i < TicTac.boardSize; i++)
            for (int j = 0; j < TicTac.boardSize; j++) {
                assertFalse(t.makeMove(i, j, TicTac.player2));
                assertFalse(t.makeMove(i, j, TicTac.player1));
            }
    }

    public static Test suite() {
        return new TestSuite(TicTacTest.class);
    }
}
