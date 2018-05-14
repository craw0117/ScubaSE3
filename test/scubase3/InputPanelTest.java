package scubase3;

import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import org.junit.Assert;
import org.junit.Test;
import scubase3.panels.ScubaInputPanel;

/**
 * Tests components of the inputPanel. Uses reflection to expose private 
 * variables, MVC makes it really important to ensure access only where needed
 * so using reflection is preferable to making everything public. 
 *
 * @author nathan
 */
public class InputPanelTest {

    ScubaController controller;
    ScubaInputPanel ip;
    
    //Testable elements
    JSpinner depthSpinner;
    JSpinner partialPressureSpinner;
    JSpinner oxygenFractionSpinner;

    JToggleButton smodSelect;
    JToggleButton ppSelect;
    JToggleButton eadSelect;
    JToggleButton modSelect;
    JToggleButton bmSelect;
        
    public InputPanelTest() throws IllegalArgumentException, IllegalAccessException {
        controller = new ScubaController();
        ip = new ScubaInputPanel(controller);

        // Put the panel in a frame so isVisible(), isShowing() methods work
        JFrame frame = new JFrame();
        frame.add(ip);
        frame.setVisible(true);
        frame.setSize(300, 300);
        
        //Testable elements
        depthSpinner = null;
        partialPressureSpinner = null;
        oxygenFractionSpinner = null;

        smodSelect = null;
        ppSelect = null;
        eadSelect = null;
        modSelect = null;
        bmSelect = null;

        // Using reflection to get private elements here
        Field[] fields = getFields(ip);
        for (Field field : fields) {
            String name = field.getName();
            if (null != name) {
                switch (name) {
                    case "depthSpinner":
                        depthSpinner = (JSpinner) field.get(ip);
                        break;
                    case "partialPressureSpinner":
                        partialPressureSpinner = (JSpinner) field.get(ip);
                        break;
                    case "oxygenFractionSpinner":
                        oxygenFractionSpinner = (JSpinner) field.get(ip);
                        break;
                    case "smodSelect":
                        smodSelect = (JToggleButton) field.get(ip);
                        break;
                    case "ppSelect":
                        ppSelect = (JToggleButton) field.get(ip);
                        break;
                    case "eadSelect":
                        eadSelect = (JToggleButton) field.get(ip);
                        break;
                    case "modSelect":
                        modSelect = (JToggleButton) field.get(ip);
                        break;
                    case "bmSelect":
                        bmSelect = (JToggleButton) field.get(ip);
                        break;
                    default:
                        break;
                }
            }
        }
    }
   
    @Test
    /**
     * Test the the panel outputs data from the model correctly. Put data into 
     * the model and check it appears correctly on the correct spinner.
     */
    public void inputPanelSpinnerOutput() {
        
        //Check all valid values for DEPTH
        for (double d = Const.DEPTH_MINIMUM; d <= Const.DEPTH_MAXIMUM; d += 1.0) {
            controller.setDepth(d);
            ip.update();

            if (depthSpinner != null) {
                Assert.assertEquals(d, (double) depthSpinner.getValue(), 0.01);
            } else {
                System.out.println("inputPanelTest: depthSpinner not found");
                break;
            }
        }

        //Check all valid values for OXYGEN
        for (double o = Const.OXYGEN_MINIMUM; o <= Const.OXYGEN_MAXIMUM; o += 0.01) {
            controller.setOxygenFraction(o);
            ip.update();

            if (oxygenFractionSpinner != null) {
                Assert.assertEquals(o, (double) oxygenFractionSpinner.getValue(), 0.01);
            } else {
                System.out.println("inputPanelTest: oxygenFractionSpinner not found");
                break;
            }
        }

        //Check all valid values for PP
        for (double p = Const.PP_MINIMUM; p <= Const.PP_MAXIMUM; p += 0.1) {
            controller.setPartialPressure(p);
            ip.update();

            if (partialPressureSpinner != null) {
                Assert.assertEquals(p, (double) partialPressureSpinner.getValue(), 0.01);
            } else {
                System.out.println("inputPanelTest: partialPressureSpinner not found");
                break;
            }
        }
    }
    
    @Test
    /**
     * Test the spinners accept good input.
     */
    public void inputPanelSpinnerGoodInputs() {
        //Set known values at the controller
        controller.setDepth(Const.DEPTH_MINIMUM);
        controller.setOxygenFraction(Const.OXYGEN_MINIMUM);
        controller.setPartialPressure(Const.PP_MINIMUM);

        //Set new values at the spinners
        depthSpinner.setValue(Const.DEPTH_MINIMUM + Const.DEPTH_INC);
        oxygenFractionSpinner.setValue(Const.OXYGEN_MINIMUM + Const.OXYGEN_INC);
        partialPressureSpinner.setValue(Const.PP_MINIMUM + Const.PP_INC);

        ip.update();

        //Check the spinners get the new values
        Assert.assertEquals(Const.DEPTH_MINIMUM + Const.DEPTH_INC, (double) depthSpinner.getValue(), 0.01);
        Assert.assertEquals(Const.OXYGEN_MINIMUM + Const.OXYGEN_INC, (double) oxygenFractionSpinner.getValue(), 0.01);
        Assert.assertEquals(Const.PP_MINIMUM + Const.PP_INC, (double) partialPressureSpinner.getValue(), 0.01);

        //Check the model get the new values
        Assert.assertEquals(Const.DEPTH_MINIMUM + Const.DEPTH_INC, controller.getDepth(), 0.01);
        Assert.assertEquals(Const.OXYGEN_MINIMUM + Const.OXYGEN_INC, controller.getOxygenFraction(), 0.01);
        Assert.assertEquals(Const.PP_MINIMUM + Const.PP_INC, controller.getPartialPressure(), 0.01);
    }
    
