/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scubase3;

/**
 *
 * @author nathan
 */
public class ScubaController {

    private final ScubaModel model;
    private final ScubaFrame view;
    

    public ScubaController() {
        this.view = new ScubaFrame(this);
        this.model = new ScubaModel(view);

        view.update();
    }

    public ScubaModel getModel() {
        return model;
    }

    public ScubaFrame getView() {
        return view;
    }
    
    /**
     * Sets the calculation to perform and will enable the correct inputs.
     *
     * @param calcType Code for calc ["EAD", "MOD", "MIX", "PP", "SMOD"]
     */
    public void setCalcType(String calcType) {
        model.setCalculationType(calcType);
    }

    public void setPartialPressure(double value) {
        model.setPartialPressure(value);
    }

    public void setFractionOxygen(double value) {
        model.setFractionOxygen(value);
    }

    public void setDepth(double value) {
        model.setDepth(value);
    }
    
    public void setTableType(String type) {
        model.setTableType(type);
    }
    public String getCalcType() {
       return model.getCalculationType();
    }
}
