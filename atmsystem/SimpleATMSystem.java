/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmsystem;
import atmsystem.Login;
/**
 *
 * @author suhani
 */
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;
public class SimpleATMSystem {
    //jdbc:oracle:thin:@localhost:1521:XE
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        int pin;
        try{
            Scanner sc=new Scanner(System.in);
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection mycon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","panna123");
            Statement st=mycon.createStatement();
            ResultSet rs=st.executeQuery("select * from users");
            while(rs.next()){
              String name=rs.getString("name");
              pin=rs.getInt("pin");
              double balance=rs.getDouble("balance");
              
              System.out.println(name+" "+pin+" "+balance);
              System.out.println("---------------------------");
             }
        }
        catch(Exception e){
           System.out.println("Error"+e);
        }
       // jdbc:oracle:thin:@localhost:1521:XE
}
}
    

 
