package scubase3;

import java.text.DecimalFormat;
import javax.swing.JTable;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class MethodsContainer {

    public static double calculateMOD(double in1, double in2) {

        double result;
        result = (in1 / in2 - 1.0) * 10.0;
        return result;
    }

    public static double calculateOxygenMOD(double in1, double in2) {

        double result;
        result = in2;
        return result;
    }

    public static double calculateBM(double in1, double in2) {

        double result;
        result = in1 / (in2 / 10.0 + 1.0);
        return result;
    }

    public static double calculateOxygenBM(double in1, double in2) {

        double result;
        result = in1 / (in2 / 10 + 1);
        return result;
    }

    public static double calculatePP(double in1, double in2) {

        double result;
        result = in1 * (in2 / 10.0 + 1.0);
        return result;
    }

    public static double calculateOxygenPP(double in1, double in2) {

        double result;
        result = in1;
        return result;
    }

    public static double calculateEAD(double in1, double in2) {

        double result;
        result = ((1.0 - in1) * (in2 / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
        return result;
    }

    public static double calculateOxygenEAD(double in1, double in2) {

        double result;
        result = in1;
        return result;
    }

    public static double calculateSMOD(double in1, double in2) {

        double result;
        result = (in1 / in2 - 1.0) * 10.0;
        return result;
    }

    public static double calculateOxygenSMOD(double in1, double in2) {

        double result;
        result = in2;
        return result;
    }

    public static JTable ppTable1() {
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
                        data[i][j] = "Danger";
                    } else {
                        data[i][j] = df.format(result) + "";
                    }
                }
            }

        }
        JTable ppjt = new JTable(data, column);
        return ppjt;
    }

    public static JTable eadTable1() {
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
                    data[i][j] = df.format(result) + "";
                }
            }

        }
        JTable eadjt = new JTable(data, column);
        return eadjt;
    }
}
