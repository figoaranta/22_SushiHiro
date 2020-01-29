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
public class UI_3 extends javax.swing.JFrame {
    Connection con;
    
    public UI_3(Connection con_pass) {
        
        initComponents();
//        Database_sushihiro asd = new Database_sushihiro();
        con = con_pass;
//        createConnection();
        showTable();
        clock();
    }
    public UI_3(){
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
            System.out.println("Connection Established");
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showTable(){
        
        DefaultTableModel tableModel = (DefaultTableModel) cashierTable.getModel();
        tableModel.setRowCount(0);
        try {
            Scanner filename = new Scanner(new File("branchName.txt"));
            String branchName="";
            while(filename.hasNext()){
                branchName = branchName + filename.next() +" "; 
            }
            branchName = branchName.substring(0,branchName.length()-1);
            
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT ID FROM Branch WHERE branchName = " +"'"+ branchName+"'");
//                ResultSet rs = statement.executeQuery("SELECT branch_id FROM Branch WHERE branchName =  'Sushi Hiro Senayan City'");

                System.out.println(branchName);
                PreparedStatement stmt = con.prepareStatement("SELECT cashierID,firstName,lastName FROM Cashier WHERE branch = ?");
                while(rs.next()){
                    stmt.setInt(1, rs.getInt("ID"));
                    ResultSet rss = stmt.executeQuery();
                        while(rss.next()){
                            int cashierID = rss.getInt("cashierID");
                            String firstName = rss.getString("firstName");
                            String lastName = rss.getString("lastName");
                            tableModel.addRow(new Object[]{cashierID,firstName,lastName});                
                        }
                        stmt.close();
            }
            statement.close();
            
            
            filename.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UI_3.class.getName()).log(Level.SEVERE, null, ex);
         } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_3.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enterCashierButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        cashierTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        timeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enterCashierButton.setText("Enter");
        enterCashierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterCashierButtonActionPerformed(evt);
            }
        });

        cashierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cashierID", "firstName", "lastName"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cashierTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashierTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cashierTable);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(enterCashierButton, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(73, 73, 73))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterCashierButton)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterCashierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterCashierButtonActionPerformed
    try {
            Scanner fileBranch = new Scanner(new File("branchName.txt"));
            String branchName="";
            while(fileBranch.hasNext()){
                branchName = branchName + fileBranch.next() +" "; 
            }
            branchName = branchName.substring(0,branchName.length()-1);
            
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT ID FROM Branch WHERE branchName = " +"'"+ branchName+"'");
            if(rs.next()){
                int row = cashierTable.getSelectedRow();
                int cashierID = Integer.parseInt(cashierTable.getModel().getValueAt(row, 0).toString());
                PreparedStatement stmt = con.prepareStatement("UPDATE Transaction SET cashierID=?,branch_id=? WHERE transactionID=?");
                Scanner filename = new Scanner(new File("transactionID.txt"));
                if(filename.hasNext()){
                    int transactionID = Integer.parseInt(filename.next()); 

               stmt.setInt(3, transactionID);
               stmt.setInt(1,cashierID);
               stmt.setInt(2, rs.getInt("ID"));
               stmt.executeUpdate();
               stmt.close();
               statement.close();
               filename.close();
               
               new UI2(con).setVisible(true);
               this.setVisible(false);
               }
           }
            rs.close();
       } catch (SQLException ex) {
           Logger.getLogger(UI_3.class.getName()).log(Level.SEVERE, null, ex);
       } catch (FileNotFoundException ex) {
            Logger.getLogger(UI_3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_enterCashierButtonActionPerformed

    private void cashierTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashierTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cashierTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new UI2(con).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_3().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cashierTable;
    private javax.swing.JButton enterCashierButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
