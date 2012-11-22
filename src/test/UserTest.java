package test;

/**
 * Created by IntelliJ IDEA.
 * User: hannes
 * Date: Jun 19, 2008
 * Time: 11:35:13 PM
 * To change this template use File | Settings | File Templates.
 */

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;


/**
 * Example for Test Driven Development
 */
public class UserTest extends TestCase {

    public UserTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOneNameValuePair() throws Exception {

    }

    public static Test suite() {
        return new TestSuite(UserTest.class);
    }
}
