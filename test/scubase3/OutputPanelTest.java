package scubase3;

import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import org.junit.Assert;
import org.junit.Test;
import scubase3.panels.ScubaOutputPanel;

/**
 * Tests components of the inputPanel. Uses reflection to expose private
 * variables, MVC makes it really important to ensure access only where needed
 * so using reflection is perferable to making everything public.
 *
 * @author nathan
 */
public class OutputPanelTest {

    ScubaController controller;
    ScubaOutputPanel op;

    //Testable elements
    JLabel nitrogenOutput;
    JLabel oxygenOutput;
    JLabel outputText;
    JLabel calcType;

    JSlider outputOxygenDisplay;

    public OutputPanelTest() throws IllegalArgumentException, IllegalAccessException {
        controller = new ScubaController();
        op = new ScubaOutputPanel(controller);

        // Put the panel in a frame so isVisible(), isShowing() methods work
        JFrame frame = new JFrame();
        frame.add(op);
        frame.setVisible(true);
        frame.setSize(500, 400);

        //Testable elements
        nitrogenOutput = null;
        oxygenOutput = null;
        outputText = null;
        calcType = null;

        outputOxygenDisplay = null;

        // Using reflection to get private elements here
        Field[] fields = getFields(op);
        for (Field field : fields) {
            String name = field.getName();
            if (null != name) {
                switch (name) {
                    case "nitrogenOutput":
                        nitrogenOutput = (JLabel) field.get(op);
                        break;
                    case "oxygenOutput":
                        oxygenOutput = (JLabel) field.get(op);
                        break;
                    case "outputText":
                        outputText = (JLabel) field.get(op);
                        break;
                    case "calcType":
                        calcType = (JLabel) field.get(op);
                        break;
                    case "outputOxygenDisplay":
                        outputOxygenDisplay = (JSlider) field.get(op);
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
     * the model and check it appears correctly on the labels.
     */
    public void outputPanelOutput() {

        //Comprehensive testing of all possible input combinations
        
        //check all calc types
        for (String calc_type : Const.CALC_TYPE_DICT.keySet()) {
            controller.setCalculationType(calc_type);
            //check all values of oxygenFraction
            for (double o = Const.OXYGEN_MINIMUM; o < Const.OXYGEN_MAXIMUM; o += Const.OXYGEN_INC) {
                controller.setOxygenFraction(o);
                for (double d = Const.DEPTH_MINIMUM; d < Const.DEPTH_MAXIMUM; d += Const.DEPTH_INC) {
                    controller.setDepth(d);
                    for (double p = Const.PP_MINIMUM; p < Const.PP_MAXIMUM; p += Const.PP_INC) {
                        controller.setPartialPressure(p);
                        op.update();

                        if (controller.getOutputValue().equals(Const.UNSAFE_OUTPUT_VALUE)) {
                            Assert.assertEquals(Const.UNSAFE_OUTPUT_MESSAGE, outputText.getText());
                            
                            if (controller.getCalculationType().equals(Const.TYPE_BM)) {
                                Assert.assertFalse(outputOxygenDisplay.isShowing());
                                Assert.assertFalse(oxygenOutput.isShowing());
                                Assert.assertFalse(nitrogenOutput.isShowing());
                            } else {
                                Assert.assertEquals(controller.getOutputOxygen(), outputOxygenDisplay.getValue());
                                Assert.assertTrue(outputOxygenDisplay.isShowing());
                                Assert.assertTrue(oxygenOutput.isShowing());
                                Assert.assertTrue(nitrogenOutput.isShowing());
                            }
                        } else {
                            Assert.assertTrue(outputText.getText().contains(controller.getOutputValue()));

                            Assert.assertTrue(outputOxygenDisplay.isShowing());
                            Assert.assertTrue(oxygenOutput.isShowing());
                            Assert.assertTrue(nitrogenOutput.isShowing());
                            
                            Assert.assertEquals(controller.getOutputOxygen(), outputOxygenDisplay.getValue());
                        }
                        Assert.assertEquals(Const.CALC_TYPE_DICT.get(controller.getCalculationType()), calcType.getText());
                    }
                }
            }
        }
    }
    
    @Test
    /**
     * Test the slider sends information to the model correctly.
     */
    public void outputPanelInput(){
        controller.setCalculationType(Const.TYPE_EAD);
        for (double o = Const.OXYGEN_MINIMUM; o < Const.OXYGEN_MAXIMUM; o += Const.OXYGEN_INC) {
            int oxy = (int)Math.round(o*100);
            
            outputOxygenDisplay.setValue(oxy);
            op.update();
                        
            Assert.assertEquals(oxy,  outputOxygenDisplay.getValue());
            Assert.assertEquals((double)(oxy/100.0),  controller.getOxygenFraction(), 0.001);
        }
    }
    
     @Test
    /**
     * Test the slider rejects bad inputs.
     */
    public void outputPanelBadInput(){
        controller.setCalculationType(Const.TYPE_EAD);

        //Set to a good value
        outputOxygenDisplay.setValue((int)Math.round(Const.OXYGEN_MINIMUM*100));
        op.update();
        
        //Try and set a bad value
        outputOxygenDisplay.setValue((int)Math.round((Const.OXYGEN_MINIMUM-Const.OXYGEN_INC)*100));
        op.update();

        //should remain good value
        Assert.assertEquals(Const.OXYGEN_MINIMUM,  outputOxygenDisplay.getValue()/100.0, 0.001);
        Assert.assertEquals(Const.OXYGEN_MINIMUM,  controller.getOxygenFraction(), 0.001);
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
