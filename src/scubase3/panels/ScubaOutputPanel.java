package scubase3.panels;

import java.text.DecimalFormat;
import javax.swing.SwingUtilities;
import scubase3.Const;
import scubase3.ScubaController;
import scubase3.ScubaFrame;
import scubase3.ScubaModel;

/**
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaOutputPanel extends javax.swing.JPanel {

    private ScubaController controller;

    /**
     * Creates new form ScubaOutputPanel
     */
    public ScubaOutputPanel() {
        initComponents();
    }

    public void update() {
        //hacky workaround for netbeans gui editor
        if (controller == null) {
            ScubaFrame topFrame = (ScubaFrame) SwingUtilities.getAncestorOfClass(ScubaFrame.class, this);
            controller = topFrame.getController();
        }

        ScubaModel model = controller.getModel();
DecimalFormat df = new DecimalFormat("0.00");
        outputText.setText(model.getCalculationType()+ df.format(model.getOutputValue())
                + "  Oxygen: "+df.format(model.getOutputOxygen()));
//                + ": Depth(m): " + model.getDepthPressure()
//                + " Pg(ata): " + model.getPartialPressure()
//                + " Fg: " + model.getFractionOxygen());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        outputLHS = new javax.swing.JPanel();
        outputOxygenDisplay = new javax.swing.JSlider();
        outputOxygenLabel = new javax.swing.JLabel();
        outputRHS = new javax.swing.JPanel();
        outputText = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(300, 300));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setLayout(new java.awt.GridBagLayout());

        outputLHS.setPreferredSize(new java.awt.Dimension(150, 300));
        outputLHS.setLayout(new java.awt.GridBagLayout());

        outputOxygenDisplay.setOrientation(javax.swing.JSlider.VERTICAL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        outputLHS.add(outputOxygenDisplay, gridBagConstraints);

        outputOxygenLabel.setText("O2%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        outputLHS.add(outputOxygenLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        add(outputLHS, gridBagConstraints);

        outputRHS.setPreferredSize(new java.awt.Dimension(150, 300));
        outputRHS.setLayout(new java.awt.GridBagLayout());

        outputText.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        outputText.setText("[This is the output area]");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        outputRHS.add(outputText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(outputRHS, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel outputLHS;
    private javax.swing.JSlider outputOxygenDisplay;
    private javax.swing.JLabel outputOxygenLabel;
    private javax.swing.JPanel outputRHS;
    private javax.swing.JLabel outputText;
    // End of variables declaration//GEN-END:variables
}
