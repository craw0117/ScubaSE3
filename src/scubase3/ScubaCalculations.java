package scubase3;

import java.text.DecimalFormat;
import javax.swing.JTable;

/**
 * Container class for the calculations performed by the application
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaCalculations {

    /**
     * Decimal format for output in ATA
     */
    private static final DecimalFormat ataFormat = new DecimalFormat("0.00");
    /**
     * Decimal format for output in meters
     */
    private static final DecimalFormat meterFormat = new DecimalFormat("0.0");
    /**
     * Decimal format for oxygen level output
     */
    private static final DecimalFormat oxygenFormat = new DecimalFormat("0");

    /**
     * Converts the oxygen fraction into a percentile for use in the oxygen
     * cylinder, i.e. 0.16 -> 16%
     *
     * @param oxygenFraction
     * @return
     */
    public static String calculateOxygen(double oxygenFraction) {
        return oxygenFormat.format(oxygenFraction * 100);
    }

    /**
     * Calculates the Maximum Operating Depth using the provided parameters
     *
     * @param partialPressure
     * @param oxygenFraction
     * @return
     */
    public static String calculateMOD(double partialPressure, double oxygenFraction) {
        return meterFormat.format((partialPressure / oxygenFraction - 1.0) * 10.0);
    }

    /**
     * Calculates the Best Mix of oxygen using the provided parameters
     *
     * @param partialPressure
     * @param oxygenFraction
     * @return
     */
    public static String calculateBM(double partialPressure, double oxygenFraction) {
        double result = partialPressure / (oxygenFraction / 10.0 + 1.0) * 100.0;
        if (result > 50 || result < 22) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        return oxygenFormat.format(result);
    }

    /**
     * Calculates the oxygen percentile for the Best Mix method, since the best
     * mix uses a slightly different approach, an alternate method is required
     * to calculate the oxygen percentile. This method is chained with the
     * calculateOxygen method to reduce duplicate code.
     *
     * @param partialPressure
     * @param oxygenFraction
     * @return
     */
    public static String calculateOxygenBM(double partialPressure, double oxygenFraction) {
        return ScubaCalculations.calculateOxygen(partialPressure / (oxygenFraction / 10.0 + 1.0));
    }

    /**
     * Calculates the partial pressure using the provided parameters, for any
     * result outside of the acceptable ranges, an appropriate warning message
     * will be displayed.
     *
     * @param oxygenFraction
     * @param depthPressure
     * @return
     */
    public static String calculatePP(double oxygenFraction, double depthPressure) {
        double result = oxygenFraction * (depthPressure / 10.0 + 1.0);
        if (result > 1.6 || result < 1.1) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        return ataFormat.format(result);
    }

    /**
     * Calculates the Equivalent Air Depth using the provided parameters
     *
     * @param oxygenFraction
     * @param depthPressure
     * @return
     */
    public static String calculateEAD(double oxygenFraction, double depthPressure) {
        double partialPressure = oxygenFraction * (depthPressure / 10.0 + 1.0);
        if (partialPressure > 1.6 || partialPressure < 1.1) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        double result = ((1.0 - oxygenFraction) * (depthPressure / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
        if (result < 0) {
            result = 0;
        }
        return meterFormat.format(result);
    }

    /**
     * Calculates the Standard Maximum Operating Depth by using the standard
     * partial pressure constant and the oxygen fraction provided. Chains the
     * calculateMOD method to reduce duplicate code.
     *
     * @param oxygenFraction
     * @return
     */
    public static String calculateSMOD(double oxygenFraction) {
        return ScubaCalculations.calculateMOD(Const.SMOD_PP_VALUE, oxygenFraction);
    }

    /**
     * Generates the Partial Pressure information table, this method should only
     * be called once to avoid unnecessary work.
     *
     * @return
     */
    public static JTable ppTable() {
        String[] column = new String[24];
        column[0] = "Oxygen(%)/Depth(m)";
        for (int i = 1; i < column.length; i++) {
            column[i] = String.valueOf(i * 3);
        }
        String[][] data = new String[33][24];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = String.valueOf(i + 18);
                } else {
                    double result = (i + 18.0) / 100.0 * (j * 3.0 / 10.0 + 1.0);
                    data[i][j] = result > 1.6 ? Const.UNSAFE_OUTPUT_VALUE : ataFormat.format(result);
                }
            }
        }

        JTable ppTable = new JTable(data, column);
        ppTable.setEnabled(false);
        ppTable.getColumnModel().getColumn(0).setPreferredWidth(120);

        return ppTable;
    }

    /**
     * Generates the Equivalent Air Depth information table, this method should
     * only be called once to avoid unnecessary work.
     *
     * @return
     */
    public static JTable eadTable() {
        String[] column = new String[24];
        column[0] = "Oxygen(%)/Depth(m)";
        for (int i = 1; i < column.length; i++) {
            column[i] = String.valueOf(i * 3);
        }
        String[][] data = new String[33][24];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = String.valueOf(i + 18);
                } else {
                    double result = ((1.0 - (i + 18.0) / 100.0) * ((j * 3.0) / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
                    data[i][j] = result <= 0.0 ? Const.UNSAFE_OUTPUT_VALUE : meterFormat.format(result);
                }
            }

        }
        JTable eadTable = new JTable(data, column);
        eadTable.setEnabled(false);
        eadTable.getColumnModel().getColumn(0).setPreferredWidth(120);

        return eadTable;
    }
}
