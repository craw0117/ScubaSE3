package scubase3;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaController {

    private final ScubaModel model;
    private final ScubaFrame view;
    

    /**
     * Default constructor, only one instance of this object should exist at any
     * one time. This application is designed using a loose implementation of
     * the MVC architecture pattern.
     */
    public ScubaController() {
        this.view = new ScubaFrame(this);
        this.model = new ScubaModel(view);

        view.update();
    }

    /**
     * Provides external access to the model object.
     *
     * @return ScubaModel
     */
    public ScubaModel getModel() {
        return model;
    }

    /**
     * Provides external access to the view object.
     *
     * @return ScubaFrame
     */
    public ScubaFrame getView() {
        return view;
    }

    /**
     * Sets the calculation to perform and will enable the correct inputs.
     *
     * @param calcType Valid calcTypes are "EAD", "MOD", "MIX", "PP", and "SMOD"
     */
    public void setCalcType(String calcType) {
        model.setCalculationType(calcType);
    }

    /**
     * Sets the partial pressure value and updates the view.
     *
     * @param value Accepts values between 1.1 and 1.6
     */
    public void setPartialPressure(double value) {
        model.setPartialPressure(value);
    }

    /**
     * Sets the oxygen percentage and updates the view.
     *
     * @param value Accepts values between 0 and 1.
     */
    public void setFractionOxygen(double value) {
        model.setFractionOxygen(value);
    }

    /**
     * Sets the depth and updates the view.
     *
     * @param value
     */
    public void setDepth(double value) {
        model.setDepth(value);
    }

    /**
     * Sets the table type to display and updates the view.
     *
     * @param type Valid types are "EAD", and "PP"
     */
    public void setTableType(String type) {
        model.setTableType(type);
    }
    public String getCalcType() {
       return model.getCalculationType();
    }
}
