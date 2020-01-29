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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author figoaranta
 */
public class UI2 extends javax.swing.JFrame {
//    UI_1 UI_1 = new UI_1();
    Connection con;
    
    public UI2(Connection con_pass) {
       initComponents();
//        Database_sushihiro asd = new Database_sushihiro();
        con = con_pass;
//        createConnection();
//        fillCombo();
        transactionCombobox.setVisible(false);
        jButton1.setVisible(false);
        printBillButton.setVisible(false);
        showTable();
        clock();
        
    }
    public UI2(){
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
    void createConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://dbta.1ez.xyz:3306/22_SushiHiro?useTimezone=true&serverTimezone=Asia/Jakarta","FIG6202","khza190y");
            Statement stmt = con.createStatement();
            System.out.println("Established Connection.");
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readFile(){
        try {
            Scanner filename = new Scanner(new File("branchName.txt"));
            String branchName = "";
            while(filename.hasNext()){
                branchName = branchName + filename.next()+" ";
            }
            branchName = branchName.substring(0,branchName.length()-1);
            System.out.println("this");
            System.out.println(branchName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void fillCombo(){
        
        try{
            Scanner filename = new Scanner(new File("branchName.txt"));
            String branchName = "";
            while(filename.hasNext()){
                branchName = branchName + filename.next()+" ";
            }
            branchName = branchName.substring(0,branchName.length()-1);
            Statement statement = con.createStatement();
            ResultSet rss = statement.executeQuery("SELECT ID from Branch WHERE branchName ="+"'"+branchName+"'");
            if(rss.next()){
            PreparedStatement stmt = con.prepareStatement("SELECT transactionID from Transaction WHERE paymentComplete = ? and branch_id ="+"'"+rss.getInt("ID")+"'");
//            PreparedStatement stmt = con.prepareStatement("SELECT transactionID from Transaction WHERE paymentComplete = ?");

            
            if(jComboBox1.getSelectedItem().toString() == "Complete"){
                stmt.setBoolean(1, true);
            }
            else{
                stmt.setBoolean(1, false);
            }
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String transactionID = rs.getString("transactionID");
                transactionCombobox.addItem(transactionID);
            }
            stmt.close();
            statement.close();
        }
        }catch(SQLException e){
        JOptionPane.showMessageDialog(null,e);
        }
        catch (FileNotFoundException ex) {
                Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showTable(){
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        try{
            Scanner filename = new Scanner(new File("branchName.txt"));
            String branchName = "";
            while(filename.hasNext()){
                branchName = branchName + filename.next()+" ";
            }
            branchName = branchName.substring(0,branchName.length()-1);
            Statement statement = con.createStatement();
            ResultSet rss = statement.executeQuery("SELECT ID from Branch WHERE branchName ="+"'"+branchName+"'");
            if(rss.next()){
            PreparedStatement stmt = con.prepareStatement("SELECT * from Transaction WHERE paymentComplete = ? and branch_id ="+"'"+rss.getInt("ID")+"'");
//            PreparedStatement stmt = con.prepareStatement("SELECT transactionID from Transaction WHERE paymentComplete = ?");

            
            if(jComboBox1.getSelectedItem().toString() == "Complete"){
                stmt.setBoolean(1, true);
            }
            else{
                stmt.setBoolean(1, false);
            }
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String transactionID = rs.getString("transactionID");
                String CashierID = rs.getString("cashierID");
                tableModel.addRow(new Object[]{transactionID,CashierID}); 
            }
            stmt.close();
            
        }
            statement.close();
            
            
        }catch(SQLException e){
        JOptionPane.showMessageDialog(null,e);
        }
        catch (FileNotFoundException ex) {
                Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        transactionCombobox = new javax.swing.JComboBox<>();
        transactionButton = new javax.swing.JButton();
        cashierButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        timeLabel = new javax.swing.JLabel();
        printBillButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "On-going", "Complete" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        transactionCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionComboboxActionPerformed(evt);
            }
        });

        transactionButton.setText("Add New Transaction");
        transactionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionButtonActionPerformed(evt);
            }
        });

