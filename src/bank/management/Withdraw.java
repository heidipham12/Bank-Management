

package bank.management;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.sql.*;


public class Withdraw extends JFrame implements ActionListener{
    
    JTextField amount;
    JButton withdraw, back;
    String pinnumber;
    
    public Withdraw(String pinnumber){
        this.pinnumber = pinnumber;
       setLayout(null);
        
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/black1.jpg"));
       Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel image = new JLabel(i3);
       image.setBounds(0, 0, 800, 800);
       add(image);
       
       JLabel text = new JLabel("Enter the amount you want to withdraw");
       text.setBounds(155, 300, 400, 20);
       text.setForeground(Color.WHITE);
       text.setFont(new Font("System", Font.BOLD, 16));
       image.add(text);
        
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(155, 350, 300, 25);
        image.add(amount);
        
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(330, 430, 125, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);
        
        back = new JButton("Back");
        back.setBounds(330, 455, 125, 30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(800, 800);
        setLocation(300, 0);
        setVisible(true);
    }
    
    
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            String number = amount.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
            }else{
                try {
                     Conn c = new Conn();
                     
                     ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
                     int balance = 0;// số dư ban đầu là 0 đồng
                     while(rs.next()){
                       if(rs.getString("type").equals("Deposit")){
                           //lấy giá trị trong cột amount khi đó đang có kiểu string bằng phương thức rs.getString của ResultSet
                           //ép kiểu chuỗi thành kiểu số nguyên bằng Integer.parseInt hoặc kiểu số thực bằng Double.parseDouble
                           balance += Integer.parseInt(rs.getString("amount"));
                                                                
                       }else{
                           balance -= Integer.parseInt(rs.getString("amount"));
                       }
                    }
                     //số dư (balance) nhỏ hơn số tiền muốn rút (number) thì thông báo số dư không dủ
                    if(balance < Integer.parseInt(number)){
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }else{
                        c.s.executeUpdate("insert into bank values('"+pinnumber+"', '"+date+"', 'Withdraw', '"+number+"')");
                    JOptionPane.showMessageDialog(null, " Debited Successfully" +number+ "VND");
                    
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                
                    }
         
                } catch(NumberFormatException e){
                e.printStackTrace();
                System.out.println("error: "+e);
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        }else if(ae.getSource() == back){
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

  
    
   
    
    public static void main(String[] args) {
        new Withdraw("");
    }

}

