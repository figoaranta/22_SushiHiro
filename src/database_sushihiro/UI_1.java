
package database_sushihiro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


public class UI_1 extends javax.swing.JFrame {
    Connection con;
    
    public UI_1() {
//        Database_sushihiro asd = new Database_sushihiro();
//        con = con_pass;
        initComponents();
//        Database_sushihiro asd = new Database_sushihiro();
//        asd.createConnection();
        createConnection();
        fillCombo();
        clock();
        showTable();
    }

    void showTable(){
        String branch2 = null;
        DefaultTableModel tableModel = (DefaultTableModel) cashierTable.getModel();
        tableModel.setRowCount(0);
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT COUNT(branch), branch from Cashier GROUP BY Branch");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            int emp = rs.getInt("COUNT(branch)");
            int branch = rs.getInt("branch");
            if(branch == 20){
            branch2 = "Sushi Hiro Senayan City";
            }
            else if(branch == 21){
            branch2 = "Sushi Hiro Senopati";
            }
            System.out.println(emp);
            tableModel.addRow(new Object[]{emp,branch2});
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(UI_1.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            System.out.println("Successfully Connected.");
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void fillCombo(){
        
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT branchName from Branch");
//            ResultSet rss = stmt.executeQuery("SELECT branch_id from Branch");

            while(rs.next()){
//                String branch_id = Integer.toString(rss.getInt("branch_id"));
                String branchName = rs.getString("branchName");
                branch_Combobox.addItem(branchName);
            }
            stmt.close();
        }catch(SQLException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        branch_Combobox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        timeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cashierTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        branch_Combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                branch_ComboboxActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Your Branch:");

        jButton1.setText("GO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cashierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Total Employee", "Branch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(cashierTable);

        jLabel2.setText("Employee Detail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(branch_Combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branch_Combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(93, 93, 93)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(264, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void branch_ComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_branch_ComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_branch_ComboboxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        String filename = "branchName.txt";
        String branchName = branch_Combobox.getSelectedItem().toString();
        System.out.println(branchName);
        try {
            PrintWriter outputStream = new PrintWriter(filename);
            outputStream.println(branchName);
            outputStream.close();
            System.out.println("Done.");
//            con.close();
            
            new UI2(con).setVisible(true);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public String returnBranchName(){
        String branchName = branch_Combobox.getSelectedItem().toString();
        return branchName;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> branch_Combobox;
    private javax.swing.JTable cashierTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
