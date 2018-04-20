package scubase3;

import java.text.DecimalFormat;
import javax.swing.JTable;

/**
 * Container class for the calculations performed by the application
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
// The Oxygen value will be used for the cylinder in the next sprint
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
     * Calculates the Maximum Operating Depth using the provided parameters
     *
     * @param partialPressure
     * @param oxygenFraction
     * @return The result in meters
     */
    public static String calculateMOD(double partialPressure, double oxygenFraction) {
        return meterFormat.format((partialPressure / oxygenFraction - 1.0) * 10.0) + "m";
    }

    /**
     * Converts the oxygen fraction into a percentile for use in the oxygen
     * cylinder
     *
     * @param oxygenFraction
     * @return The result
     */
    public static String calculateOxygenMOD(double oxygenFraction) {
        return oxygenFormat.format(oxygenFraction * 100);
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
        if (result > 50) {
            return "Input combination will cause harm! BM value too high!";
        }
        if (result < 22) {
            return "Input combination will cause harm! BM value too low!";
        }
        return oxygenFormat.format(result) + "%";
    }

    public static String calculateOxygenBM(double partialPressure, double oxygenFraction) {
        double result = partialPressure / (oxygenFraction / 10.0 + 1.0) * 100.0;
        return oxygenFormat.format(result);
    }

    public static String calculatePP(double oxygenFraction, double depthPressure) {
        double result = oxygenFraction * (depthPressure / 10.0 + 1.0);
        if (result > 1.6) {
            return "Input combination will cause harm! PP value too high!";
        }
        if (result < 1.1) {
            return "Input combination will cause harm! PP value too low!";
        }
        return ataFormat.format(result) + "ata";
    }

    public static String calculateOxygenPP(double oxygenFraction, double depthPressure) {
        double result = oxygenFraction * 100;
        return oxygenFormat.format(result);
    }

    public static String calculateEAD(double oxygenFraction, double depthPressure) {
        double partialPressure = oxygenFraction * (depthPressure / 10.0 + 1.0);
        if (partialPressure > 1.6) {
            return "Input combination will cause harm! PP value too high!";
        }
        if (partialPressure < 1.1) {
            return "Input combination will cause harm! PP value too low!";
        }
        double result = ((1.0 - oxygenFraction) * (depthPressure / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
        if (result <= 0) {
            return "Result is out of range!";
        }
        return meterFormat.format(result) + "m";
    }

    public static String calculateOxygenEAD(double oxygenFraction, double depthPressure) {
        double result = oxygenFraction * 100;
        return oxygenFormat.format(result);
    }

    public static String calculateSMOD(double partialPressure, double oxygenFraction) {
        double result = (partialPressure / oxygenFraction - 1.0) * 10.0;
        return meterFormat.format(result) + "m";
    }

    public static String calculateOxygenSMOD(double partialPressure, double oxygenFraction) {
        double result = oxygenFraction * 100;
        return oxygenFormat.format(result);
    }

    public static JTable ppTable() {
        String[] column = new String[24];
        column[0] = "Oxygen(%)/Depth(m)";
        double result;
        for (int i = 1; i < column.length; i++) {
            int temp = i * 3;
            column[i] = temp + "";
        }
        String[][] data = new String[33][24];
        DecimalFormat df = new DecimalFormat("0.0");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = (i + 18) + "";
                } else {

                    result = (i + 18.0) / 100.0 * (j * 3.0 / 10.0 + 1.0);
                    if (result > 1.6) {
                        data[i][j] = "Not Applicable";
                    } else {
                        data[i][j] = df.format(result) + "";
                    }
                }
            }

        }

        JTable ppjt = new JTable(data, column);
        ppjt.setEnabled(false);
        ppjt.getColumnModel().getColumn(0).setPreferredWidth(120);

        return ppjt;
    }

    public static JTable eadTable() {
        String[] column = new String[24];
        column[0] = "Oxygen(%)/Depth(m)";
        double result;
        for (int i = 1; i < column.length; i++) {
            int temp = i * 3;
            column[i] = temp + "";
        }
        String[][] data = new String[33][24];
        DecimalFormat df = new DecimalFormat("0");
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (j == 0) {
                    data[i][j] = (i + 18) + "";
                } else {
                    result = ((1.0 - (i + 18.0) / 100.0) * ((j * 3.0) / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
                    if (result <= 0.0) {
                        data[i][j] = "Not Applicable";
                    } else {
                        data[i][j] = df.format(result) + "";
                    }
                }
            }

        }
        JTable eadjt = new JTable(data, column);
        eadjt.setEnabled(false);
        eadjt.getColumnModel().getColumn(0).setPreferredWidth(120);
        return eadjt;
    }
}
