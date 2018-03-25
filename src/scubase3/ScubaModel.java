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
public class ScubaModel {

    private final ScubaFrame view;

    private int inputFlags;
    private String calculationType;
    private double partialPressure;
    private double fractionOxygen;
    private double depthPressure; //in ata
    
    private String tableType;

    public ScubaModel(ScubaFrame view) {
        this.view = view;

        initState();
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
                break;
            case "MOD":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_FRAC_OXYGEN;
                break;
            case "MIX":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                break;
            case "PP":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                break;
            case "SMOD":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_FRAC_OXYGEN;
                //TODO verify these flags are correct (no idea what SMOD is)
                break;
            default:
                throw new java.lang.Error("Invalid calcType: " + calcType);
        }

        this.calculationType = calcType;
        view.update();
    }

    public double getPartialPressure() {
        return partialPressure;
    }

    public void setPartialPressure(double partialPressure) {
        this.partialPressure = partialPressure;
        view.update();
    }

    public double getFractionOxygen() {
        return fractionOxygen;
    }

    public void setFractionOxygen(double fractionOxygen) {
        this.fractionOxygen = fractionOxygen;
        view.update();
    }

    public double getDepthPressure() {
        return depthPressure;
    }

    public void setDepthPressure(double pressure) {
        this.depthPressure = pressure;
        view.update();
    }

    public double getDepth() {
        // we store depth as the pressure from depth
        // thus must calculate it on demand
        return (depthPressure - 1) * 10;
    }

    public void setDepth(double depth) {
        this.depthPressure = depth / 10 + 1;
        view.update();
    }

    public int getInputFlags() {
        return inputFlags;
    }

    public void setInputFlags(int inputFlags) {
        this.inputFlags = inputFlags;
        view.update();
    }
    
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
        view.update();
    }

}