    @Test
    /**
     * Test the spinners doesn't accept bad inputs.
     */
    public void inputPanelSpinnerBadInputs() {
        // Set known values (can't use minimum as safe value due to
        // floating point errors)
        depthSpinner.setValue(Const.DEPTH_MINIMUM + Const.DEPTH_INC);
        oxygenFractionSpinner.setValue(Const.OXYGEN_MINIMUM + Const.OXYGEN_INC);
        partialPressureSpinner.setValue(Const.PP_MINIMUM + Const.PP_INC);


        //Attempt to set bad values at the spinner
        depthSpinner.setValue(Const.DEPTH_MINIMUM - 2*Const.DEPTH_INC);
        oxygenFractionSpinner.setValue(Const.OXYGEN_MINIMUM - 2*Const.OXYGEN_INC);
        partialPressureSpinner.setValue(Const.PP_MINIMUM - 2*Const.PP_INC);
        ip.update();

        //Check the spinners still have the last known good value
        Assert.assertEquals(Const.DEPTH_MINIMUM + Const.DEPTH_INC, (double) depthSpinner.getValue(), 0.01);
        Assert.assertEquals(Const.OXYGEN_MINIMUM + Const.OXYGEN_INC, (double) oxygenFractionSpinner.getValue(), 0.01);
        Assert.assertEquals(Const.PP_MINIMUM + Const.PP_INC, (double) partialPressureSpinner.getValue(), 0.01);

        //Check the model still has the last known good value
        Assert.assertEquals(Const.DEPTH_MINIMUM + Const.DEPTH_INC, controller.getDepth(), 0.01);
        Assert.assertEquals(Const.OXYGEN_MINIMUM + Const.OXYGEN_INC, controller.getOxygenFraction(), 0.01);
        Assert.assertEquals(Const.PP_MINIMUM + Const.PP_INC, controller.getPartialPressure(), 0.01);
    }
    
    @Test
    /**
     * Check the calc type buttons select correctly and send calcType to model.
     */
    public void inputPanelCalcType() {

        smodSelect.doClick();
        ip.update();
        Assert.assertEquals(Const.TYPE_SMOD, controller.getCalculationType());
        Assert.assertTrue(smodSelect.isSelected());
        Assert.assertFalse(ppSelect.isSelected());
        Assert.assertFalse(eadSelect.isSelected());
        Assert.assertFalse(modSelect.isSelected());
        Assert.assertFalse(bmSelect.isSelected());
        
        Assert.assertFalse(depthSpinner.isShowing());
        Assert.assertFalse(partialPressureSpinner.isShowing());
        Assert.assertTrue(oxygenFractionSpinner.isShowing());

        ppSelect.doClick();
        ip.update();
        Assert.assertEquals(Const.TYPE_PP, controller.getCalculationType());
        Assert.assertTrue(ppSelect.isSelected());
        Assert.assertFalse(smodSelect.isSelected());
        Assert.assertFalse(eadSelect.isSelected());
        Assert.assertFalse(modSelect.isSelected());
        Assert.assertFalse(bmSelect.isSelected());
        
        Assert.assertTrue(depthSpinner.isShowing());
        Assert.assertFalse(partialPressureSpinner.isShowing());
        Assert.assertTrue(oxygenFractionSpinner.isShowing());

        eadSelect.doClick();
        ip.update();
        Assert.assertEquals(Const.TYPE_EAD, controller.getCalculationType());
        Assert.assertTrue(eadSelect.isSelected());
        Assert.assertFalse(smodSelect.isSelected());
        Assert.assertFalse(ppSelect.isSelected());
        Assert.assertFalse(modSelect.isSelected());
        Assert.assertFalse(bmSelect.isSelected());
        
        Assert.assertTrue(depthSpinner.isShowing());
        Assert.assertFalse(partialPressureSpinner.isShowing());
        Assert.assertTrue(oxygenFractionSpinner.isShowing());

        modSelect.doClick();
        ip.update();
        Assert.assertEquals(Const.TYPE_MOD, controller.getCalculationType());
        Assert.assertTrue(modSelect.isSelected());
        Assert.assertFalse(smodSelect.isSelected());
        Assert.assertFalse(ppSelect.isSelected());
        Assert.assertFalse(eadSelect.isSelected());
        Assert.assertFalse(bmSelect.isSelected());
        
        Assert.assertFalse(depthSpinner.isShowing());
        Assert.assertTrue(partialPressureSpinner.isShowing());
        Assert.assertTrue(oxygenFractionSpinner.isShowing());

        bmSelect.doClick();
        ip.update();
        Assert.assertEquals(Const.TYPE_BM, controller.getCalculationType());
        Assert.assertTrue(bmSelect.isSelected());
        Assert.assertFalse(smodSelect.isSelected());
        Assert.assertFalse(ppSelect.isSelected());
        Assert.assertFalse(eadSelect.isSelected());
        Assert.assertFalse(modSelect.isSelected());
        
        Assert.assertTrue(depthSpinner.isShowing());
        Assert.assertTrue(partialPressureSpinner.isShowing());
        Assert.assertFalse(oxygenFractionSpinner.isShowing());

    }

    /**
     * Get all fields, private or otherwise from an object. This method uses
     * reflection to change the class and should only be used for testing.
     *
     * @param obj An object
     * @return list of fields with forced accessbility
     * @throws Exception
     */
    private Field[] getFields(Object obj) {
        Class<?> object = obj.getClass();

        Field[] fields = object.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        return fields;
    }
}
