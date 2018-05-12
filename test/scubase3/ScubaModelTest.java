package scubase3;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author nathan
 */
public class ScubaModelTest {
    
    @Test
    public void validCalcParameters() {
        ScubaModel model = new ScubaModel();
        
        final double DEPTH = 50.0;
        final double PP = 1.3;
        final double OXY = 0.30;

        model.setDepth(DEPTH);
        model.setPartialPressure(PP);
        model.setOxygenFraction(OXY);

        Assert.assertEquals(DEPTH, model.getDepth(), 0.01);
        Assert.assertEquals(PP, model.getPartialPressure(), 0.01);
        Assert.assertEquals(OXY, model.getOxygenFraction(), 0.01);
    }
    
    @Test
    public void invalidCalcParameters() {
        ScubaModel model = new ScubaModel();
        
        //Check invalid values don't save
        final double DEPTH = 150.0;
        final double PP = 1.0;
        final double OXY = 0.0;

        model.setDepth(DEPTH);
        model.setPartialPressure(PP);
        model.setOxygenFraction(OXY);

        Assert.assertNotEquals(DEPTH, model.getDepth(), 0.01);
        Assert.assertNotEquals(PP, model.getPartialPressure(), 0.01);
        Assert.assertNotEquals(OXY, model.getOxygenFraction(), 0.01);
    }
    
    @Test
    public void validCalcType() {
       ScubaModel model = new ScubaModel();
       String[] types = {Const.TYPE_BM, Const.TYPE_EAD, 
            Const.TYPE_MOD, Const.TYPE_MOD, 
            Const.TYPE_PP, Const.TYPE_SMOD};
       
       for (String type : types)
       {
           model.setCalculationType(type);
           Assert.assertEquals(type, model.getCalculationType());
       }
    }
    
    @Test(expected = java.lang.Error.class)
    public void invalidCalcType() {
       ScubaModel model = new ScubaModel();
       model.setCalculationType("AD)@D@(JK");
    }
    
    @Test
    public void validTableParameters() {
        ScubaModel model = new ScubaModel();
        
        model.setTableType("EAD");
        Assert.assertEquals("EAD", model.getTableType());
        Assert.assertEquals(1, model.getTabView());
        
        //reset tab
        model.setTabView(0);
        
        model.setTableType("PP");
        Assert.assertEquals("PP", model.getTableType());
        Assert.assertEquals(1, model.getTabView());
    }
}
