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
        String expResult = "28.9m";
        String result = ScubaCalculations.calculateMOD(in1, in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateOxygenMOD method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenMOD() {
        System.out.println("calculateOxygenMOD");
        double in1 = 0.36;
        String expResult = "36";
        String result = ScubaCalculations.calculateOxygenMOD(in1);
        assertEquals(expResult, result);

    }

    /**
     * Test of calculateBM method, of class MethodsContainer.
     */
    @Test
    public void testCalculateBMNormal() {
        System.out.println("calculateBM");
        double in1 = 1.4;
        double in2 = 41.0;
        String expResult = "27%";
        String result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result);

    }

    @Test
    public void testCalculateBMLowerBoundary() {
        System.out.println("calculateBM");
        double in1 = 1.3;
        double in2 = 67.0;
        String expResult = "Input combination will cause harm! BM value too low!";
        String result = ScubaCalculations.calculateBM(in1, in2);
        assertEquals(expResult, result);

    }

    @Test
    public void testCalculateBMUpperBoundary() {
        System.out.println("calculateBM");
        double in1 = 1.3;
        double in2 = 12.0;
        String expResult = "Input combination will cause harm! BM value too high!";
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
    public void testCalculatePPNormal() {
        System.out.println("calculatePP");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "1.28ata";
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculatePPLowerBoundary() {
        System.out.println("calculatePP");
        double in1 = 0.22;
        double in2 = 4.0;
        String expResult = "Input combination will cause harm! PP value too low!";
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculatePPUpperBoundary() {
        System.out.println("calculatePP");
        double in1 = 0.5;
        double in2 = 67.0;
        String expResult = "Input combination will cause harm! PP value too high!";
        String result = ScubaCalculations.calculatePP(in1, in2);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateOxygenPP method, of class MethodsContainer.
     */
    @Test
    public void testCalculateOxygenPP() {
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
    public void testCalculateEADNormal() {
        System.out.println("calculateEAD");
        double in1 = 0.32;
        double in2 = 30.0;
        String expResult = "24.4m";
        String result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result);

    }

    @Test
    public void testCalculateEADLowerBoundary() {
        System.out.println("calculateEAD");
        double in1 = 0.22;
        double in2 = 4.0;
        String expResult = "Input combination will cause harm! PP value too low!";
        String result = ScubaCalculations.calculateEAD(in1, in2);

        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateEADUpperBoundary() {
        System.out.println("calculateEAD");
        double in1 = 0.5;
        double in2 = 30.0;
        String expResult = "Input combination will cause harm! PP value too high!";
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
    public void testCalculateSMOD() {
        System.out.println("calculateSMOD");
        double in1 = 1.4;
        double in2 = 0.36;
        String expResult = "28.9m";
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
