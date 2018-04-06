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

    public void testCalculateMODNormal() {
        System.out.println("calculateMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        double expResult = 28.8;
        double result = ScubaCalculations.calculateMOD(in1, in2);
        assertEquals(expResult, result, 0.1);
    }

    public void testCalculateMODUpperbound() {
        System.out.println("calculateMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        double expResult = 28.8;
        double result = ScubaCalculations.calculateMOD(in1, in2);
        assertEquals(expResult, result, 0.1);

    }

    public void testCalculateMODLowerbound() {
        System.out.println("calculateMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        double expResult = 28.8;
        double result = ScubaCalculations.calculateMOD(in1, in2);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calculateOxygenMOD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenMOD() {
        System.out.println("calculateOxygenMOD");
        double in1 = 0.0;
        double in2 = 0.27;
        double expResult = 0.27;
        double result = ScubaCalculations.calculateOxygenMOD(in1, in2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculateBM method, of class MethodsContainer.
     */
    @Test
    public void testCalculateBM() {
        System.out.println("calculateBM");
        double in1 = 1.4;
        double in2 = 41.0;
        double expResult = 0.27;
        double result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calculateOxygenBM method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenBM() {
        System.out.println("calculateOxygenBM");
        double in1 = 1.4;
        double in2 = 41.0;
        double expResult = 0.27;
        double result = ScubaCalculations.calculateOxygenBM(in1, in2);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calculatePP method, of class MethodsContainer.
     */
    @Test
    public void testCalculatePP() {
        System.out.println("calculatePP");
        double in1 = 0.32;
        double in2 = 30.0;
        double expResult = 1.28;
        double result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calculateOxygenPP method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenPP() {
        System.out.println("calculateOxygenPP");
        double in1 = 0.32;
        double in2 = 30.0;
        double expResult = 0.32;
        double result = ScubaCalculations.calculateOxygenPP(in1, in2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculateEAD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateEAD() {
        System.out.println("calculateEAD");
        double in1 = 0.32;
        double in2 = 30.0;
        double expResult = 24.0;
        double result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result, 1.0);

    }

    /**
     * Test of calculateOxygenEAD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenEAD() {
        System.out.println("calculateOxygenEAD");
        double in1 = 0.32;
        double in2 = 30.0;
        double expResult = 0.32;
        double result = ScubaCalculations.calculateOxygenEAD(in1, in2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of calculateSMOD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateSMOD() {
        System.out.println("calculateSMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        double expResult = 28.8;
        double result = ScubaCalculations.calculateSMOD(in1, in2);
        assertEquals(expResult, result, 0.1);

    }

    /**
     * Test of calculateOxygenSMOD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenSMOD() {
        System.out.println("calculateOxygenSMOD");
        double in1 = 1.4;
        double in2 = 0.32;
        double expResult = 0.32;
        double result = ScubaCalculations.calculateOxygenSMOD(in1, in2);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of ppTable1 method, of class MethodsContainer.
     */
    @Test
    public void testPpTable1() {
        System.out.println("ppTable1");
        String expResult = "1.4";
        JTable table = ScubaCalculations.ppTable1();
        String result;
        result = (String) table.getModel().getValueAt(17, 10);
        assertEquals(expResult, result);

    }

    /**
     * Test of eadTable1 method, of class MethodsContainer.
     */
    @Test
    public void testEadTable1() {
        System.out.println("eadTable1");
        String expResult = "23";
        JTable table = ScubaCalculations.eadTable1();
        String result;
        result = (String) table.getModel().getValueAt(17, 10);
        assertEquals(expResult, result);
    }

}
