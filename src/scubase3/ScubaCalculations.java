package scubase3;

import java.text.DecimalFormat;
import javax.swing.JTable;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
// The Oxygen value will be used for the cylinder in the next sprint
public class ScubaCalculations {

    private static final DecimalFormat ataFormat = new DecimalFormat("0.00");
    private static final DecimalFormat meterFormat = new DecimalFormat("0.0");
    private static final DecimalFormat oxygenFormat = new DecimalFormat("0");

    public static String calculateMOD(double in1, double in2) {
        double result;
        result = (in1 / in2 - 1.0) * 10.0;
        return meterFormat.format(result) + "m";
    }

    public static String calculateOxygenMOD(double in1, double in2) {
        double result;
        result = in2 * 100;
        return oxygenFormat.format(result);
    }

    public static String calculateBM(double in1, double in2) {
        double result;
        result = in1 / (in2 / 10.0 + 1.0) * 100.0;
        if (result > 50) {
            return "Input combination will cause harm! BM value too high!";
        }
        if (result < 22) {
            return "Input combination will cause harm! BM value too low!";
        }
        return oxygenFormat.format(result) + "%";
    }

    public static String calculateOxygenBM(double in1, double in2) {
        double result;
        result = in1 / (in2 / 10.0 + 1.0) * 100.0;
        return oxygenFormat.format(result);
    }

    public static String calculatePP(double in1, double in2) {

        double result;
        result = in1 * (in2 / 10.0 + 1.0);
        if (result > 1.6) {
            return "Input combination will cause harm! PP value too high!";
        }
        if (result < 1.1) {
            return "Input combination will cause harm! PP value too low!";
        }
        return ataFormat.format(result) + "ata";
    }

    public static String calculateOxygenPP(double in1, double in2) {

        double result;
        result = in1 * 100;
        return oxygenFormat.format(result);
    }

    public static String calculateEAD(double in1, double in2) {
        double pg;
        pg = in1 * (in2 / 10.0 + 1.0);
        if (pg > 1.6) {
            return "Input combination will cause harm! PP value too high!";
        }
        if (pg < 1.1) {
            return "Input combination will cause harm! PP value too low!";
        }
        double result;
        result = ((1.0 - in1) * (in2 / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
        if (result <= 0) {
            return "Result is out of range!";
        }
        return meterFormat.format(result) + "m";
    }

    public static String calculateOxygenEAD(double in1, double in2) {

        double result;
        result = in1 * 100;
        return oxygenFormat.format(result);
    }

    public static String calculateSMOD(double in1, double in2) {

        double result;
        result = (in1 / in2 - 1.0) * 10.0;
        return meterFormat.format(result) + "m";
    }

    public static String calculateOxygenSMOD(double in1, double in2) {

        double result;
        result = in2 * 100;
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
        return eadjt;
    }

    public static void main() {

        ScubaCalculations.calculateEAD(0, 0);

    }
}
