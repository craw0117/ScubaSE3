package scubase3;

import javax.swing.JTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaCalculationsTest {

    public ScubaCalculationsTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculateMOD method, of class ScubaCalculations.
     */
    @Test

    public void testCalculateMOD() {
        System.out.println("calculateMOD");
        double in1 = 1.3;
        double in2 = 0.36;
        String expResult = "26.1";
        String result = ScubaCalculations.calculateMOD(in1, in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateBM method, of class ScubaCalculations.
     */
    @Test
    public void testCalculateBMNormal() {
        System.out.println("calculateBM");
        double in1 = 1.4;
        double in2 = 41.0;
        String expResult = "27";
        String result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result);

    }

    @Test
    public void testCalculateBMLowerBoundary() {
        System.out.println("calculateBM");
        double in1 = 1.3;
        double in2 = 67.0;
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result);

    }

    @Test
    public void testCalculateBMUpperBoundary() {
        System.out.println("calculateBM");
        double in1 = 1.3;
        double in2 = 12.0;
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculateOxygenBM method, of class ScubaCalculations.
     */
    @Test
    public void testCalculateOxygenBM() {
        System.out.println("calculateOxygenBM");
        double in1 = 1.4;
        double in2 = 41.0;
        String expResult = "27";
        String result = ScubaCalculations.calculateOxygenBM(in1, in2);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculatePP method, of class ScubaCalculations.
     */
    @Test
    public void testCalculatePPNormal() {
        System.out.println("calculatePP");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "1.28";
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculatePPLowerBoundary() {
        System.out.println("calculatePP");
        double in1 = 0.22;
        double in2 = 4.0;
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculatePPUpperBoundary() {
        System.out.println("calculatePP");
        double in1 = 0.5;
        double in2 = 67.0;
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateEAD method, of class ScubaCalculations.
     */
    @Test
    public void testCalculateEADNormal() {
        System.out.println("calculateEAD");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "24.4";
        String result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result);

    }

    @Test
    public void testCalculateEADLowerBoundary() {
        System.out.println("calculateEAD");
        double in1 = 0.22;
        double in2 = 4.0;
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateEADUpperBoundary() {
        System.out.println("calculateEAD");
        double in1 = 0.5;
        double in2 = 30.0;
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result);
    }

    /**
     * Test of calculateOxygen method, of class ScubaCalculations.
     */
    @Test
    public void testCalculateOxygen() {
        System.out.println("calculateOxygen");
        double in1 = 0.32;
        String expResult = "32";
        String result = ScubaCalculations.calculateOxygen(in1);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculateSMOD method, of class ScubaCalculations.
     */
    @Test
    public void testCalculateSMOD() {
        System.out.println("calculateSMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        String expResult = "28.9";
        String result = ScubaCalculations.calculateSMOD(in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of ppTable method, of class ScubaCalculations.
     */
    @Test
    public void testPpTable1() {
        System.out.println("ppTable1");
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        JTable table = ScubaCalculations.ppTable();
        String result;
        result = (String) table.getModel().getValueAt(30, 20);
        assertEquals(expResult, result);

    }

    @Test
    public void testPpTable2() {
        System.out.println("ppTable2");
        String expResult = "1.40";
        JTable table = ScubaCalculations.ppTable();
        String result;
        result = (String) table.getModel().getValueAt(17, 10);
        assertEquals(expResult, result);

    }

    /**
     * Test of eadTable method, of class ScubaCalculations.
     */
    @Test
    public void testEadTable1() {
        System.out.println("eadTable1");
        String expResult = "22.9";
        JTable table = ScubaCalculations.eadTable();
        String result;
        result = (String) table.getModel().getValueAt(17, 10);
        assertEquals(expResult, result);
    }

    @Test
    public void testEadTable2() {
        System.out.println("eadTable2");
        String expResult = Const.UNSAFE_OUTPUT_VALUE;
        JTable table = ScubaCalculations.eadTable();
        String result;
        result = (String) table.getModel().getValueAt(30, 1);
        assertEquals(expResult, result);
    }

}
