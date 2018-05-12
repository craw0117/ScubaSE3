package scubase3.panels;

import javax.swing.JSpinner.DefaultEditor;
import scubase3.Const;
import scubase3.ScubaSE3;

/**
 * ScubaInputPanel is the upper-left panel in the table interface
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public final class ScubaTableInputPanel extends javax.swing.JPanel {

    /**
     * Default constructor for ScubaInputPanel
     */
    public ScubaTableInputPanel() {
        initComponents();
    }

    /**
     * Updates dynamic components - must be called after state change.
     */
    public void update() {
        Double[] params = ScubaSE3.getController().getTableParams();
        oxygenFractionSpinner.setValue(params[0]);
        oxygenFractionSpinner1.setValue(params[1]);
        depthSpinner.setValue(params[2]);
        depthSpinner1.setValue(params[3]);
        
        String tableType = ScubaSE3.getController().getTableType();
        switch (tableType) {
            case Const.TYPE_EAD:
                eadTableSelect.setSelected(true);
                ppTableSelect.setSelected(false);
                break;
            case Const.TYPE_PP:
                ppTableSelect.setSelected(true);
                eadTableSelect.setSelected(false);
                break;
            default:
                throw new java.lang.Error("Invalid tableType: " + tableType);
        }
    }
    /**
     * Sets all table params
     */
    private void updateParams()
    {
        ScubaSE3.getController().setTableParams(
                (double)oxygenFractionSpinner.getValue(),
                (double)oxygenFractionSpinner1.getValue(),
                (double)depthSpinner.getValue(),
                (double)depthSpinner1.getValue()
            );
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

        inputSelectGroup = new javax.swing.ButtonGroup();
        jMenu1 = new javax.swing.JMenu();
        inputToolBar1 = new javax.swing.JToolBar();
        eadTableSelect = new javax.swing.JToggleButton();
        ppTableSelect = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        oxygenFractionPanel = new javax.swing.JPanel();
        oxygenFractionLabel = new javax.swing.JLabel();
        oxygenFractionSpinner = new javax.swing.JSpinner();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 15));
        oxygenFractionLabel1 = new javax.swing.JLabel();
        oxygenFractionSpinner1 = new javax.swing.JSpinner();
        depthPanel = new javax.swing.JPanel();
        depthLabel = new javax.swing.JLabel();
        depthSpinner = new javax.swing.JSpinner();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 15));
        depthLabel1 = new javax.swing.JLabel();
        depthSpinner1 = new javax.swing.JSpinner();

        jMenu1.setText("jMenu1");

        setMaximumSize(new java.awt.Dimension(2147483647, 158));
        setMinimumSize(new java.awt.Dimension(573, 158));
        setLayout(new java.awt.GridBagLayout());

        inputToolBar1.setBorder(null);
        inputToolBar1.setFloatable(false);
        inputToolBar1.setRollover(true);
        inputToolBar1.setAlignmentY(0.5F);

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
        inputToolBar1.add(eadTableSelect);

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
        inputToolBar1.add(ppTableSelect);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(inputToolBar1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        oxygenFractionPanel.setAlignmentX(0.0F);
        oxygenFractionPanel.setAlignmentY(0.0F);
        oxygenFractionPanel.setName(""); // NOI18N
        oxygenFractionPanel.setLayout(new javax.swing.BoxLayout(oxygenFractionPanel, javax.swing.BoxLayout.Y_AXIS));

        oxygenFractionLabel.setText("Min Oxygen Percent (Fg)");
        oxygenFractionPanel.add(oxygenFractionLabel);

        oxygenFractionSpinner.setModel(new javax.swing.SpinnerNumberModel(18.0, 18.0, 50.0, 1.0));
        oxygenFractionSpinner.setAlignmentX(0.0F);
        oxygenFractionSpinner.setMaximumSize(new java.awt.Dimension(32767, 30));
        oxygenFractionSpinner.setMinimumSize(new java.awt.Dimension(100, 25));
        oxygenFractionSpinner.setRequestFocusEnabled(false);
        oxygenFractionSpinner.setValue(18.0);
        oxygenFractionSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                oxygenFractionSpinnerStateChanged(evt);
            }
        });
        ((DefaultEditor) oxygenFractionSpinner.getEditor()).getTextField().setEditable(false);
        oxygenFractionPanel.add(oxygenFractionSpinner);
        javax.swing.JSpinner.NumberEditor oEditor = (javax.swing.JSpinner.NumberEditor)oxygenFractionSpinner.getEditor();
        java.text.DecimalFormat oFormat = oEditor.getFormat();

        //Internally oxygenFraction is a double (0.32)
        //but we want to display it as an int (32)
        oFormat.setMinimumFractionDigits(0);
        oFormat.setMaximumFractionDigits(0);
        oxygenFractionPanel.add(filler4);

        oxygenFractionLabel1.setText("Max Oxygen Percent (Fg)");
        oxygenFractionPanel.add(oxygenFractionLabel1);

        oxygenFractionSpinner1.setModel(new javax.swing.SpinnerNumberModel(50.0, 18.0, 50.0, 1.0));
        oxygenFractionSpinner1.setAlignmentX(0.0F);
        oxygenFractionSpinner1.setMaximumSize(new java.awt.Dimension(32767, 30));
        oxygenFractionSpinner1.setMinimumSize(new java.awt.Dimension(100, 25));
        oxygenFractionSpinner1.setOpaque(false);
        oxygenFractionSpinner1.setRequestFocusEnabled(false);
        oxygenFractionSpinner1.setValue(50.0);
        oxygenFractionSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                oxygenFractionSpinner1StateChanged(evt);
            }
        });
        ((DefaultEditor) oxygenFractionSpinner1.getEditor()).getTextField().setEditable(false);
        oxygenFractionPanel.add(oxygenFractionSpinner1);
        javax.swing.JSpinner.NumberEditor oEditor2 = (javax.swing.JSpinner.NumberEditor)oxygenFractionSpinner1.getEditor();
        java.text.DecimalFormat oFormat2 = oEditor2.getFormat();

        //Internally oxygenFraction is a double (0.32)
        //but we want to display it as an int (32)
        oFormat2.setMinimumFractionDigits(0);
        oFormat2.setMaximumFractionDigits(0);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 5);
        jPanel1.add(oxygenFractionPanel, gridBagConstraints);

        depthPanel.setAlignmentX(0.0F);
        depthPanel.setAlignmentY(0.0F);
        depthPanel.setMinimumSize(new java.awt.Dimension(155, 90));
        depthPanel.setOpaque(false);
        depthPanel.setPreferredSize(new java.awt.Dimension(155, 90));
        depthPanel.setRequestFocusEnabled(false);
        depthPanel.setLayout(new javax.swing.BoxLayout(depthPanel, javax.swing.BoxLayout.Y_AXIS));

        depthLabel.setText("Min Depth (m)");
        depthPanel.add(depthLabel);

        depthSpinner.setModel(new javax.swing.SpinnerNumberModel(3.0d, 3.0d, 69.0d, 3.0d));
        depthSpinner.setAlignmentX(0.0F);
        depthSpinner.setMaximumSize(new java.awt.Dimension(32767, 35));
        depthSpinner.setMinimumSize(new java.awt.Dimension(100, 25));
        depthSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                depthSpinnerStateChanged(evt);
            }
        });
        ((DefaultEditor) depthSpinner.getEditor()).getTextField().setEditable(false);
        depthPanel.add(depthSpinner);
        depthPanel.add(filler3);

        depthLabel1.setText("Max Depth (m)");
        depthPanel.add(depthLabel1);

        depthSpinner1.setModel(new javax.swing.SpinnerNumberModel(69.0d, 3.0d, 69.0d, 3.0d));
        depthSpinner1.setAlignmentX(0.0F);
        depthSpinner1.setMaximumSize(new java.awt.Dimension(32767, 35));
        depthSpinner1.setMinimumSize(new java.awt.Dimension(100, 25));
        depthSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                depthSpinner1StateChanged(evt);
            }
        });
        ((DefaultEditor) depthSpinner.getEditor()).getTextField().setEditable(false);
        depthPanel.add(depthSpinner1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 10);
        jPanel1.add(depthPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called whenever the oxygen fraction input field value is changed
     *
     * @param evt
     */
    private void oxygenFractionSpinnerStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_oxygenFractionSpinnerStateChanged
    {//GEN-HEADEREND:event_oxygenFractionSpinnerStateChanged
      if ((double)oxygenFractionSpinner.getValue() <= (double)oxygenFractionSpinner1.getValue())
            updateParams();
        else
            update(); //erase the change
    }//GEN-LAST:event_oxygenFractionSpinnerStateChanged

    /**
     * Called whenever the depth input field value is changed
     *
     * @param evt
     */
    private void depthSpinnerStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_depthSpinnerStateChanged
    {//GEN-HEADEREND:event_depthSpinnerStateChanged
        if ((double)depthSpinner.getValue() <= (double)depthSpinner1.getValue())
            updateParams();
        else
            update(); //erase the change
    }//GEN-LAST:event_depthSpinnerStateChanged

    private void eadTableSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eadTableSelectActionPerformed
        ScubaSE3.getController().setTableType(Const.TYPE_EAD);
    }//GEN-LAST:event_eadTableSelectActionPerformed

    private void ppTableSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppTableSelectActionPerformed
        ScubaSE3.getController().setTableType(Const.TYPE_PP);
    }//GEN-LAST:event_ppTableSelectActionPerformed

    private void oxygenFractionSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_oxygenFractionSpinner1StateChanged
       if ((double)oxygenFractionSpinner.getValue() <= (double)oxygenFractionSpinner1.getValue())
            updateParams();
        else
            update(); //erase the change
    }//GEN-LAST:event_oxygenFractionSpinner1StateChanged

    private void depthSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_depthSpinner1StateChanged
       if ((double)depthSpinner.getValue() <= (double)depthSpinner1.getValue())
            updateParams();
        else
            update(); //erase the change
    }//GEN-LAST:event_depthSpinner1StateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel depthLabel;
    private javax.swing.JLabel depthLabel1;
    private javax.swing.JPanel depthPanel;
    private javax.swing.JSpinner depthSpinner;
    private javax.swing.JSpinner depthSpinner1;
    private javax.swing.JToggleButton eadTableSelect;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.ButtonGroup inputSelectGroup;
    private javax.swing.JToolBar inputToolBar1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel oxygenFractionLabel;
    private javax.swing.JLabel oxygenFractionLabel1;
    private javax.swing.JPanel oxygenFractionPanel;
    private javax.swing.JSpinner oxygenFractionSpinner;
    private javax.swing.JSpinner oxygenFractionSpinner1;
    private javax.swing.JToggleButton ppTableSelect;
    // End of variables declaration//GEN-END:variables
}
