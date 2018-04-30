package scubase3;

import javax.swing.JTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for ScubaCalculations
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaCalculationsTest {

    JTable ppTable = ScubaCalculations.createPPTable();
    JTable eadTable = ScubaCalculations.createEADTable();

    public String getTestResult(boolean value) {
        return value ? "Test passed!" : "Test failed!";
    }

    @Test
    public void calculateMODTest() {
        // Test data
        double partialPressure = 1.3;
        double oxygenFraction = 0.36;
        String expectedResult = Const.DF_DEPTH.format(26.11);
        String result = ScubaCalculations.calculateMOD(partialPressure, oxygenFraction);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateMODTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateBMTest() {
        // Test data
        double partialPressure = 1.4;
        double depth = 41.0;
        String expectedResult = Const.DF_O2.format(27);
        String result = ScubaCalculations.calculateBM(partialPressure, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateBMTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateBMLowerBoundaryTest() {
        // Test data
        double partialPressure = 1.3;
        double depth = 67.0;
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateBM(partialPressure, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateBMLowerBoundaryTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateBMUpperBoundaryTest() {
        // Test data
        double partialPressure = 1.3;
        double depth = 12.0;
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateBM(partialPressure, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateBMUpperBoundaryTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculatePPTest() {
        // Test data
        double oxygenFraction = 0.32;
        double depth = 30.0;
        String expectedResult = Const.DF_PP.format(1.28);
        String result = ScubaCalculations.calculatePP(oxygenFraction, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculatePPTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculatePPLowerBoundaryTest() {
        // Test data
        double oxygenFraction = 0.22;
        double depth = 4.0;
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculatePP(oxygenFraction, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculatePPLowerBoundaryTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculatePPUpperBoundaryTest() {
        // Test data
        double oxygenFraction = 0.5;
        double depth = 67.0;
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculatePP(oxygenFraction, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculatePPUpperBoundaryTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateEADTest() {
        // Test data
        double oxygenFraction = 0.32;
        double depth = 30.0;
        String expectedResult = Const.DF_DEPTH.format(24.4);
        String result = ScubaCalculations.calculateEAD(oxygenFraction, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateEADTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateEADLowerBoundaryTest() {
        // Test data
        double oxygenFraction = 0.22;
        double depth = 4.0;
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateEAD(oxygenFraction, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateEADLowerBoundaryTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateEADUpperBoundaryTest() {
        // Test data
        double oxygenFraction = 0.5;
        double depth = 30.0;
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = ScubaCalculations.calculateEAD(oxygenFraction, depth);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateEADUpperBoundaryTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateOxygenTest() {
        // Test data
        double oxygenFraction = 0.32;
        String expectedResult = Const.DF_O2.format(32);
        String result = ScubaCalculations.calculateOxygen(oxygenFraction);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("calculateOxygenTest() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void calculateSMODTest() {
        // Test data
        double oxygenFraction = 0.36;
        String expectedResult = Const.DF_DEPTH.format(28.9);
        String result = ScubaCalculations.calculateSMOD(oxygenFraction);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("testCalculateSMOD() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void ppTableTest1() {
        // Test data
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = (String) ppTable.getModel().getValueAt(30, 20);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("ppTableTest1() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void ppTableTest2() {
        // Test data
        String expectedResult = Const.DF_PP.format(1.4);
        String result = (String) ppTable.getModel().getValueAt(17, 10);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("ppTableTest2() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void eadTableTest1() {
        // Test data
        String expectedResult = Const.DF_DEPTH.format(22.9);
        String result = (String) eadTable.getModel().getValueAt(17, 10);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("eadTableTest1() - " + getTestResult(result.equals(expectedResult)));
    }

    @Test
    public void eadTableTest2() {

        // Test data
        String expectedResult = Const.UNSAFE_OUTPUT_VALUE;
        String result = (String) eadTable.getModel().getValueAt(30, 1);

        // Test conditions
        assertEquals(expectedResult, result);
        System.out.println("eadTableTest2() - " + getTestResult(result.equals(expectedResult)));
    }

}
