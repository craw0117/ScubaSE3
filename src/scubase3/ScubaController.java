package scubase3;

import javax.swing.JTable;

/**
 * Controller class, holds a reference to both the model object and provides
 * "patch-through" methods to ensure that all data updates pass through the
 * controller object
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaController {

    /**
     * A reference to the model object, it is private to ensure that MVC is
     * strictly adhered to.
     */
    private final ScubaModel model;

    /**
     * Default constructor, only one instance of this object should exist at any
     * one time. This application is designed using a loose implementation of
     * the MVC architecture pattern.
     */
    public ScubaController() {
        model = new ScubaModel(this);
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @param value
     * @see ScubaModel#setViewVisibility(boolean value)
     */
    public void setViewVisibility(boolean value) {
        model.setViewVisibility(value);
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @see ScubaModel#update()
     */
    public void forceViewUpdate() {
        model.update();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @param value
     * @see ScubaModel#setCalculationType(String value)
     */
    public void setCalculationType(String value) {
        model.setCalculationType(value);
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @param value
     * @see ScubaModel#setPartialPressure(double value)
     */
    public void setPartialPressure(double value) {
        model.setPartialPressure(value);
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @param value
     * @see ScubaModel#setOxygenFraction(double)
     */
    public void setOxygenFraction(double value) {
        model.setOxygenFraction(value);
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @param value
     * @see ScubaModel#setDepth(double value)
     */
    public void setDepth(double value) {
        model.setDepth(value);
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @param value
     * @see ScubaModel#setTableType(String value)
     */
    public void setTableType(String value) {
        model.setTableType(value);
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getCalculationType()
     */
    public String getCalculationType() {
        return model.getCalculationType();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getTableType()
     */
    public String getTableType() {
        return model.getTableType();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getInputFlags()
     */
    public int getInputFlags() {
        return model.getInputFlags();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getPartialPressure()
     */
    public double getPartialPressure() {
        return model.getPartialPressure();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getOxygenFraction()
     */
    public double getOxygenFraction() {
        return model.getOxygenFraction();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getDepth()
     */
    public double getDepth() {
        return model.getDepth();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getOutputValue()
     */
    public String getOutputValue() {
        return model.getOutputValue();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getOutputUnit()
     */
    public String getOutputUnit() {
        return model.getOutputUnit();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getOutputOxygen()
     */
    public String getOutputOxygen() {
        return model.getOutputOxygen();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getEADTable()
     */
    public JTable getEADTable() {
        return model.getEADTable();
    }

    /**
     * Patch-through method for ScubaModel
     *
     * @return
     * @see ScubaModel#getPPTable()
     */
    public JTable getPPTable() {
        return model.getPPTable();
    }
}