        cashierButton.setText("Add/Edit Cashier");
        cashierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashierButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Add Food");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back to Main Menu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Complete Payment");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("View Order");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "transactionID", "cashierID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        printBillButton.setText("Print Bill");
        printBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBillButtonActionPerformed(evt);
            }
        });

        jButton5.setText("Delete Transaction");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(transactionCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(transactionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(printBillButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cashierButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(transactionCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(transactionButton)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cashierButton)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(printBillButton)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 27, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void transactionComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transactionComboboxActionPerformed

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
        showTable();
        transactionCombobox.removeAllItems();
        fillCombo();
        if(jComboBox1.getSelectedItem().toString()=="Complete"){
            cashierButton.setVisible(false);
            jButton1.setVisible(false);
            jButton4.setVisible(false);
            jButton3.setVisible(false);
            printBillButton.setVisible(true);
        }
        else{
            printBillButton.setVisible(false);
            cashierButton.setVisible(true);
            jButton1.setVisible(false);
            jButton4.setVisible(true);
            jButton3.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void transactionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionButtonActionPerformed
        
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        System.out.println(date);
        
        try {
            
            Scanner filename = new Scanner(new File("branchName.txt"));
            String branchName = "";
            while(filename.hasNext()){
                branchName = branchName + filename.next()+" ";
            }
            branchName = branchName.substring(0,branchName.length()-1);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT ID from Branch WHERE branchName ="+"'"+branchName+"'");
            if(rs.next()){
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Transaction (paymentComplete,branch_id,transactionDate) VALUES(?,?,?)");
            
            stmt.setBoolean(1,false);
            stmt.setInt(2,rs.getInt("ID"));
            stmt.setTimestamp(3, date);
            stmt.execute();
            System.out.println("Insert data successfully");
            
            stmt.close();
            }
            statement.close();
            transactionCombobox.removeAllItems();
            fillCombo();
            showTable();
            jLabel1.setText("New transaction has been added.");
           
        } catch (SQLException ex) {
            Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }catch (FileNotFoundException ex) {
            Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }//GEN-LAST:event_transactionButtonActionPerformed

    private void cashierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashierButtonActionPerformed
        if(jTable1.getSelectedRow()==-1){
            new TransactionCashierErrorMessage().setVisible(true);
            
        }
        else{
        new UI_3(con).setVisible(true);
        String filename = "transactionID.txt";
//        int transactionID = Integer.parseInt(transactionCombobox.getSelectedItem().toString());
        int row = jTable1.getSelectedRow();
        int transactionID = Integer.parseInt(jTable1.getValueAt(row,0).toString());
        System.out.println(transactionID);
        try {
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println(transactionID);
            outputStream.close();
            System.out.println("Done.");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(false);
        }
    }//GEN-LAST:event_cashierButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new UI_4(con).setVisible(true);
        String filename = "transactionID.txt";
        int row = jTable1.getSelectedRow();
       int transactionID = Integer.parseInt(jTable1.getValueAt(row,0).toString());
//        int transactionID = Integer.parseInt(transactionCombobox.getSelectedItem().toString());
        System.out.println(transactionID);
        try {
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println(transactionID);
            outputStream.close();
            System.out.println("Done.");
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            con.close();
            new UI_1().setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(jTable1.getSelectedRow()==-1){
        new TransactionCashierErrorMessage().setVisible(true);
//        this.setVisible(false);
        }
        else{
        String filename = "transactionID.txt";
        int row = jTable1.getSelectedRow();
        String transactionID = jTable1.getValueAt(row,0).toString();
        try {
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println(transactionID);
//            System.out.println(transactionCombobox.getSelectedItem().toString());
            outputStream.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        new UI_5(con).setVisible(true);
        this.setVisible(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jTable1.getSelectedRow()==-1){
            new TransactionCashierErrorMessage().setVisible(true);
        }
        
        else{

            new UI_6(con).setVisible(true);
            String filename = "transactionID.txt";
            int row = jTable1.getSelectedRow();
            int transactionID = Integer.parseInt(jTable1.getValueAt(row,0).toString());
            //        int transactionID = Integer.parseInt(transactionCombobox.getSelectedItem().toString());
            System.out.println(transactionID);
            try {
                PrintWriter outputStream = new PrintWriter(filename);
                outputStream.println(transactionID);
                outputStream.close();
                System.out.println("Done.");
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void printBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBillButtonActionPerformed
        if(jTable1.getSelectedRow()==-1){
            new TransactionCashierErrorMessage().setVisible(true);
        }
        else{
        String filename = "transactionID.txt";
        int row = jTable1.getSelectedRow();
       int transactionID = Integer.parseInt(jTable1.getValueAt(row,0).toString());
//        int transactionID = Integer.parseInt(transactionCombobox.getSelectedItem().toString());
        System.out.println(transactionID);
        try {
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println(transactionID);
            outputStream.close();
            System.out.println("Done.");
            new UI_7(con).setVisible(true);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        }
    }//GEN-LAST:event_printBillButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Transaction WHERE TransactionID =?");
            int row = jTable1.getSelectedRow();
            int transactionID = Integer.parseInt(jTable1.getValueAt(row,0).toString());
            System.out.println(transactionID);
            stmt.setInt(1, transactionID);
            stmt.executeUpdate();
            showTable();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UI2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed
    
    public int returnTransactionID(){
        int transactionID = Integer.parseInt(transactionCombobox.getSelectedItem().toString());
        return transactionID;
    }
    
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
            java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cashierButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton printBillButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JButton transactionButton;
    private javax.swing.JComboBox<String> transactionCombobox;
    // End of variables declaration//GEN-END:variables
}
