package scubase3;

import javax.swing.JTable;

/**
 * Controller class, holds a reference to both the model and view objects.
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
    public void setFractionOxygen(double value) {
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

    public String getTableType() {
        return model.getTableType();
    }

    public int getInputFlags() {
        return model.getInputFlags();
    }

    public double getPartialPressure() {
        return model.getPartialPressure();
    }

    public double getFractionOxygen() {
        return model.getOxygenFraction();
    }

    public double getDepth() {
        return model.getDepth();
    }

    public String getOutputValue() {
        return model.getOutputValue();
    }

    public String getOutputUnit() {
        return model.getOutputUnit();
    }

    public String getOutputOxygen() {
        return model.getOutputOxygen();
    }

    public JTable getEADTable() {
        return model.getEADTable();
    }

    public JTable getPPTable() {
        return model.getPPTable();
    }
}
