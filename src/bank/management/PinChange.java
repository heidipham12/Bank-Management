
package bank.management;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener{

    JPasswordField pin, repin;
    JButton change, back ;
    String pinnumber;
    public PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);
      
       JLabel text = new JLabel("CHANGE YOUR PIN");
       text.setBounds(250, 280, 500, 35);
       text.setForeground(Color.BLACK);
       text.setFont(new Font("System", Font.BOLD, 16));
       add(text);

       JLabel pintext = new JLabel("NEW PIN");
       pintext.setBounds(165, 320, 180, 25);
       pintext.setForeground(Color.BLACK);
       pintext.setFont(new Font("System", Font.BOLD, 16));
       add(pintext);
       
        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(250, 320, 180, 25);
        add(pin);
       
       JLabel repintext = new JLabel(" RE-ENTER NEW PIN");
       repintext.setBounds(165, 360, 180, 25);
       repintext.setForeground(Color.BLACK);
       repintext.setFont(new Font("System", Font.BOLD, 16));
       add(repintext);
       
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 22));
        repin.setBounds(250, 400, 180, 25);
        add(repin);
       
        change = new JButton("Change");
        change.setBounds(330, 430, 125, 30);
        change.addActionListener(this);
        add(change);
        
        back = new JButton("Back");
        back.setBounds(330, 455, 125, 30);
        back.addActionListener(this);
        add(back);
       
        setSize(800, 800);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == change){
           try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if(!npin.equals(rpin)){
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");

                    return;
                }
                
                if(npin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please entered New PIN ");

                    return;
                }
                if(rpin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please re-entered  New PIN ");

                    return;
                }
                
                Conn c = new Conn();
                String query1 = "update bank set pin = '"+rpin+"'where pin = '"+pinnumber+"' ";
                String query2 = "update login set pin = '"+rpin+"'where pin = '"+pinnumber+"' ";
                String query3 = "update signupthree set pin = '"+rpin+"'where pin = '"+pinnumber+"' ";
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);
                
                JOptionPane.showMessageDialog(null, "PIN change successfully");
                
                setVisible(false);
                new Transactions(rpin).setVisible(true);
                
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
 
    public static void main(String[] args) {
        new PinChange("");
    }
}
