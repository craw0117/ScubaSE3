package scubase3;

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
    private String outputValue;
    private String outputOxygen;
    private String outputUnit;
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
        calculationType = Const.CALCULATION_TYPE_EAD;
        inputFlags = Const.FLAG_DEPTH | Const.FLAG_FRAC_OXYGEN;

        partialPressure = 1.40;
        fractionOxygen = 0.32;
        depthPressure = 33.3;

        tableType = Const.CALCULATION_TYPE_EAD;

        eadTable = ScubaCalculations.eadTable();
        ppTable = ScubaCalculations.ppTable();
    }

    public String getCalculationType() {
        return calculationType;
    }

    /**
     * Sets the calculation to perform and will enable the correct inputs.
     *
     * @param calcType Code for calculation ["EAD", "MOD", "BM", "PP", "SMOD"]
     */
    public void setCalculationType(String calcType) {

        switch (calcType) {
            case Const.CALCULATION_TYPE_EAD:
                this.inputFlags = Const.FLAG_DEPTH | Const.FLAG_FRAC_OXYGEN;
                this.outputValue = ScubaCalculations.calculateEAD(this.fractionOxygen, this.depthPressure);
                this.outputOxygen = ScubaCalculations.calculateOxygen(this.fractionOxygen);
                this.outputUnit = Const.UNIT_METERS;
                break;
            case Const.CALCULATION_TYPE_MOD:
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_FRAC_OXYGEN;
                this.outputValue = ScubaCalculations.calculateMOD(this.partialPressure, this.fractionOxygen);
                this.outputOxygen = ScubaCalculations.calculateOxygen(this.fractionOxygen);
                this.outputUnit = Const.UNIT_METERS;
                break;
            case Const.CALCULATION_TYPE_BM:
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                this.outputValue = ScubaCalculations.calculateBM(this.partialPressure, this.depthPressure);
                this.outputOxygen = this.outputValue;
                this.outputUnit = "%";
                break;
            case Const.CALCULATION_TYPE_PP:
                this.inputFlags = Const.FLAG_FRAC_OXYGEN | Const.FLAG_DEPTH;
                this.outputValue = ScubaCalculations.calculatePP(this.fractionOxygen, this.depthPressure);
                this.outputOxygen = ScubaCalculations.calculateOxygen(this.fractionOxygen);
                this.outputUnit = "ata";
                break;
            case Const.CALCULATION_TYPE_SMOD:
                this.inputFlags = Const.FLAG_FRAC_OXYGEN;
                this.outputValue = ScubaCalculations.calculateSMOD(this.fractionOxygen);
                this.outputOxygen = ScubaCalculations.calculateOxygen(this.fractionOxygen);
                this.outputUnit = Const.UNIT_METERS;
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

    public String getOutputValue() {
        return outputValue;
    }

    public String getOutputOxygen() {
        return outputOxygen;
    }

    public String getOutputUnit() {
        return outputUnit;
    }

    public JTable getEadTable() {
        return eadTable;
    }

    public JTable getPpTable() {
        return ppTable;
    }
}
