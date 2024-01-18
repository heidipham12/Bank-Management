
package bank.management;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Deposit extends JFrame implements ActionListener{
    
    JTextField amount;
    JButton deposit, back;
    String pinnumber;
    public Deposit(String pinnumber){
        this.pinnumber = pinnumber;
       setLayout(null);
        
       JLabel text = new JLabel("Enter the amount you want to deposit");
       text.setBounds(155, 300, 400, 20);
       text.setForeground(Color.BLACK);
       text.setFont(new Font("System", Font.BOLD, 16));
       add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(155, 350, 300, 25);
        add(amount);
        
        deposit = new JButton("Deposit");
        deposit.setBounds(330, 430, 125, 30);
        deposit.addActionListener(this);
        add(deposit);
        
        back = new JButton("Back");
        back.setBounds(330, 455, 125, 30);
        back.addActionListener(this);
        add(back);
    
        setSize(800, 800);
        setLocation(300, 0);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == deposit){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            }else{
                try {
                    Conn c = new Conn();
                    String query = "insert into bank values('"+pinnumber+"', '"+date+"', 'Deposit', '"+number+"')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null,"Deposited Successfully"   +number+  "VND");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        }else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Deposit("");
    }
}
