

package bank.management;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;


public class FastCash extends JFrame implements ActionListener{
    
    JButton deposit, withdraw, ministatement, fastcash, exit;
    String pinnumber;
    
   public FastCash(String pinnumber){
       this.pinnumber = pinnumber;
       setLayout(null);
       
      
       JLabel text = new JLabel("SELECT WITHDRAW AMOUNT");
       text.setBounds(210, 300, 700, 35);
       text.setForeground(Color.BLACK);
       text.setFont(new Font("System", Font.BOLD, 16));
       add(text);
       
       deposit = new JButton("500.000");
       deposit.setBounds(170, 400, 130, 30);
       deposit.addActionListener(this);
       add(deposit);
       
       withdraw = new JButton("1.000.000");
       withdraw.setBounds(330, 400, 125, 30);
       withdraw.addActionListener(this);
       add(withdraw);
       
       fastcash = new JButton("100.000");
       fastcash.setBounds(170, 370, 130, 30);
       fastcash.addActionListener(this);
       add(fastcash);
       
       ministatement = new JButton("200.000");
       ministatement.setBounds(330, 370, 125, 30);
       ministatement.addActionListener(this);
       add(ministatement);
       
      
       
       exit = new JButton("BACK");
       exit.setBounds(330, 455, 125, 30);
       exit.addActionListener(this);
       add(exit);
       
       setSize(800, 800);
       setLocation(300, 0);
       setUndecorated(true);
       setVisible(true);
       
   }
    public void actionPerformed(ActionEvent ae){
        //so sánh địa chỉ của 2 đối tượng ActionEvent và exit nên ko cần ép kiểu
        if(ae.getSource() == exit){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }else {
            //lấy ra giá trị của nút được nhấn để sử dụng cho hành động rút tiền, mục đích để biết đã rút ra bao nhiêu tiền
            //replace(".", "") thay thế dấu "." trong các text của JButton
            
            String amount = ((JButton)ae.getSource()).getText().replace(".", "");

            Conn c = new Conn();
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                int balance = 0 ;
                    while(rs.next()){
                        if(rs.getString("type").equals("Deposit")){
                            balance += Integer.parseInt(rs.getString("amount"));

                        }else{
                            balance -= Integer.parseInt(rs.getString("amount"));
                        }
                    }
                    if(ae.getSource() != exit && balance < Integer.parseInt(amount)){
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                
                Date date = new Date();
                String query = "insert into bank values ('"+pinnumber+"', '"+date+"', 'Withdraw', '"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Debited Successfully" +amount+ "VND");
                
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public static void main(String[] args) {
        new FastCash("");
    }
    
}
   
    

