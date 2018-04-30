package scubase3;

/**
 * Contains constant variables used throughout the application
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class Const {

    // bitmap flags
    public static final int FLAG_O2_PRESSURE = 1;
    public static final int FLAG_O2_FRACTION = 2;
    public static final int FLAG_DEPTH = 4;

    // Calculation/Table types
    public static final String TYPE_EAD = "EAD";
    public static final String TYPE_MOD = "MOD";
    public static final String TYPE_BM = "BM";
    public static final String TYPE_PP = "PP";
    public static final String TYPE_SMOD = "SMOD";

    // Output units
    public static final String UNIT_METERS = "m";
    public static final String UNIT_ATA = "ata";
    public static final String UNIT_PERCENT = "%";

    /**
     * The partial pressure constant for the Standard Maximum Operating Depth
     * calculation.
     */
    public static final double SMOD_PP_VALUE = 1.4;

    /**
     * The value returned by calculations to signify that the output is unsafe
     * and will cause harm
     */
    public static final String UNSAFE_OUTPUT_VALUE = "-";

    /**
     * The text to display in the output area when the output values are not
     * within acceptable ranges and will cause harm.
     */
    public static final String UNSAFE_OUTPUT_MESSAGE = "Input combination will cause harm!";

}
