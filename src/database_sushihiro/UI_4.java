/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_sushihiro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author figoaranta
 */
public class UI_4 extends javax.swing.JFrame {

    Connection con;
    public UI_4(Connection con_pass) {
        initComponents();
//        Database_sushihiro asd = new Database_sushihiro();
        con = con_pass;
//        createConnection();
        showTable(con);
        clock();
    }
    public UI_4(){
    initComponents();
    }
    

    void clock(){
        new Timer(0,new ActionListener(){
            
            
            
            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
                timeLabel.setText(s.format(d));
            }
        
        
        
        }
        ).start();
    }
   public void showTable(Connection con){
       String tipe;
       DefaultTableModel tableModel = (DefaultTableModel) foodTable.getModel();
       tableModel.setRowCount(0);
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Item WHERE typeID =? ORDER BY ItemName ASC;");
//            Statement stmt = con.createStatement();
            System.out.print("a");
            if(foodTypeComboBox.getSelectedItem().toString()== "Beverages"){
//                tipe = "2";
                stmt.setInt(1, 2);
            }
            else{
//                tipe = "1";
                stmt.setInt(1, 1);
            }
//            System.out.print("Select * from item where typeID =" + tipe);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String itemName = rs.getString("itemName");
                int price = rs.getInt("Price");
                tableModel.addRow(new Object[]{itemName,price}); 
//                
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UI_4.class.getName()).log(Level.SEVERE, null, ex);
            
        }
   }
    
    
    
    void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://dbta.1ez.xyz:3306/22_SushiHiro?useTimezone=true&serverTimezone=Asia/Jakarta","FIG6202","khza190y");
            Statement stmt = con.createStatement();
            System.out.println("Successfully Connected.");
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        foodTypeComboBox = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        quantityTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        foodTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        foodTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Beverages", "Food" }));
        foodTypeComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                foodTypeComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        foodTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodTypeComboBoxActionPerformed(evt);
            }
        });

        jButton1.setText("Enter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        quantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Quantity");

        jLabel2.setText("Food Type");

        foodTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Price"
            }
        ));
        jScrollPane1.setViewportView(foodTable);

        jButton2.setText("Return");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(foodTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(210, 210, 210)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(quantityTextField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jButton1)))
                    .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foodTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityTextFieldActionPerformed

    private void foodTypeComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_foodTypeComboBoxPopupMenuWillBecomeInvisible
        foodTable.removeAll();
        showTable(con);
    }//GEN-LAST:event_foodTypeComboBoxPopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new UI_6(con).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(foodTable.getSelectedRow()==-1){
        new foodErrorMessage2().setVisible(true);
        }
        else{
        try {
            Scanner filename = new Scanner(new File("transactionID.txt"));
            Statement statement = con.createStatement();
            int row = foodTable.getSelectedRow();
            String itemName = foodTable.getValueAt(row, 0).toString();
            
            if(filename.hasNext()){
                int transactionID = Integer.parseInt(filename.next());
                ResultSet rs = statement.executeQuery("SELECT itemID FROM Item WHERE ItemName="+"'"+itemName+"'");
                if(rs.next()){
                Statement validationstmt = con.createStatement();
                ResultSet Validationstmtset = validationstmt.executeQuery("SELECT itemID FROM OrderedItem WHERE transactionID ="+"'"+transactionID+"'");
                while(Validationstmtset.next()){
                    if(Validationstmtset.getInt("itemID")==rs.getInt("itemID")){
                        PreparedStatement stmt = con.prepareStatement("Update OrderedItem SET Quantity = ? WHERE TransactionID="+"'"+transactionID+"' AND itemID ="+"'"+rs.getInt("itemID")+"'");
                        Statement getItemID = con.createStatement();
                        ResultSet rss = getItemID.executeQuery("SELECT Quantity FROM OrderedItem WHERE itemID="+"'"+rs.getInt("itemID")+"'AND transactionID ="+"'"+transactionID+"'");
                        if(rss.next()){
                            if(quantityTextField.getText().isEmpty()){
                            new foodErrorMessage2().setVisible(true);
                            getItemID.close();
                            return;
                            }
                            stmt.setInt(1, rss.getInt("Quantity")+Integer.parseInt(quantityTextField.getText().toString()));
                            stmt.executeUpdate();
                            stmt.close();
                            jLabel3.setText("Item has been added.");
                            quantityTextField.setText("");
                            return;
                        }
                    }
                }
                
                        PreparedStatement stmt2 = con.prepareStatement("INSERT OrderedItem VALUES (?,?,?)");
                        stmt2.setInt(2, rs.getInt("itemID"));
                        if(quantityTextField.getText().isEmpty()){
                            new foodErrorMessage2().setVisible(true);
                            stmt2.close();
                            return;
                            }
                        stmt2.setInt(3, Integer.parseInt(quantityTextField.getText()));
                        stmt2.setInt(1, transactionID);
                        stmt2.execute();        
                        stmt2.close();
                        jLabel3.setText("Item has been added.");
                        quantityTextField.setText("");
//                        new UI2().setVisible(true);
//                        this.setVisible(false);
                }
                statement.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UI_4.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void foodTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodTypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodTypeComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(UI_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_4().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable foodTable;
    private javax.swing.JComboBox<String> foodTypeComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
