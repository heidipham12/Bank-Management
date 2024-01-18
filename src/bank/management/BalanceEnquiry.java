
package bank.management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class BalanceEnquiry extends JFrame implements ActionListener{

    JButton back ;
    String pinnumber;
    public BalanceEnquiry(String pinnumber)  {
        this.pinnumber = pinnumber;
         setLayout(null);
     
        back = new JButton("Back");
        back.setBounds(330, 455, 125, 30);
        back.addActionListener(this);
        add(back);
      
       Conn c = new Conn();
        int balance = 0 ;
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                
                    while(rs.next()){
                        if(rs.getString("type").equals("Deposit")){
                            balance += Integer.parseInt(rs.getString("amount"));

                        }else{
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
            }catch(Exception e){
                System.out.println(e);
            }
        
        JLabel text = new JLabel("Your Current Account balance is" + balance+ "VND");
        text.setForeground(Color.WHITE);
        text.setBounds(170, 300, 400, 30);
        add(text);
       
        setSize(800, 800);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }
   

    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }
    
    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
