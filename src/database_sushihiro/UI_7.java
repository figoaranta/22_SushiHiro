/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_sushihiro;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author figoaranta
 */
public class UI_7 extends javax.swing.JFrame {

    Connection con;
    public UI_7(Connection con_pass) {
        initComponents();
        Database_sushihiro asd = new Database_sushihiro();
        con = con_pass;
//        createConnection();
        showTransactionID();
        showTable();
        showSumPrice();
        showDate();
        showCashier();
        showPayment();
    }
public UI_7() {
        initComponents();
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
    
    void showTransactionID(){
        try {
            Scanner filename = new Scanner(new File("transactionID.txt"));
            if(filename.hasNext()){
                String transactionID = filename.next();
                jLabel3.setText(transactionID);
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_5.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    void showCashier(){
        try {
            Scanner filename = new Scanner(new File("transactionID.txt"));
            if(filename.hasNext()){
                int ID = Integer.parseInt(filename.next());
                PreparedStatement stmt = con.prepareStatement("SELECT CashierID FROM Transaction WHERE transactionID =?");
                stmt.setInt(1, ID);
                ResultSet rs = stmt.executeQuery();
              
                if(rs.next()){
                    int cashierID = rs.getInt("cashierID");
                    Statement statement = con.createStatement();
                    ResultSet rss = statement.executeQuery("SELECT firstName from Cashier WHERE cashierID="+"'"+cashierID+"'");
                    if(rss.next()){
                    jLabel7.setText(String.valueOf(rss.getString("firstName")));
                    }
                }
        
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_7.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UI_7.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void showPayment(){
        try {
            Scanner filename = new Scanner(new File("transactionID.txt"));
            if(filename.hasNext()){
                int ID = Integer.parseInt(filename.next());
                PreparedStatement stmt = con.prepareStatement("SELECT paymentID FROM Transaction WHERE transactionID =?");
                stmt.setInt(1, ID);
                ResultSet rs = stmt.executeQuery();
              
                if(rs.next()){
                    int paymentID = rs.getInt("paymentID");
                    Statement statement = con.createStatement();
                    ResultSet rss = statement.executeQuery("SELECT paymentName from PaymentType WHERE paymentID="+"'"+paymentID+"'");
                    if(rss.next()){
                    jLabel9.setText(String.valueOf(rss.getString("paymentName")));
                    }
                }
        
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_7.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UI_7.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void showDate(){
        try {
            Scanner filename = new Scanner(new File("transactionID.txt"));
            if(filename.hasNext()){
                String transactionID = filename.next();
                PreparedStatement stmt = con.prepareStatement("SELECT transactionDate from Transaction WHERE transactionID =?");
                stmt.setInt(1, Integer.parseInt(transactionID));
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    jLabel5.setText(rs.getTimestamp("transactionDate").toString());
                }
                stmt.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_7.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UI_7.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    
    }
    void showSumPrice(){
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        int sum = 0;
        for(int i = 0 ; i < tableModel.getRowCount();i++){
            sum = sum + (Integer.parseInt(tableModel.getValueAt(i, 2).toString())*Integer.parseInt(tableModel.getValueAt(i, 0).toString()));
        }
        System.out.println(sum);
        sumPriceLabel.setText(Integer.toString(sum));
    }
    
    
    void showTable(){
    DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
    tableModel.setRowCount(0);
        try {
            Scanner filename = new Scanner(new File("transactionID.txt"));
            PreparedStatement stmt = con.prepareStatement("SELECT * from OrderedItem WHERE transactionID = ? ");
            Statement statement = con.createStatement();
            ResultSet rss = statement.executeQuery("SELECT * from Item");
            if(filename.hasNext()){
                int transactionID = Integer.parseInt(filename.next());
                stmt.setInt(1, transactionID);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()){
                   System.out.println(rs.getInt("itemID"));
                    while(rss.next()){
                        System.out.println("Asd");
                        if(rs.getInt("itemID") == rss.getInt("itemID")){
                           tableModel.addRow(new Object[]{rs.getInt("Quantity"),rss.getString("itemName"),rss.getInt("Price")}); 
                           break;
                        }
                        
                    }
                    
            
            }
                statement.close();
                stmt.close();
            
            }
                    
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_5.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UI_5.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sumPriceLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Quantity", "itemName", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Transaction ID:");

        jLabel2.setText("Total:");

        jLabel4.setText("Time Stamp:");

        jButton2.setText("Return");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("CashierName:");

        jLabel8.setText("Payment Type:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sumPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sumPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton2))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new UI2(con).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(UI_7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI_7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI_7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI_7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_7().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel sumPriceLabel;
    // End of variables declaration//GEN-END:variables
}
