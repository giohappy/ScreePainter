/*
 * PreferencesDialog.java
 *
 * Created on June 2, 2007, 9:03 PM
 */

package ika.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * Shows a preferences dialog.
 * @author  Bernhard Jenny, Institute of Cartography, ETH Zurich.
 */
public class PreferencesDialog extends javax.swing.JDialog {
    
    private PreferencesPanel preferencesPanel;

    public static boolean canCreatePreferencesDialog() {
        try {
            java.util.Properties props = ika.utils.PropertiesLoader.loadProperties("ika.app.Application");
            String className = props.getProperty("PreferencesPanel");
            if (className == null) {
                return false;
            }
            Class prefDialogClass = Class.forName(className);
            return prefDialogClass != null;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }

    /** Creates new form PreferencesDialog */
    public PreferencesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        try {
            // create the content of the preferences dialog.
            JPanel prefsPanel = this.createNewPreferencesPanel();
            this.getContentPane().add(prefsPanel, BorderLayout.CENTER);
            
            // name of the application
            String appName = ika.app.ApplicationInfo.getApplicationName();
            
            // window title
            String windowTitle = appName + " Preferences";
            this.setTitle(windowTitle);
            
            // ok button reacts on return key
            this.getRootPane().setDefaultButton(this.okButton);
            
            // cancel button reacts on escape key.
            ActionListener l = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cancel();
                }
            };
            this.cancelButton.registerKeyboardAction(l, "EscapeKey",
                    KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE,
                    0 , true), JComponent.WHEN_IN_FOCUSED_WINDOW);
            
            // size
            this.pack();
            this.setResizable(false);
            
            // location
            Dimension screen = this.getToolkit().getScreenSize();
            Dimension dialog = this.getSize();
            this.setLocation((screen.width - dialog.width) / 2,
                    (screen.height - dialog.height) / 2 );
            
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    /**
     * Creates a new sub-class of PreferencesPanel and returns it. The name of the
     * class that is instantiated is extracted from the MainWindow.properties
     * file and is called PreferencesPanel.
     * @return The new document window.
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    private JPanel createNewPreferencesPanel()
    throws java.lang.ClassNotFoundException,
            java.lang.InstantiationException,
            IllegalAccessException {
        
        java.util.Properties props
                = ika.utils.PropertiesLoader.loadProperties("ika.app.Application");
        String className = props.getProperty("PreferencesPanel");
        this.preferencesPanel
                = (PreferencesPanel)Class.forName(className).newInstance();
        return (JPanel)this.preferencesPanel;
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 20, 20));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        buttonPanel.add(cancelButton);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        buttonPanel.add(okButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void cancel() {
        this.setVisible(false);
        this.preferencesPanel.cancelPressed();
        this.dispose();
    }
    
    private void ok() {
        this.setVisible(false);
        this.preferencesPanel.okPressed();
        this.dispose();
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.cancel();
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.ok();
    }//GEN-LAST:event_okButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PreferencesDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    public PreferencesPanel getPreferencesPanel() {
        return preferencesPanel;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
    
}
