package scubase3.panels;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import scubase3.Const;
import scubase3.ScubaController;
import scubase3.ScubaSE3;

/**
 * ScubaTablePanel is the lower panel in the user interface
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaTablePanel extends javax.swing.JPanel {

    private ScubaController controller;
    private JTable activeTable;
    
    /**
     * Default constructor for ScubaTablePanel
     */
    public ScubaTablePanel() {
        initComponents();
    }
    public ScubaTablePanel(ScubaController controller) {
        initComponents();
        this.controller = controller;
    }

    /**
     * Updates dynamic components - must be called after state change.
     */
    public void update() {
        if (controller == null)
            controller = ScubaSE3.getController();
                
        String tableType = controller.getTableType();
        switch (tableType) {
            case Const.TYPE_EAD:
                JTable tableEAD = controller.getEADTable();

                eadTable.removeAll();
                eadTable.add(tableEAD.getTableHeader(), BorderLayout.NORTH);
                eadTable.add(tableEAD);
                activeTable = tableEAD;

                eadTable.setVisible(true);
                ppTable.setVisible(false);

                break;
            case Const.TYPE_PP:
                JTable tablePP = controller.getPPTable();

                ppTable.removeAll();
                ppTable.add(tablePP.getTableHeader(), BorderLayout.NORTH);
                ppTable.add(tablePP);
                activeTable = tablePP;

                eadTable.setVisible(false);
                ppTable.setVisible(true);

                break;
            default:
                throw new java.lang.Error("Invalid tableType: " + tableType);
        }
        
        this.validate();
    }

    public JTable getActiveTable() {
        return activeTable;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tableButtonGroup = new javax.swing.ButtonGroup();
        tableScrollPane = new javax.swing.JScrollPane();
        tableDisplay = new javax.swing.JPanel();
        eadTable = new javax.swing.JPanel();
        ppTable = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        tableScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        tableScrollPane.setPreferredSize(new java.awt.Dimension(250, 250));

        tableDisplay.setBackground(new java.awt.Color(0, 0, 0));
        tableDisplay.setRequestFocusEnabled(false);
        tableDisplay.setLayout(new java.awt.GridBagLayout());

        eadTable.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tableDisplay.add(eadTable, gridBagConstraints);

        ppTable.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        tableDisplay.add(ppTable, gridBagConstraints);

        tableScrollPane.setViewportView(tableDisplay);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(tableScrollPane, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel eadTable;
    private javax.swing.JPanel ppTable;
    private javax.swing.ButtonGroup tableButtonGroup;
    private javax.swing.JPanel tableDisplay;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
