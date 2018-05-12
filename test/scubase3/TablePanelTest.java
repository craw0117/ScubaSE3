package scubase3;

import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.junit.Assert;
import org.junit.Test;
import scubase3.panels.ScubaTablePanel;

/**
 * Tests components of the inputPanel. Uses reflection to expose private 
 * variables, MVC makes it really important to ensure access only where needed
 * so using reflection is perferable to making everything public. 
 *
 * @author nathan
 */
public class TablePanelTest {

    ScubaController controller;
    ScubaTablePanel tp;
    
    //Testable elements
    JPanel eadTable;
    JPanel ppTable;

        
    public TablePanelTest() throws IllegalArgumentException, IllegalAccessException {
        controller = new ScubaController();
        tp = new ScubaTablePanel(controller);

        // Put the panel in a frame so isVisible(), isShowing() methods work
        JFrame frame = new JFrame();
        frame.add(tp);
        frame.setVisible(true);
        frame.setSize(600, 600);
        
        //Testable elements
        eadTable = null;
        ppTable = null;

        // Using reflection to get private elements here
        Field[] fields = getFields(tp);
        for (Field field : fields) {
            String name = field.getName();
            if (null != name) {
                switch (name) {
                    case "eadTable":
                        eadTable = (JPanel) field.get(tp);
                        break;
                    case "ppTable":
                        ppTable = (JPanel) field.get(tp);
                        break;
                    default:
                        break;
                }
            }
        }
    }
   
    @Test
    public void tablePanelTypes(){
        controller.setTableType("EAD");
        tp.update();
        Assert.assertTrue(eadTable.isVisible());
        Assert.assertFalse(ppTable.isVisible());
        
        controller.setTableType("PP");
        tp.update();
        Assert.assertFalse(eadTable.isVisible());
        Assert.assertTrue(ppTable.isVisible());
    }
    
    @Test
    public void tablePanelParams(){
        
        int minOxy = 18;
        int maxOxy = 50;
        int minDepth = 3;
        int maxDepth = 69;
        
        //Cover all possible sizes but not all possible combinations
        for (int i = minOxy; i <= maxOxy; i+=1){
            
            for (int p = minDepth; p <= maxDepth; p+=3){
                    controller.setTableParams(minOxy, i, minDepth, p);
                    tp.update();
                    
                    JTable table = tp.getActiveTable();
                    Assert.assertEquals(i-minOxy+1, table.getRowCount());
                    Assert.assertEquals(((p-minDepth+3)/3)+1, table.getColumnCount());

            }
        }
        
        
        
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
