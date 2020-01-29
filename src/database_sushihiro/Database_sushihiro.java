
package database_sushihiro;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;


public class Database_sushihiro {

 
    public static void main(String[] args) {
        Database_sushihiro pro = new Database_sushihiro();
        pro.createConnection();
        
    }
        public Database_sushihiro(){
        createConnection();
    }
        

    
    Connection createConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sushihiro?useTimezone=true&serverTimezone=UTC","root","1Dodoandjoa");
            con = DriverManager.getConnection("jdbc:mysql://dbta.1ez.xyz:3306/22_SushiHiro?useTimezone=true&serverTimezone=Asia/Jakarta","FIG6202","khza190y");

          
            
            System.out.println("Connection Successful.");
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database_sushihiro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
