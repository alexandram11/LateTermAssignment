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
    public void testDisplayBoard() throws Exception {
        TicTac t = new TicTac();
        String s = t.DisplayBoard();
        assertFalse(s.contains("O"));
        assertFalse(s.contains("X"));
    }


        public static Test suite() {
        return new TestSuite(TicTacTest.class);
    }
}
