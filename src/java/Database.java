
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.apache.catalina.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mehraj Bhat
 */
public class Database {
       static final String USER = "root";
       static final String PASS = "12345";
      static final String DB_URL = "jdbc:mysql://localhost:8080/jdbc";
      
      Connection conn = null;
          PreparedStatement stmt = null;
          Statement statement;
        
         public String connect()
                 
         {
          
            String message="null";

        try {
            //STEP 2: Driver Registartion & Connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            message="Connected With Database Succesfully";

        } catch (Exception se) {
            message=""+se.toString();
        }
        finally{
        return message;
        }
         
         }
         
   
    
    public Message login(String email,String pass) throws SQLException
    {
        
        
         String message="Student Not Found";
            
           Message me=new Message();
           me.Name=null;
           me.exists=false;
          // me.message="Student Not Found";
           
           
          PreparedStatement ps=conn.prepareStatement(  
            "select * from student where email= ? and pass=?");  
             ps.setString(1,email);  
             ps.setString(2,pass);  
             
           ResultSet result=ps.executeQuery(); 
                   
            if(result.next()) {
               
                me.Name=result.getString("name");
                me.exists=true;
              //  me.message="Student is registered ";
               // message="Student is registered "+result.getString("name");
            }
            
            return me;
    
    }
    
    
         
    
    
    
}
