
package bank.management;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame{

    String pinnumber;
    public MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;
        setTitle("Mini Statement");
        
        setLayout(null);
        
        JLabel mini = new JLabel();
        mini.setBounds(20, 140, 400, 200);
        add(mini);
        
        JLabel bank = new JLabel("ABC Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);
        
       
        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 50);
        add(balance);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
            while(rs.next()){
                //lấy kết quả của cột cardmunber trong csdl sau đó lấy 4 kí tự của số thẻ, sau đó ẩn 8 kí tự tiếp theo, sau đó lấy kí tư thứ 12 trở đi của số thẻ
                card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            Conn c = new Conn();
            int bal = 0 ;//lưu số dư tài khoản
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");//truy vấn csdl lấy các kết quả có mã pin trùng với mã pin của người dùng
            while(rs.next()){
                //tạo văn bản cho đối tượng JLable tên mini bằng cách thêm ngày tháng, loại giao dịch, số tiền giao dịch và dùng HTML để định dạng văn bản
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                if(rs.getString("type").equals("Deposit")){
                            bal += Integer.parseInt(rs.getString("amount"));

                        }else{
                            bal -= Integer.parseInt(rs.getString("amount"));
                        }
            }
            balance.setText("Your current account balance is" + bal + "VND");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
        
        setSize(500, 700);
        setLocation(40, 40);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    
    
    
    public static void main(String[] args) {
        new MiniStatement("");
    }
}
