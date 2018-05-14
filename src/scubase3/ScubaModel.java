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
     * A reference to the view object, it is private to ensure that MVC is
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
    private int outputOxygen;
    private String outputUnit;
    private JTable eadTable;
    private JTable ppTable;
    private String tableType;
    private int showTableTab;
    private int[] tableParams;

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
     * Stub constructor normally used for testing.
     */
    ScubaModel() {
        view = null;
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
        oxygenFraction = 0.33;
        depth = 33.0;

        outputOxygen = 33;

        tableType = Const.TYPE_EAD;

        eadTable = ScubaTables.createEADTable();
        ppTable = ScubaTables.createPPTable();

        tableParams = new int[]{18, 50, 3, 69};

        showTableTab = 0;
    }

    /**
     * Sets the visibility of the view
     *
     * @param value
     */
    public void setViewVisibility(boolean value) {
        if (view != null) {
            view.setVisible(value);
        }
        update();
    }

    /**
     * Forces the ui to update
     */
    public void updateUI() {
        if (view != null) {
            SwingUtilities.updateComponentTreeUI(view);
            updateComponentTreeUI(view);
        }

    }

    /**
     * Patch-through method for ScubaFrame
     *
     * @see ScubaFrame#update()
     */
    public void update() {
        // The model can work without a view
        // it just means there is nothing that needs updating.
        if (view != null) {
            view.update();
        }
    }

    /**
     * Reruns and saves required calculations.
     */
    public void reCalculate() {
        switch (calculationType) {
            case Const.TYPE_EAD:
                inputFlags = Const.FLAG_DEPTH | Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateEAD(oxygenFraction, depth);
                outputOxygen = (int) Math.round(oxygenFraction * 100);
                outputUnit = Const.UNIT_METERS;
                break;
            case Const.TYPE_MOD:
                inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateMOD(partialPressure, oxygenFraction);
                outputOxygen = (int) Math.round(oxygenFraction * 100);
                outputUnit = Const.UNIT_METERS;
                break;
            case Const.TYPE_BM:
                inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                outputValue = ScubaCalculations.calculateBM(partialPressure, depth);
                if (outputValue != null && outputValue.length() > 0 && outputValue.matches("-?\\d+(\\.\\d+)?")) {
                    outputOxygen = Integer.parseInt(outputValue);
                }

                outputUnit = Const.UNIT_PERCENT;
                break;
            case Const.TYPE_PP:
                inputFlags = Const.FLAG_O2_FRACTION | Const.FLAG_DEPTH;
                outputValue = ScubaCalculations.calculatePP(oxygenFraction, depth);
                outputOxygen = (int) Math.round(oxygenFraction * 100);
                outputUnit = Const.UNIT_ATA;
                break;
            case Const.TYPE_SMOD:
                inputFlags = Const.FLAG_O2_FRACTION;
                outputValue = ScubaCalculations.calculateSMOD(oxygenFraction);
                outputOxygen = (int) Math.round(oxygenFraction * 100);
                outputUnit = Const.UNIT_METERS;
                break;
            default:
                break;
        }
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
        if (Const.CALC_TYPE_DICT.containsKey(value)) {
            showTableTab = 0;
            calculationType = value;
            reCalculate();
            update();
        } else {
            throw new java.lang.Error("Invalid calculation type: " + value);
        }

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
        if (value < Const.PP_MINIMUM || value > Const.PP_MAXIMUM) {
            //flush changes
            update();
            return;
        }
        if (Math.abs(value - partialPressure) > 0.001) {
            partialPressure = value;
            reCalculate();
            update();
        }
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
        if (value < Const.OXYGEN_MINIMUM || value > Const.OXYGEN_MAXIMUM) {
            //flush changes
            update();
            return;
        }
        if (Math.abs(value - oxygenFraction) > 0.001) {
            oxygenFraction = value;
            reCalculate();
            update();
        }
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
        if (value < Const.DEPTH_MINIMUM || value > Const.DEPTH_MAXIMUM) {
            //flush changes
            update();
            return;
        }
        if (Math.abs(value - depth) > 0.001) {
            depth = value;
            reCalculate();
            update();
        }
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
     * @see #setTableParams(double OxyMin, double OxyMax, double depthMin,
     * double depthMax)
     */
    public int[] getTableParams() {
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
    public void setTableParams(int OxyMin, int OxyMax, int depthMin, int depthMax) {
        if (OxyMin < Const.T_OXY_MIN || OxyMin > Const.T_OXY_MAX
                || OxyMax < Const.T_OXY_MIN || OxyMax > Const.T_OXY_MAX
                || depthMin < Const.T_DEPTH_MIN || depthMin > Const.T_DEPTH_MAX
                || depthMax < Const.T_DEPTH_MIN || depthMax > Const.T_DEPTH_MAX) {
            update(); //flush bad inputs
            return;
        }

        if (OxyMin > OxyMax || depthMin > depthMax) {
            update(); //flush bad inputs
            return;
        }

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
    public int getOutputOxygen() {
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
        eadTable = ScubaTables.createEADTable((int) Math.round(tableParams[0]),
                (int) Math.round(tableParams[1]),
                (int) Math.round(tableParams[2]),
                (int) Math.round(tableParams[3]));
        return eadTable;
    }

    /**
     * Gets the PP table stored in the model
     *
     * @return
     * @see #getEADTable()
     */
    public JTable getPPTable() {
        ppTable = ScubaTables.createPPTable((int) Math.round(tableParams[0]),
                (int) Math.round(tableParams[1]),
                (int) Math.round(tableParams[2]),
                (int) Math.round(tableParams[3]));

        return ppTable;
    }
    
}
