package GUI;

import BL.Station;
import BL.TableModel;
import BL.TableRenderer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class WetterstationGUI extends javax.swing.JFrame {

    private TableModel model = new TableModel();
    private TableRenderer rend = new TableRenderer();
    private File file = new File("src\\data.bin");
    
    public WetterstationGUI() throws IOException, FileNotFoundException, ClassNotFoundException {
        initComponents();
        taOutput.setModel(model);
        taOutput.setDefaultRenderer(Object.class, rend);
        taOutput.setShowGrid(true);
        
        try{
            model.load(file);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Load error!");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        AusEinblenden = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        taOutput = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Add = new javax.swing.JMenuItem();
        Remove = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        Set = new javax.swing.JMenuItem();
        Set2 = new javax.swing.JMenuItem();

        AusEinblenden.setText("Ausblenden/Einblenden");
        AusEinblenden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AusEinblendenActionPerformed(evt);
            }
        });
        jPopupMenu1.add(AusEinblenden);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setComponentPopupMenu(jPopupMenu1);

        taOutput.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        taOutput.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(taOutput);

        jMenu1.setText("Staions");

        Add.setText("Add Weather Station");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });
        jMenu1.add(Add);

        Remove.setText("Remove Weather Station");
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });
        jMenu1.add(Remove);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Values");

        Set.setText("Set Temperature");
        Set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetActionPerformed(evt);
            }
        });
        jMenu2.add(Set);

        Set2.setText("Set Humidity");
        Set2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Set2ActionPerformed(evt);
            }
        });
        jMenu2.add(Set2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        StationDlg dlg = new StationDlg(this, true);
        dlg.setVisible(true);

        if (dlg.isOk()) {
            model.add(dlg.getStation());
        }
    }//GEN-LAST:event_AddActionPerformed

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
        int[] idx = taOutput.getSelectedRows();

        for (int i = idx.length - 1; i >= 0; i--) {
            model.remove(idx[i]);
        }
    }//GEN-LAST:event_RemoveActionPerformed

    private void AusEinblendenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AusEinblendenActionPerformed
        if (model.isLevel()) {
            model.setLevel(false);
            rend.setLevel(false);
        } else {
            model.setLevel(true);
            rend.setLevel(true);
        }
        model.fireTableStructureChanged();
    }//GEN-LAST:event_AusEinblendenActionPerformed

    private void SetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetActionPerformed
        double temp = Double.parseDouble(JOptionPane.showInputDialog("Input new temperature: "));
        Station s = (Station) model.getValueAt(taOutput.getSelectedRow(), 0);
        if (!(temp < -35 || temp > 45)) {
            s.setTemp(temp);
            model.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(null, "Please input a valid temperature!");
        }
    }//GEN-LAST:event_SetActionPerformed

    private void Set2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Set2ActionPerformed
        int hum = Integer.parseInt(JOptionPane.showInputDialog("Input new humidity: "));
        Station s = (Station) model.getValueAt(taOutput.getSelectedRow(), 0);
        if (!(hum < 0 || hum > 100)) {
            s.setHum(hum);
            model.fireTableDataChanged();
        } else {
            JOptionPane.showMessageDialog(null, "Please input a valid humidity!");
        }
    }//GEN-LAST:event_Set2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try{
            model.safe(file);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Safe error!");
        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WetterstationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WetterstationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WetterstationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WetterstationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WetterstationGUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(WetterstationGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(WetterstationGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Add;
    private javax.swing.JMenuItem AusEinblenden;
    private javax.swing.JMenuItem Remove;
    private javax.swing.JMenuItem Set;
    private javax.swing.JMenuItem Set2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable taOutput;
    // End of variables declaration//GEN-END:variables
}
