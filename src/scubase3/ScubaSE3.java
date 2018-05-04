package scubase3;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Main class for ScubaSE3 project, holds the main execution method
 *
 * @author liu1028, eden0021, mitc0341, craw0117, kris0068
 */
public class ScubaSE3 {

    private static final ScubaController CONTROLLER = new ScubaController();

    /**
     * Gets the ScubaController singleton
     *
     * @return
     */
    public static ScubaController getController() {
        return CONTROLLER;
    }

    public static void main(String[] args) {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
                }
                else if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScubaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScubaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScubaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScubaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            CONTROLLER.forceViewUpdate();
            CONTROLLER.setViewVisibility(true);
        });
    }
}
