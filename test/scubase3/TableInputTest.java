package scubase3;

import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import org.junit.Assert;
import org.junit.Test;
import scubase3.panels.ScubaTableInputPanel;

/**
 * Tests components of the tableInputPanel. Uses reflection to expose private
 * variables, MVC makes it really important to ensure access only where needed
 * so using reflection is perferable to making everything public.
 *
 * @author nathan
 */
public class TableInputTest {

    ScubaController controller;
    ScubaTableInputPanel ti;

    //Testable elements
    JSpinner depthSpinner;
    JSpinner depthSpinner1;
    JSpinner oxygenFractionSpinner;
    JSpinner oxygenFractionSpinner1;

    JToggleButton eadTableSelect;
    JToggleButton ppTableSelect;

    public TableInputTest() throws IllegalArgumentException, IllegalAccessException {
        controller = new ScubaController();
        ti = new ScubaTableInputPanel(controller);

        // Put the panel in a frame so isVisible(), isShowing() methods work
        JFrame frame = new JFrame();
        frame.add(ti);
        frame.setVisible(true);
        frame.setSize(300, 300);

        //Testable elements
        depthSpinner = null;
        depthSpinner1 = null;
        oxygenFractionSpinner = null;
        oxygenFractionSpinner1 = null;

        eadTableSelect = null;
        ppTableSelect = null;

        // Using reflection to get private elements here
        Field[] fields = getFields(ti);
        for (Field field : fields) {
            String name = field.getName();
            if (null != name) {
                switch (name) {
                    case "depthSpinner":
                        depthSpinner = (JSpinner) field.get(ti);
                        break;
                    case "depthSpinner1":
                        depthSpinner1 = (JSpinner) field.get(ti);
                        break;
                    case "oxygenFractionSpinner":
                        oxygenFractionSpinner = (JSpinner) field.get(ti);
                        break;
                    case "oxygenFractionSpinner1":
                        oxygenFractionSpinner1 = (JSpinner) field.get(ti);
                        break;
                    case "eadTableSelect":
                        eadTableSelect = (JToggleButton) field.get(ti);
                        break;
                    case "ppTableSelect":
                        ppTableSelect = (JToggleButton) field.get(ti);
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
    public void tableInputPanelSpinnerOutput() {

        //Check all valid values for minOxy
        for (int i = Const.T_OXY_MIN; i <= Const.T_OXY_MAX; i += Const.T_OXY_INC) {
            controller.setTableParams(i, Const.T_OXY_MAX, Const.T_DEPTH_MIN, Const.T_DEPTH_MAX);
            ti.update();

            Assert.assertEquals(i, (int) oxygenFractionSpinner.getValue());
        }

        //Check all valid values for maxOxy
        for (int i = Const.T_OXY_MIN; i <= Const.T_OXY_MAX; i += Const.T_OXY_INC) {
            controller.setTableParams(Const.T_OXY_MIN, i, Const.T_DEPTH_MIN, Const.T_DEPTH_MAX);
            ti.update();
            
            Assert.assertEquals(i, (int) oxygenFractionSpinner1.getValue());
        }

        //Check all valid values for minDepth
        for (int i = Const.T_DEPTH_MIN; i <= Const.T_DEPTH_MAX; i += Const.T_DEPTH_INC) {
            controller.setTableParams(Const.T_OXY_MIN, Const.T_OXY_MAX, i, Const.T_DEPTH_MAX);
            ti.update();

            Assert.assertEquals(i, (int) depthSpinner.getValue());
        }

        //Check all valid values for maxDepth
        for (int i = Const.T_DEPTH_MIN; i <= Const.T_DEPTH_MAX; i += Const.T_DEPTH_INC) {
            controller.setTableParams(Const.T_OXY_MIN, Const.T_OXY_MAX, Const.T_DEPTH_MIN, i);
            ti.update();

            Assert.assertEquals(i, (int) depthSpinner1.getValue());
        }
    }
    
    @Test
    /**
     * Test the the panel sends input data to the model correctly.
     */
    public void tableInputPanelSpinnerGoodInput() {
        //Set known values at the controller
        controller.setTableParams(Const.T_OXY_MIN, Const.T_OXY_MAX, Const.T_DEPTH_MIN, Const.T_DEPTH_MAX);

        //Set new values at the spinners
        oxygenFractionSpinner.setValue(Const.T_OXY_MIN + Const.T_OXY_INC);
        oxygenFractionSpinner1.setValue(Const.T_OXY_MAX - Const.T_OXY_INC);
        
        depthSpinner.setValue(Const.T_DEPTH_MIN + Const.T_DEPTH_INC);
        depthSpinner1.setValue(Const.T_DEPTH_MAX - Const.T_DEPTH_INC);

        ti.update();

        //Check the spinners get the new values
        Assert.assertEquals(Const.T_OXY_MIN + Const.T_OXY_INC, (int) oxygenFractionSpinner.getValue());
        Assert.assertEquals(Const.T_OXY_MAX - Const.T_OXY_INC, (int) oxygenFractionSpinner1.getValue());
        
        Assert.assertEquals(Const.T_DEPTH_MIN + Const.T_DEPTH_INC, (int) depthSpinner.getValue());
        Assert.assertEquals(Const.T_DEPTH_MAX - Const.T_DEPTH_INC, (int) depthSpinner1.getValue());
     

        //Check the model get the new values
        Assert.assertEquals(Const.T_OXY_MIN + Const.T_OXY_INC, controller.getTableParams()[0]);
        Assert.assertEquals(Const.T_OXY_MAX - Const.T_OXY_INC, controller.getTableParams()[1]);
        Assert.assertEquals(Const.T_DEPTH_MIN + Const.T_DEPTH_INC, controller.getTableParams()[2]);
        Assert.assertEquals(Const.T_DEPTH_MAX - Const.T_DEPTH_INC, controller.getTableParams()[3]);
    }
    
    @Test
    /**
     * Tests that the model rejects bad inputs
     */
    public void tableInputPanelSpinnerBadInput() {
        //Set known values at the controller
        controller.setTableParams(Const.T_OXY_MIN, Const.T_OXY_MAX, Const.T_DEPTH_MIN, Const.T_DEPTH_MAX);

        //Set bad values at the spinners
        oxygenFractionSpinner.setValue(Const.T_OXY_MIN - Const.T_OXY_INC);
        oxygenFractionSpinner1.setValue(Const.T_OXY_MAX + Const.T_OXY_INC);
        
        depthSpinner.setValue(Const.T_DEPTH_MIN - Const.T_DEPTH_INC);
        depthSpinner1.setValue(Const.T_DEPTH_MAX + Const.T_DEPTH_INC);

        ti.update();

        //Check the spinners keep the last known good values
        Assert.assertEquals(Const.T_OXY_MIN, (int) oxygenFractionSpinner.getValue());
        Assert.assertEquals(Const.T_OXY_MAX, (int) oxygenFractionSpinner1.getValue());
        
        Assert.assertEquals(Const.T_DEPTH_MIN, (int) depthSpinner.getValue());
        Assert.assertEquals(Const.T_DEPTH_MAX, (int) depthSpinner1.getValue());
     

        //Check the model keeps the last known good values
        Assert.assertEquals(Const.T_OXY_MIN, controller.getTableParams()[0]);
        Assert.assertEquals(Const.T_OXY_MAX, controller.getTableParams()[1]);
        Assert.assertEquals(Const.T_DEPTH_MIN, controller.getTableParams()[2]);
        Assert.assertEquals(Const.T_DEPTH_MAX, controller.getTableParams()[3]);
    }
    
    @Test
    /**
     * Tests that the model rejects min params that are less than the max params.
     */
    public void tableInputPanelSpinnerMixedMinInput() {
        //Set known values at the controller
        controller.setTableParams(Const.T_OXY_MIN, Const.T_OXY_MAX, Const.T_DEPTH_MIN, Const.T_DEPTH_MAX);

        //Set bad values at the spinners
        oxygenFractionSpinner1.setValue(Const.T_OXY_MIN);
        oxygenFractionSpinner.setValue(Const.T_OXY_MIN + Const.T_OXY_INC); //Should be rejected
        
        depthSpinner1.setValue(Const.T_DEPTH_MIN);
        depthSpinner.setValue(Const.T_DEPTH_MIN + Const.T_DEPTH_INC); //Should be rejected
        ti.update();

        //Check the spinners keep the last known good values
        Assert.assertEquals(Const.T_OXY_MIN, (int) oxygenFractionSpinner.getValue());
        Assert.assertEquals(Const.T_OXY_MIN, controller.getTableParams()[0]);
        
        Assert.assertEquals(Const.T_DEPTH_MIN, (int) depthSpinner.getValue());
        Assert.assertEquals(Const.T_DEPTH_MIN, controller.getTableParams()[2]);
    }
    
    @Test
    /**
     * Tests that the model rejects max params that are less than the min params.
     */
    public void tableInputPanelSpinnerMixedMaxInput() {
        //Set known values at the controller
        controller.setTableParams(Const.T_OXY_MIN, Const.T_OXY_MAX, Const.T_DEPTH_MIN, Const.T_DEPTH_MAX);

        //Set bad values at the spinners
        oxygenFractionSpinner.setValue(Const.T_OXY_MAX);
        oxygenFractionSpinner1.setValue(Const.T_OXY_MAX - Const.T_OXY_INC); //Should be rejected
        
        depthSpinner.setValue(Const.T_DEPTH_MAX);
        depthSpinner1.setValue(Const.T_DEPTH_MAX - Const.T_DEPTH_INC); //Should be rejected
        ti.update();

        //Check the spinners keep the last known good values
        Assert.assertEquals(Const.T_OXY_MAX, (int) oxygenFractionSpinner1.getValue());
        Assert.assertEquals(Const.T_OXY_MAX, controller.getTableParams()[1]);
        
        Assert.assertEquals(Const.T_DEPTH_MAX, (int) depthSpinner1.getValue());
        Assert.assertEquals(Const.T_DEPTH_MAX, controller.getTableParams()[3]);
    }
    
    @Test
    /**
     * Check the table type buttons select correctly and send tableType to
     * model.
     */
    public void tableInputPanelTableType() {

        eadTableSelect.doClick();
        ti.update();
        Assert.assertEquals("EAD", controller.getTableType());
        Assert.assertTrue(eadTableSelect.isSelected());
        Assert.assertFalse(ppTableSelect.isSelected());

        ppTableSelect.doClick();
        ti.update();
        Assert.assertEquals("PP", controller.getTableType());
        Assert.assertTrue(ppTableSelect.isSelected());
        Assert.assertFalse(eadTableSelect.isSelected());
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
