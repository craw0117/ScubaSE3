package scubase3;

/**
 * Calculations are separated from the ScubaModel class to simplify the process
 * of updating calculations, methods in this class are called from the
 * ScubaModel class.
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaCalculations {

    /**
     * Calculates the Maximum Operating Depth using the provided parameters.
     *
     * @param partialPressure
     * @param oxygenFraction
     * @return
     * @see #calculateSMOD(double oxygenFraction)
     */
    public static String calculateMOD(double partialPressure, double oxygenFraction) {
        return Const.DF_DEPTH.format((partialPressure / oxygenFraction - 1.0) * 10.0);
    }

    /**
     * Calculates the Best Mix using the provided parameters, for any result
     * outside of the acceptable ranges, an appropriate warning message will be
     * displayed.
     *
     * @param partialPressure
     * @param depth
     * @return
     */
    public static String calculateBM(double partialPressure, double depth) {
        double result = partialPressure / (depth / 10.0 + 1.0) * 100.0;
        if (result > 50 || result < 22) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        return Const.DF_O2.format(result);
    }

    /**
     * Calculates the Partial Pressure using the provided parameters, for any
     * result outside of the acceptable ranges, an appropriate warning message
     * will be displayed.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     * @see #calculateRawPP(double oxygenFraction, double depth)
     */
    public static String calculatePP(double oxygenFraction, double depth) {
        double result = calculateRawPP(oxygenFraction, depth);
        if (result > 1.6 || result < 1.1) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        return Const.DF_PP.format(result);
    }

    /**
     * Calculates the Partial Pressure using the provided parameters, does not
     * perform any safety checks but instead returns the "raw" value.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     * @see #calculatePP(double oxygenFraction, double depth)
     */
    public static double calculateRawPP(double oxygenFraction, double depth) {
        return oxygenFraction * (depth / 10.0 + 1.0);
    }

    /**
     * Calculates the Equivalent Air Depth using the provided parameters, for
     * any result outside of the acceptable ranges, an appropriate warning
     * message will be displayed.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     */
    public static String calculateEAD(double oxygenFraction, double depth) {
        double partialPressure = calculateRawPP(oxygenFraction, depth);
        if (partialPressure > 1.6 || partialPressure < 1.1) {
            return Const.UNSAFE_OUTPUT_VALUE;
        }
        double result = calculateRawEAD(oxygenFraction, depth);
        return Const.DF_DEPTH.format(result < 0 ? 0 : result);
    }

    /**
     * Calculates the Equivalent Air Depth using the provided parameters, does
     * not perform any safety checks but instead returns the "raw" value.
     *
     * @param oxygenFraction
     * @param depth
     * @return
     * @see #calculateEAD(double oxygenFraction, double depth)
     */
    public static double calculateRawEAD(double oxygenFraction, double depth) {
        return ((1.0 - oxygenFraction) * (depth / 10.0 + 1.0) / 0.79 - 1.0) * 10.0;
    }

    /**
     * Calculates the Standard Maximum Operating Depth by using the standard
     * partial pressure constant and the oxygen fraction provided.
     *
     * @param oxygenFraction
     * @return
     * @see #calculateMOD(double partialPressure, double oxygenFraction)
     */
    public static String calculateSMOD(double oxygenFraction) {
        return ScubaCalculations.calculateMOD(Const.SMOD_PP_VALUE, oxygenFraction);
    }
}
