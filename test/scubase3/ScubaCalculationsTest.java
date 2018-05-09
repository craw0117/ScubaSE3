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

    JTable ppTable = ScubaTables.createPPTable();
    JTable eadTable = ScubaTables.createEADTable();

    /**
     * TestData class contains common variables and values to reduce duplicate
     * code, also allows test data to be easily inserted into an array and
     * iterated over.
     */
    private class TestData {

        public double inputA;
        public double inputB;
        public String expectedResult;

        public TestData(double inputA, double inputB, String expectedResult) {
            this.inputA = inputA;
            this.inputB = inputB;
            this.expectedResult = expectedResult;
        }

        /**
         * Returns "Test passed!" or "Test failed!" based on whether the
         * expected result is equal to the actual result
         *
         * @param result
         * @return
         */
        public String getTestStatus(String result) {
            assertEquals(expectedResult, result);
            return result.equals(expectedResult) ? "Test passed!" : "Test failed!";
        }
    }

    @Test
    public void calculateMODTest() {
        TestData td = new TestData(1.3, 0.36, Const.DF_DEPTH.format(26.11));
        System.out.println("calculateMODTest() - "
                + td.getTestStatus(ScubaCalculations.calculateMOD(td.inputA, td.inputB)));
    }

    @Test
    public void calculateBMTest() {
        TestData td = new TestData(1.4, 41.0, Const.DF_O2.format(27));
        System.out.println("calculateBMTest() - "
                + td.getTestStatus(ScubaCalculations.calculateBM(td.inputA, td.inputB)));
    }

    @Test
    public void calculateBMLowerBoundaryTest() {
        TestData td = new TestData(1.3, 67.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("calculateBMLowerBoundaryTest() - "
                + td.getTestStatus(ScubaCalculations.calculateBM(td.inputA, td.inputB)));
    }

    @Test
    public void calculateBMUpperBoundaryTest() {
        TestData td = new TestData(1.3, 12.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("calculateBMUpperBoundaryTest() - "
                + td.getTestStatus(ScubaCalculations.calculateBM(td.inputA, td.inputB)));
    }

    @Test
    public void calculatePPTest() {
        TestData td = new TestData(0.32, 30.0, Const.DF_PP.format(1.28));
        System.out.println("calculatePPTest() - "
                + td.getTestStatus(ScubaCalculations.calculatePP(td.inputA, td.inputB)));
    }

    @Test
    public void calculatePPLowerBoundaryTest() {
        TestData td = new TestData(0.22, 4.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("calculatePPLowerBoundaryTest() - "
                + td.getTestStatus(ScubaCalculations.calculatePP(td.inputA, td.inputB)));
    }

    @Test
    public void calculatePPUpperBoundaryTest() {
        TestData td = new TestData(0.5, 67.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("calculatePPUpperBoundaryTest() - "
                + td.getTestStatus(ScubaCalculations.calculatePP(td.inputA, td.inputB)));
    }

    @Test
    public void calculateEADTest() {
        TestData td = new TestData(0.32, 30.0, Const.DF_DEPTH.format(24.4));
        System.out.println("calculateEADTest() - "
                + td.getTestStatus(ScubaCalculations.calculateEAD(td.inputA, td.inputB)));
    }

    @Test
    public void calculateEADLowerBoundaryTest() {
        TestData td = new TestData(0.22, 4.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("calculateEADLowerBoundaryTest() - "
                + td.getTestStatus(ScubaCalculations.calculateEAD(td.inputA, td.inputB)));
    }

    @Test
    public void calculateEADUpperBoundaryTest() {
        TestData td = new TestData(0.5, 30.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("calculateEADUpperBoundaryTest() - "
                + td.getTestStatus(ScubaCalculations.calculateEAD(td.inputA, td.inputB)));
    }

    @Test
    public void calculateOxygenTest() {
        TestData td = new TestData(0.32, 0.0, Const.DF_O2.format(32));
        System.out.println("calculateOxygenTest() - "
                + td.getTestStatus(ScubaCalculations.calculateOxygen(td.inputA)));
    }

    @Test
    public void calculateSMODTest() {
        TestData td = new TestData(0.36, 0.0, Const.DF_DEPTH.format(28.9));
        System.out.println("calculateSMODTest() - "
                + td.getTestStatus(ScubaCalculations.calculateSMOD(td.inputA)));
    }

    @Test
    public void ppTableTest1() {
        TestData td = new TestData(0.0, 0.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("ppTableTest1() - "
                + td.getTestStatus((String) ppTable.getModel().getValueAt(30, 20)));
    }

    @Test
    public void ppTableTest2() {
        TestData td = new TestData(0.0, 0.0, Const.DF_PP.format(1.4));
        System.out.println("ppTableTest2() - "
                + td.getTestStatus((String) ppTable.getModel().getValueAt(17, 10)));
    }

    @Test
    public void eadTableTest1() {
        TestData td = new TestData(0.0, 0.0, Const.DF_DEPTH.format(22.9));
        System.out.println("eadTableTest1() - "
                + td.getTestStatus((String) eadTable.getModel().getValueAt(17, 10)));
    }

    @Test
    public void eadTableTest2() {
        TestData td = new TestData(0.0, 0.0, Const.DF_DEPTH.format(0));
        System.out.println("eadTableTest2() - "
                + td.getTestStatus((String) eadTable.getModel().getValueAt(30, 1)));
    }

    @Test
    public void eadTableTest3() {
        TestData td = new TestData(0.0, 0.0, Const.UNSAFE_OUTPUT_VALUE);
        System.out.println("eadTableTest3() - "
                + td.getTestStatus((String) eadTable.getModel().getValueAt(32, 23)));
    }

}
