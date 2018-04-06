package scubase3;

import java.text.DecimalFormat;
import javax.swing.JTable;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaModel {

    private final ScubaFrame view;

    private int inputFlags;
    private String calculationType;
    private double partialPressure;
    private double fractionOxygen;
    private double depthPressure; //in ata
    private double outputValue;
    private double outputOxygen;
    private JTable eadTable;
    private JTable ppTable;

    private String tableType;

    /**
     * This is the stub constructor for use without the view.
     */
    public ScubaModel() {
        this.view = null;
        initState();
    }

    /**
     * This is the integrated model for use with the view.
     *
     * @param view
     */
    public ScubaModel(ScubaFrame view) {
        this.view = view;

        initState();
    }

    private void updateView() {
        if (view != null) {
            view.update();
        }
    }

    /**
     * Default state setup.
     */
    private void initState() {
        calculationType = "EAD";
        inputFlags = Const.FLAG_DEPTH | Const.FLAG_FRAC_OXYGEN;

        partialPressure = 1.4;
        fractionOxygen = .32;
        depthPressure = 3.88;

        tableType = "EAD";
        
        eadTable = makeEadTable();
        ppTable = makePpTable();
    }

    public String getCalculationType() {
        return calculationType;
    }
    
    /**
     * Sets the calculation to perform and will enable the correct inputs.
     *
     * @param calcType Code for calc ["EAD", "MOD", "MIX", "PP", "SMOD"]
     */
    public void setCalculationType(String calcType) {
        switch (calcType) {
            case "EAD":
                this.inputFlags = Const.FLAG_DEPTH | Const.FLAG_FRAC_OXYGEN;
                this.outputValue = ((1.0 - this.fractionOxygen) * (this.depthPressure / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
                this.outputOxygen = this.fractionOxygen;
                break;
            case "MOD":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_FRAC_OXYGEN;
                this.outputValue = (this.partialPressure / this.fractionOxygen - 1.0) * 10.0;
                this.outputOxygen = this.fractionOxygen;
                break;
            case "BM":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                this.outputValue = this.partialPressure / (this.depthPressure / 10.0 + 1.0);
                this.outputOxygen = this.partialPressure / (this.depthPressure / 10 + 1);
                break;
            case "PP":
                this.inputFlags = Const.FLAG_FRAC_OXYGEN | Const.FLAG_DEPTH;
                this.outputValue = this.fractionOxygen * (this.depthPressure / 10.0 + 1.0);
                this.outputOxygen = this.fractionOxygen;
                break;
            case "SMOD":
                this.inputFlags = Const.FLAG_FRAC_OXYGEN;
                this.outputValue = (this.partialPressure / this.fractionOxygen - 1.0) * 10.0;
                this.outputOxygen = this.fractionOxygen;
                break;
            default:
                throw new java.lang.Error("Invalid calcType: " + calcType);

        }

        this.calculationType = calcType;
        updateView();
    }

    public double getPartialPressure() {
        return partialPressure;
    }

    public void setPartialPressure(double partialPressure) {
        this.partialPressure = partialPressure;
        updateView();
    }

    public double getFractionOxygen() {
        return fractionOxygen;
    }

    public void setFractionOxygen(double fractionOxygen) {
        this.fractionOxygen = fractionOxygen;
        updateView();
    }

    public double getDepthPressure() {
        return depthPressure;
    }

    public void setDepthPressure(double pressure) {
        this.depthPressure = pressure;
        updateView();
    }

    public double getDepth() {
        // we store depth as the pressure from depth
        // thus must calculate it on demand
        return depthPressure;
    }

    public void setDepth(double depth) {
        this.depthPressure = depth;
        updateView();
    }

    public int getInputFlags() {
        return inputFlags;
    }

    public void setInputFlags(int inputFlags) {
        this.inputFlags = inputFlags;
        updateView();
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
        updateView();
    }

    public double getOutputValue() {
        return outputValue;
    }

    public double getOutputOxygen() {
        return outputOxygen;
    }

    private JTable makePpTable() {
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

    private JTable makeEadTable() {
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

    public JTable getEadTable() {
        return eadTable;
    }

    public JTable getPpTable() {
        return ppTable;
    }
}
