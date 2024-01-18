
package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener{
    
    JButton deposit, withdraw, ministatement, pinchange, fastcash, balanceenquiry, exit;
    String pinnumber;
    
   public Transactions(String pinnumber){
       this.pinnumber = pinnumber;
       setLayout(null);
    
       
       JLabel text = new JLabel("Please select your Transaction");
       text.setBounds(210, 300, 700, 35);
       text.setForeground(Color.BLACK);
       text.setFont(new Font("System", Font.BOLD, 16));
       add(text);
       
       deposit = new JButton("Deposit");
       deposit.setBounds(170, 400, 130, 30);
       deposit.addActionListener(this);
       add(deposit);
       
       withdraw = new JButton("Withdraw");
       withdraw.setBounds(330, 400, 125, 30);
       withdraw.addActionListener(this);
       add(withdraw);
       
       fastcash = new JButton("Fast Cash");
       fastcash.setBounds(170, 370, 130, 30);
       fastcash.addActionListener(this);
       add(fastcash);
       
       ministatement = new JButton("Mini Statement");
       ministatement.setBounds(330, 370, 125, 30);
       ministatement.addActionListener(this);
       add(ministatement);
       
       pinchange = new JButton("Pin Change");
       pinchange.setBounds(170, 430, 130, 30);
       pinchange.addActionListener(this);
       add(pinchange);
       
       balanceenquiry = new JButton("Balance Enquiry");
       balanceenquiry.setBounds(330, 430, 125, 30);
       balanceenquiry.addActionListener(this);
       add(balanceenquiry);
       
       exit = new JButton("Exit");
       exit.setBounds(330, 455, 125, 30);
       exit.addActionListener(this);
       add(exit);
       
       setSize(800, 800);
       setLocation(300, 0);
       setUndecorated(true);
       setVisible(true);
       
   }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            System.exit(0);
        }else if(ae.getSource() == deposit){
            setVisible(false);
            new Deposit(pinnumber).setVisible(true);
        }else if(ae.getSource() == withdraw){
            setVisible(false);
            new Withdraw(pinnumber).setVisible(true);
        }else if(ae.getSource() == fastcash){
            setVisible(false);
            new FastCash(pinnumber).setVisible(true);
        }else if(ae.getSource() == pinchange){
            setVisible(false);
            new PinChange(pinnumber).setVisible(true);
        }else if(ae.getSource() == balanceenquiry){
            setVisible(false);
            new BalanceEnquiry(pinnumber).setVisible(true);
        }else if(ae.getSource() == ministatement){
      
            new MiniStatement(pinnumber).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Transactions("");
    }
    
}
   
    

