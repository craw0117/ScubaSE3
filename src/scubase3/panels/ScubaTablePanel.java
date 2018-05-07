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

    /**
     * Default constructor for ScubaTablePanel
     */
    public ScubaTablePanel() {
        initComponents();
    }

    /**
     * Updates dynamic components - must be called after state change.
     */
    public void update() {
        String tableType = ScubaSE3.getController().getTableType();
        switch (tableType) {
            case Const.TYPE_EAD:
                JTable tableEAD = ScubaSE3.getController().getEADTable();

                eadTable.removeAll();
                eadTable.add(tableEAD.getTableHeader(), BorderLayout.NORTH);
                eadTable.add(tableEAD);

                eadTable.setVisible(true);
                ppTable.setVisible(false);

                eadTableSelect.setSelected(true);
                ppTableSelect.setSelected(false);
                break;
            case Const.TYPE_PP:
                JTable tablePP = ScubaSE3.getController().getPPTable();

                ppTable.removeAll();
                ppTable.add(tablePP.getTableHeader(), BorderLayout.NORTH);
                ppTable.add(tablePP);

                eadTable.setVisible(false);
                ppTable.setVisible(true);

                ppTableSelect.setSelected(true);
                eadTableSelect.setSelected(false);
                break;
            default:
                throw new java.lang.Error("Invalid tableType: " + tableType);
        }

        //Fixes bug with tables not being styled
        SwingUtilities.updateComponentTreeUI(this);
        updateComponentTreeUI(this);
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
        inputToolBar = new javax.swing.JToolBar();
        eadTableSelect = new javax.swing.JToggleButton();
        ppTableSelect = new javax.swing.JToggleButton();
        tableScrollPane = new javax.swing.JScrollPane();
        tableDisplay = new javax.swing.JPanel();
        eadTable = new javax.swing.JPanel();
        ppTable = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        inputToolBar.setBorder(null);
        inputToolBar.setFloatable(false);
        inputToolBar.setRollover(true);

        tableButtonGroup.add(eadTableSelect);
        eadTableSelect.setSelected(true);
        eadTableSelect.setText("EAD");
        eadTableSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eadTableSelect.setFocusable(false);
        eadTableSelect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eadTableSelect.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eadTableSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eadTableSelectActionPerformed(evt);
            }
        });
        inputToolBar.add(eadTableSelect);

        tableButtonGroup.add(ppTableSelect);
        ppTableSelect.setText("PP");
        ppTableSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ppTableSelect.setFocusable(false);
        ppTableSelect.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ppTableSelect.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ppTableSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppTableSelectActionPerformed(evt);
            }
        });
        inputToolBar.add(ppTableSelect);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        add(inputToolBar, gridBagConstraints);

        tableScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        tableScrollPane.setPreferredSize(new java.awt.Dimension(250, 250));

        tableDisplay.setBackground(new java.awt.Color(0, 0, 0));
        tableDisplay.setMinimumSize(new java.awt.Dimension(0, 0));
        tableDisplay.setPreferredSize(new java.awt.Dimension(1000, 500));
        tableDisplay.setRequestFocusEnabled(false);
        tableDisplay.setLayout(new java.awt.GridBagLayout());

        eadTable.setMinimumSize(new java.awt.Dimension(1000, 500));
        eadTable.setPreferredSize(new java.awt.Dimension(1000, 0));
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

        ppTable.setMinimumSize(new java.awt.Dimension(500, 500));
        ppTable.setPreferredSize(new java.awt.Dimension(1000, 1000));
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

    /**
     * Called when the EAD button is selected from the button group
     *
     * @param evt
     */
    private void eadTableSelectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_eadTableSelectActionPerformed
    {//GEN-HEADEREND:event_eadTableSelectActionPerformed
        ScubaSE3.getController().setTableType(Const.TYPE_EAD);
    }//GEN-LAST:event_eadTableSelectActionPerformed

    /**
     * Called when the PP button is selected from the button group
     *
     * @param evt
     */
    private void ppTableSelectActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ppTableSelectActionPerformed
    {//GEN-HEADEREND:event_ppTableSelectActionPerformed
        ScubaSE3.getController().setTableType(Const.TYPE_PP);
    }//GEN-LAST:event_ppTableSelectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel eadTable;
    private javax.swing.JToggleButton eadTableSelect;
    private javax.swing.JToolBar inputToolBar;
    private javax.swing.JPanel ppTable;
    private javax.swing.JToggleButton ppTableSelect;
    private javax.swing.ButtonGroup tableButtonGroup;
    private javax.swing.JPanel tableDisplay;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
