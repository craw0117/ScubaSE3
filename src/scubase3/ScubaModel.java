package scubase3;

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
                this.outputValue = this.depthPressure;
this.outputValue = MethodsContainer.calculateEAD( this.fractionOxygen, this.depthPressure);
this.outputOxygen = MethodsContainer.calculateOxygenEAD( this.fractionOxygen, this.depthPressure);
                break;
            case "MOD":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_FRAC_OXYGEN;
                this.outputValue = MethodsContainer.calculateMOD(this.partialPressure, this.fractionOxygen);
                this.outputOxygen = MethodsContainer.calculateOxygenMOD(this.partialPressure, this.fractionOxygen);
                break;
            case "BM":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_DEPTH;
                this.outputValue = MethodsContainer.calculateBM(this.partialPressure, this.depthPressure);
                this.outputOxygen = MethodsContainer.calculateOxygenBM(this.partialPressure, this.depthPressure);
                break;
            case "PP":
                this.inputFlags = Const.FLAG_FRAC_OXYGEN | Const.FLAG_DEPTH;
                this.outputValue = MethodsContainer.calculatePP(this.fractionOxygen, this.depthPressure);
                this.outputOxygen = MethodsContainer.calculateOxygenPP(this.fractionOxygen, this.depthPressure);
                break;
            case "SMOD":
                this.inputFlags = Const.FLAG_O2_PRESSURE | Const.FLAG_FRAC_OXYGEN;
                this.outputValue = MethodsContainer.calculateMOD(this.partialPressure, this.fractionOxygen);
                this.outputOxygen = MethodsContainer.calculateOxygenMOD(this.partialPressure, this.fractionOxygen);
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
        return depthPressure;
    }

    public void setDepth(double depth) {
        this.depthPressure = depth;
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

    public double getOutputValue() {
        return outputValue;
    }

    public double getOutputOxygen() {
        return outputOxygen;
    }
    

}
