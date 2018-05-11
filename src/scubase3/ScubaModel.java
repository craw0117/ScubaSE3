package scubase3;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.updateComponentTreeUI;

/**
 * Model class, holds a reference to the view object and is responsible for
 * storing data and managing access to it. Whenever an update occurs within the
 * data, the view will also be updated.
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaModel {

    /**
     * A reference to the model object, it is private to ensure that MVC is
     * strictly adhered to.
     */
    private final ScubaFrame view;

    // Private fields used to store data
    private int inputFlags;
    private String calculationType;
    private double partialPressure;
    private double oxygenFraction;
    private double depth;
    private String outputValue;
    private String outputOxygen;
    private String outputUnit;
    private JTable eadTable;
    private JTable ppTable;
    private String tableType;
    private int showTableTab;
    private Double[] tableParams;

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
        oxygenFraction = 0.32;
        depth = 33.3;

        tableType = Const.TYPE_EAD;

        eadTable = ScubaTables.createEADTable();
        ppTable = ScubaTables.createPPTable();
        
        tableParams = new Double[]{0.18, 0.5, 3.0, 69.0};
        
        showTableTab = 0;
    }

    /**
     * Sets the visibility of the view
     *
     * @param value
     */
    public void setViewVisibility(boolean value) {
        view.setVisible(value);
        update();
    }

    /**
     * Forces the ui to update
     */
    public void updateUI() {
        SwingUtilities.updateComponentTreeUI(view);
        updateComponentTreeUI(view);
    }

    /**
     * Patch-through method for ScubaFrame
     *
     * @see ScubaFrame#update()
     */
    public void update() {
        view.update();
    }

    /**
     * Gets the current calculation type, will return one of the calculation
     * types as defined in the <code>Const</code> class
     *
     * @return
     * @see #setCalculationType(String value)
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
     * @see #getCalculationType()
     * @see Const
     */
    public void setCalculationType(String value) {
        switch (value) {
            case Const.TYPE_EAD:
                inputFlags = Const.FLAG_DEPTH | Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateEAD(oxygenFraction, depth);
                outputOxygen = ScubaCalculations.calculateOxygen(oxygenFraction);
                outputUnit = Const.UNIT_METERS;
                showTableTab = 0;
                break;
            case Const.TYPE_MOD:
                inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateMOD(partialPressure, oxygenFraction);
                outputOxygen = ScubaCalculations.calculateOxygen(oxygenFraction);
                outputUnit = Const.UNIT_METERS;
                showTableTab = 0;
                break;
            case Const.TYPE_BM:
                inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                outputValue = ScubaCalculations.calculateBM(partialPressure, depth);
                outputOxygen = outputValue;
                outputUnit = Const.UNIT_PERCENT;
                showTableTab = 0;
                break;
            case Const.TYPE_PP:
                inputFlags = Const.FLAG_O2_FRACTION | Const.FLAG_DEPTH;
                outputValue = ScubaCalculations.calculatePP(oxygenFraction, depth);
                outputOxygen = ScubaCalculations.calculateOxygen(oxygenFraction);
                outputUnit = Const.UNIT_ATA;
                showTableTab = 0;
                break;
            case Const.TYPE_SMOD:
                inputFlags = Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateSMOD(oxygenFraction);
                outputOxygen = ScubaCalculations.calculateOxygen(oxygenFraction);
                outputUnit = Const.UNIT_METERS;
                showTableTab = 0;
                break;
            default:
                throw new java.lang.Error("Invalid calculation type: " + value);
        }
        calculationType = value;
        update();
    }

    public void setTabView(int value) {
        showTableTab = value;
        
        update();
    }
    public int getTabView() {
        return showTableTab;
    }
    
    /**
     * Gets the partial pressure value stored in the model
     *
     * @return
     * @see #setPartialPressure(double value)
     */
    public double getPartialPressure() {
        return partialPressure;
    }

    /**
     * Sets the partial pressure value and updates the view.
     *
     * @param value a value between 1.1 and 1.6
     * @see #getPartialPressure()rowCount
     */
    public void setPartialPressure(double value) {
        partialPressure = value;
        update();
    }

    /**
     * Gets the oxygen fraction stored in the model
     *
     * @return
     * @see #setOxygenFraction(double)
     */
    public double getOxygenFraction() {
        return oxygenFraction;
    }

    /**
     * Sets the oxygen percentage and updates the view.
     *
     * @param value a value between 0 and 1.
     * @see #getOxygenFraction()
     */
    public void setOxygenFraction(double value) {
        oxygenFraction = value;
        update();
    }

    /**
     * Gets the depth stored in the model
     *
     * @return
     * @see #setDepth(double value)
     */
    public double getDepth() {
        return depth;
    }

    /**
     * Sets the depth and updates the view.
     *
     * @param value
     * @see #getDepth()
     */
    public void setDepth(double value) {
        depth = value;
        update();
    }

    /**
     * Gets the input flags, input flags are defined in <code>Const</code>
     *
     * @return
     * @see #setInputFlags(int value)
     * @see Const
     */
    public int getInputFlags() {
        return inputFlags;
    }

    /**
     * Sets the input flags and updates the view, input flags are defined in
     * <code>Const</code>
     *
     * @param value
     * @see #getInputFlags()
     * @see Const
     */
    public void setInputFlags(int value) {
        inputFlags = value;
        update();
    }

    /**
     * Gets the table type, table types are defined in <code>Const</code>
     *
     * @return
     * @see #setTableType(String value)
     * @see Const
     */
    public String getTableType() {
        return tableType;
    }

    /**
     * Sets the table type to display and updates the view, table types are
     * defined in <code>Const</code>
     *
     * @param value
     * @see #getTableType()
     * @see Const
     */
    public void setTableType(String value) {
        tableType = value;
        showTableTab = 1;
        update();
    }
    
    /**
     * Gets the table params in an array 
     *
     * @return Params in an array {OxyMin, OxyMax, depthMin, depthMax}
     * @see #setTableParams(double OxyMin, double OxyMax, double depthMin, double depthMax)
     */
    public Double[] getTableParams() {
        return tableParams;
    }
    
    /**
     * Sets the table parameters
     *
     * @param OxyMin Minmum oxygen percentage eg 0.22
     * @param OxyMax Maximum oxygen percentage eg 0.50
     * @param depthMin Min depth eg 33.0
     * @param depthMax Max depth eg 3.0
     * @see #getTableParams()
     */
    public void setTableParams(double OxyMin, double OxyMax, double depthMin, double depthMax) {
        tableParams[0] = OxyMin;
        tableParams[1] = OxyMax;
        tableParams[2] = depthMin;
        tableParams[3] = depthMax;
        update();
    }

    /**
     * Gets the output value for the calculation
     *
     * @return
     * @see #getOutputOxygen()
     * @see #getOutputUnit()
     */
    public String getOutputValue() {
        return outputValue;
    }

    /**
     * Gets the output value for oxygen
     *
     * @return
     * @see #getOutputValue()
     * @see #getOutputUnit()
     */
    public String getOutputOxygen() {
        return outputOxygen;
    }

    /**
     * Gets the output value unit, units are defined in <code>Const</code>
     *
     * @return
     * @see #getOutputOxygen()
     * @see #getOutputValue()
     * @see Const
     */
    public String getOutputUnit() {
        return outputUnit;
    }

    /**
     * Gets the EAD table stored in the model
     *
     * @return
     * @see #getPPTable()
     */
    public JTable getEADTable() {
        return eadTable;
    }

    /**
     * Gets the PP table stored in the model
     *
     * @return
     * @see #getEADTable()
     */
    public JTable getPPTable() {
        return ppTable;
    }
}
