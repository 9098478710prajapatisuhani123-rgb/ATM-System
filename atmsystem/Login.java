/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package atmsystem;
import atmsystem.SimpleATMSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author suhani
 */
public class Login {

    private static PreparedStatement pst;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            int pin,oldpin=0,newpin=0;
            double balance;
            String names="";

            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection mycon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "panna123");
            Statement st = mycon.createStatement();
            //ResultSet rs = st.executeQuery("select * from users");
            System.out.println("================================");
            System.out.println("         ATM SYSTEM             ");
            System.out.println("================================");
            Scanner sc = new Scanner(System.in);
                System.out.println("Enter pin");
                int enteredpin = sc.nextInt();
                String query = "select * from users where pin=" + enteredpin;
                ResultSet rs=st.executeQuery(query);
                if(rs.next())
                {
                    System.out.println("Welcome and Continue");
                    balance = rs.getDouble("balance");
                    String name = rs.getString("name");
                    System.out.println("Your Name "+name);
                    name=rs.getString("name");
                    System.out.println("1.Check Balance");
                    System.out.println("2.Deposit");
                    System.out.println("3.Withdraw");
                    System.out.println("4.Pin Change");
                    System.out.println("5.Exit");
                    int choice,amount=0;
                    do{
                    System.out.println("Enter  Your Choice: ");
                    choice=sc.nextInt();
                    if(choice==1){
                        System.out.println("Balance from DB = "+balance);
                    }
                    else if(choice==2){
                    String  sql="update users set balance=balance+? where pin=?";
                    PreparedStatement  pst=mycon.prepareStatement(sql);
                    System.out.print("Enter deposit amount: ");
                    double amount1= sc.nextDouble();
                    pst.setDouble(1, amount1);
                    pst.setInt(2, enteredpin);
                    pst.executeUpdate();
                    balance += amount1;  // deposit
                    System.out.println("Total Balance = "+balance);
                    }
                    else  if(choice==3){
                    String  sql="update users set balance=balance-? where pin=?";
                    PreparedStatement  pst=mycon.prepareStatement(sql);
                    System.out.print("Enter withdrawal amount: ");
                    double amount1= sc.nextDouble();
                    pst.setDouble(1, amount1);
                    pst.setInt(2, enteredpin);
                    pst.executeUpdate();
                    balance -= amount1;  // withdraw
                    System.out.println(" Remaining Balance = "+balance);
                    }
                    else if(choice==4){
                        System.out.println("Enter Oldpin");
                        oldpin = sc.nextInt();
                        System.out.println("Enter Newpin");
                        newpin = sc.nextInt();
                        if(oldpin==enteredpin)
                            System.out.println("Can be change Pin");
                        else
                            System.out.println("Incorrect Old Pin");
                    String  sql="update users set pin=? where pin=?";
                    PreparedStatement  pst=mycon.prepareStatement(sql);
                    pst.setInt(1, newpin);
                    pst.setInt(2, oldpin);
                    pst.executeUpdate();
                    System.out.println("Pin Update Successfully");
                    }
                    else {
                        System.out.println("Exit");
                    }
                  }while(choice!=5);
                }
                else 
                {
                    System.out.println("Invalid pin");
                }
              } 
        catch (Exception e) {
            System.out.print("Error" + e);
        }
    }
}
