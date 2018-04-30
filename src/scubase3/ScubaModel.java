package scubase3;

import javax.swing.JTable;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaModel {

    /**
     * A reference to the model object, it is private to ensure that MVC is
     * strictly adhered to.
     */
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
     * Default Constructor for ScubaModel, creates a view object using the
     * provided controller object, this ensures that MVC is adhered to
     * correctly. The model contains a reference to the view, but not the
     * controller.
     *
     * @param controller
     */
    public ScubaModel(ScubaController controller) {
        view = new ScubaFrame(controller);
        initState();
    }

    /**
     * Default state setup. Calculation type is set to EAD and input flags are
     * set accordingly, Table type is set to EAD and both the tables are
     * generated.
     */
    private void initState() {
        calculationType = Const.TYPE_EAD;
        inputFlags = Const.FLAG_DEPTH | Const.FLAG_O2_FRACTION;

        partialPressure = 1.40;
        fractionOxygen = 0.32;
        depthPressure = 33.3;

        tableType = Const.TYPE_EAD;

        eadTable = ScubaCalculations.eadTable();
        ppTable = ScubaCalculations.ppTable();
    }

    /**
     * Sets the visibility of the view
     *
     * @param value
     */
    public void setViewVisibility(boolean value) {
        view.setVisible(value);
    }

    /**
     * Force an update on the view, should only be used externally
     */
    public void forceViewUpdate() {
        view.update();
    }

    /**
     * Gets the current calculation type, will return one of the calculation
     * types as defined in the <code>Const</code> class
     *
     * @return
     * @see Const
     */
    public String getCalculationType() {
        return calculationType;
    }

    /**
     * Sets the current calculation type and enables the correct inputs, only
     * accepts the calculation types defined in <code>Const</code>.
     *
     * @param value
     * @see Const
     */
    public void setCalculationType(String value) {
        switch (value) {
            case Const.TYPE_EAD:
                inputFlags = Const.FLAG_DEPTH | Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateEAD(fractionOxygen, depthPressure);
                outputOxygen = ScubaCalculations.calculateOxygen(fractionOxygen);
                outputUnit = Const.UNIT_METERS;
                break;
            case Const.TYPE_MOD:
                inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateMOD(partialPressure, fractionOxygen);
                outputOxygen = ScubaCalculations.calculateOxygen(fractionOxygen);
                outputUnit = Const.UNIT_METERS;
                break;
            case Const.TYPE_BM:
                inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                outputValue = ScubaCalculations.calculateBM(partialPressure, depthPressure);
                outputOxygen = outputValue;
                outputUnit = Const.UNIT_PERCENT;
                break;
            case Const.TYPE_PP:
                inputFlags = Const.FLAG_O2_FRACTION | Const.FLAG_DEPTH;
                outputValue = ScubaCalculations.calculatePP(fractionOxygen, depthPressure);
                outputOxygen = ScubaCalculations.calculateOxygen(fractionOxygen);
                outputUnit = Const.UNIT_ATA;
                break;
            case Const.TYPE_SMOD:
                inputFlags = Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateSMOD(fractionOxygen);
                outputOxygen = ScubaCalculations.calculateOxygen(fractionOxygen);
                outputUnit = Const.UNIT_METERS;
                break;
            default:
                throw new java.lang.Error("Invalid calculation type: " + value);
        }
        calculationType = value;
        view.update();
    }

    /**
     * Gets the partial pressure value stored in the model
     *
     * @return
     */
    public double getPartialPressure() {
        return partialPressure;
    }

    /**
     * Sets the partial pressure value and updates the view.
     *
     * @param value a value between 1.1 and 1.6
     */
    public void setPartialPressure(double value) {
        partialPressure = value;
        view.update();
    }

    /**
     * Gets the oxygen fraction stored in the model
     *
     * @return
     */
    public double getFractionOxygen() {
        return fractionOxygen;
    }

    /**
     * Sets the oxygen percentage and updates the view.
     *
     * @param value a value between 0 and 1.
     */
    public void setFractionOxygen(double value) {
        fractionOxygen = value;
        view.update();
    }

    /**
     * Gets the depth pressure stored in the model
     *
     * @return
     */
    public double getDepthPressure() {
        return depthPressure;
    }

    /**
     * Sets the depth pressure
     *
     * @param value
     */
    public void setDepthPressure(double value) {
        depthPressure = value;
        view.update();
    }

    public double getDepth() {
        return depthPressure; // calculate depth problem?
    }

    /**
     * Sets the depth and updates the view.
     *
     * @param value
     */
    public void setDepth(double value) {
        this.depthPressure = value;
        this.view.update();
    }

    public int getInputFlags() {
        return inputFlags;
    }

    public void setInputFlags(int inputFlags) {
        this.inputFlags = inputFlags;
        this.view.update();
    }

    public String getTableType() {
        return tableType;
    }

    /**
     * Sets the table type to display and updates the view.
     *
     * @param value Valid types are "EAD", and "PP"
     */
    public void setTableType(String value) {
        tableType = value;
        view.update();
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

    public JTable getEADTable() {
        return eadTable;
    }

    public JTable getPPTable() {
        return ppTable;
    }
}
