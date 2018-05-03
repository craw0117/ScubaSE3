package scubase3;

import java.text.DecimalFormat;
import javax.swing.JTable;

/**
 * Calculations are separated from the ScubaModel class to simplify the process
 * of updating calculations, methods in this class are called from the
 * ScubaModel class.
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaCalculations {

    /**
     * Converts the oxygen fraction into a percentile for use in the oxygen
     * cylinder, i.e. 0.16 -> 16%
     *
     * @param oxygenFraction
     * @return
     * @see #calculateOxygenBM(double partialPressure, double oxygenFraction)
     */
    public static String calculateOxygen(double oxygenFraction) {
        return Const.DF_O2.format(oxygenFraction * 100);
    }

    /**
     * Calculates the Maximum Operating Depth using the provided parameters.
     *
     * @param partialPressure
     * @param oxygenFraction
     * @return
     * @see #calculateSMOD(double oxygenFraction)
     */
    public static String calculateMOD(double partialPressure, double oxygenFraction) {
        return Const.DF_DEPTH.format((partialPressure / oxygenFraction - 1.0) * 10.0);
    }

    /**
     * Calculates the Best Mix using the provided parameters, for any result
     * outside of the acceptable ranges, an appropriate warning message will be
     * displayed.
     *
     * @param partialPressure
     * @param depth
     * @return
     */
    public static String calculateBM(double partialPressure, double depth) {
        double result = partialPressure / (depth / 10.0 + 1.0) * 100.0;
        if (result > 50 || result < 22) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        return Const.DF_O2.format(result);
    }

    /**
     * Calculates the Partial Pressure using the provided parameters, for any
     * result outside of the acceptable ranges, an appropriate warning message
     * will be displayed.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     * @see #calculateRawPP(double oxygenFraction, double depth)
     */
    public static String calculatePP(double oxygenFraction, double depth) {
        double result = calculateRawPP(oxygenFraction, depth);
        if (result > 1.6 || result < 1.1) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        return Const.DF_PP.format(result);
    }

    /**
     * Calculates the Partial Pressure using the provided parameters, does not
     * perform any safety checks but instead returns the "raw" value.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     * @see #calculatePP(double oxygenFraction, double depth)
     */
    private static double calculateRawPP(double oxygenFraction, double depth) {
        return oxygenFraction * (depth / 10.0 + 1.0);
    }

    /**
     * Calculates the Equivalent Air Depth using the provided parameters, for
     * any result outside of the acceptable ranges, an appropriate warning
     * message will be displayed.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     */
    public static String calculateEAD(double oxygenFraction, double depth) {
        double partialPressure = calculateRawPP(oxygenFraction, depth);
        if (partialPressure > 1.6 || partialPressure < 1.1) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        double result = calculateRawEAD(oxygenFraction, depth);
        return Const.DF_DEPTH.format(result < 0 ? 0 : result);
    }

    /**
     * Calculates the Equivalent Air Depth using the provided parameters, does
     * not perform any safety checks but instead returns the "raw" value.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     * @see #calculateEAD(double oxygenFraction, double depth)
     */
    public static double calculateRawEAD(double oxygenFraction, double depth) {
        return ((1.0 - oxygenFraction) * (depth / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
    }

    /**
     * Calculates the Standard Maximum Operating Depth by using the standard
     * partial pressure constant and the oxygen fraction provided.
     *
     * @param oxygenFraction
     * @return
     * @see #calculateMOD(double partialPressure, double oxygenFraction)
     */
    public static String calculateSMOD(double oxygenFraction) {
        return ScubaCalculations.calculateMOD(Const.SMOD_PP_VALUE, oxygenFraction);
    }

    /**
     * Generates the Partial Pressure information table, this method should only
     * be called once to avoid unnecessary work.
     *
     * @return
     * @see #createEADTable()
     */
    public static JTable createPPTable() {
        String[] column = new String[24];
        column[0] = "Oxygen/Depth";
        for (int i = 1; i < column.length; i++) {
            column[i] = String.valueOf(i * 3) + Const.UNIT_METERS;
        }
        String[][] data = new String[33][24];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = String.valueOf(i + 18) + Const.UNIT_PERCENT;
                } else {
                    double result = calculateRawPP((i + 18.0) / 100.0, j * 3.0);
                    data[i][j] = result > 1.6 ? Const.UNSAFE_OUTPUT_VALUE : Const.DF_PP.format(result);
                }
            }
        }

        JTable table = new JTable(data, column);
        table.setEnabled(false);
        
        //set the min width of the first column
        table.getColumnModel().getColumn(0).setMinWidth(100);

        //set the min width of each other column
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setMinWidth(45);
        }
        
        return table;
    }

    /**
     * Generates the Equivalent Air Depth information table, this method should
     * only be called once to avoid unnecessary work.
     *
     * @return
     * @see #createPPTable()
     */
    public static JTable createEADTable() {
        String[] column = new String[24];
        column[0] = "Oxygen/Depth";
        for (int i = 1; i < column.length; i++) {
            column[i] = String.valueOf(i * 3) + Const.UNIT_METERS;
        }
        String[][] data = new String[33][24];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = String.valueOf(i + 18) + Const.UNIT_PERCENT;
                } else {
                    double partialPressure = calculateRawPP((i + 18.0) / 100.0, j * 3.0);
                    double result = calculateRawEAD((i + 18.0) / 100.0, j * 3.0);
                    data[i][j] = result < 0 ? "0.0" : partialPressure <= 1.6 ? Const.DF_DEPTH.format(result) : Const.UNSAFE_OUTPUT_VALUE;
                }
            }
        }

        JTable table = new JTable(data, column);
        table.setEnabled(false);
        
        //set the min width of the first column
        table.getColumnModel().getColumn(0).setMinWidth(100);

        //set the min width of each other column
        for (int i = 1; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setMinWidth(45);
        }

        return table;
    }
}
