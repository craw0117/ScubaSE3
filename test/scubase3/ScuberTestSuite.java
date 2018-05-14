package scubase3;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author lyeqi
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({scubase3.ScubaCalculationsTest.class, scubase3.OutputPanelTest.class, scubase3.TablePanelTest.class, scubase3.ScubaModelTest.class, scubase3.TableInputTest.class, scubase3.InputPanelTest.class})
public class ScuberTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
