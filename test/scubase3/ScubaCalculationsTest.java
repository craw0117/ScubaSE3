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
     * Test of calculateMOD method, of class MethodsContainer.
     */
    @Test

    public void testCalculateMOD() {
        System.out.println("calculateMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        String expResult = "29m";
        String result = ScubaCalculations.calculateMOD(in1, in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateOxygenMOD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenMOD() {
        System.out.println("calculateOxygenMOD");
        double in1 = 0.0;
        double in2 = 0.27;
        String expResult = "27";
        String result = ScubaCalculations.calculateOxygenMOD(in1, in2);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculateBM method, of class MethodsContainer.
     */
    @Test
    public void testCalculateBM1() {
        System.out.println("calculateBM");
        double in1 = 1.4;
        double in2 = 41.0;
        String expResult = "27%";
        String result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result);

    }

    public void testCalculateBM2() {
        System.out.println("calculateBM");
        double in1 = 1.6;
        double in2 = 3.0;
        String expResult = "Result is out of range!";
        String result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculateOxygenBM method, of class MethodsContainer.
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
     * Test of calculatePP method, of class MethodsContainer.
     */
    @Test
    public void testCalculatePP1() {
        System.out.println("calculatePP");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "1.3ata";
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }

    public void testCalculatePP2() {
        System.out.println("calculatePP");
        double in1 = 0.5;
        double in2 = 67.0;
        String expResult = "Result is out of range!";
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }
    public void testCalculatePP3() {
        System.out.println("calculatePP");
        double in1 = 0.8;
        double in2 = 3.0;
        String expResult = "Result is out of range!";
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }
    /**
     * Test of calculateOxygenPP method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenPP1() {
        System.out.println("calculateOxygenPP");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "32";
        String result = ScubaCalculations.calculateOxygenPP(in1, in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateEAD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateEAD1() {
        System.out.println("calculateEAD");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "24m";
        String result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result);

    }

    public void testCalculateEAD2() {
        System.out.println("calculateEAD");
        double in1 = 0.5;
        double in2 = 3.0;
        String expResult = "Result is out of range!";
        String result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result);

    }

    /**
     * Test of calculateOxygenEAD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenEAD() {
        System.out.println("calculateOxygenEAD");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "32";
        String result = ScubaCalculations.calculateOxygenEAD(in1, in2);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculateSMOD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateSMOD1() {
        System.out.println("calculateSMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        String expResult = "29m";
        String result = ScubaCalculations.calculateSMOD(in1, in2);
        assertEquals(expResult, result);
    }

    public void testCalculateSMOD2() {
        System.out.println("calculateSMOD");
        double in1 = 1.4;
        double in2 = 3.0;
        String expResult = "Result is out of range!";
        String result = ScubaCalculations.calculateSMOD(in1, in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateOxygenSMOD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenSMOD() {
        System.out.println("calculateOxygenSMOD");
        double in1 = 1.4;
        double in2 = 0.32;
        String expResult = "32";
        String result = ScubaCalculations.calculateOxygenSMOD(in1, in2);
        assertEquals(expResult, result);

    }

    /**
     * Test of ppTable method, of class MethodsContainer.
     */
    @Test
    public void testPpTable1() {
        System.out.println("ppTable1");
        String expResult = "Not Applicable";
        JTable table = ScubaCalculations.ppTable();
        String result;
        result = (String) table.getModel().getValueAt(30, 20);
        assertEquals(expResult, result);

    }

    @Test
    public void testPpTable2() {
        System.out.println("ppTable2");
        String expResult = "1.4";
        JTable table = ScubaCalculations.ppTable();
        String result;
        result = (String) table.getModel().getValueAt(17, 10);
        assertEquals(expResult, result);

    }

    /**
     * Test of eadTable method, of class MethodsContainer.
     */
    @Test
    public void testEadTable1() {
        System.out.println("eadTable1");
        String expResult = "23";
        JTable table = ScubaCalculations.eadTable();
        String result;
        result = (String) table.getModel().getValueAt(17, 10);
        assertEquals(expResult, result);
    }

    @Test
    public void testEadTable2() {
        System.out.println("eadTable2");
        String expResult = "Not Applicable";
        JTable table = ScubaCalculations.eadTable();
        String result;
        result = (String) table.getModel().getValueAt(30, 1);
        assertEquals(expResult, result);
    }

}
