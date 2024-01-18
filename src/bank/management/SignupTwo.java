

package bank.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

   
    JComboBox  occupation,income;
    JButton next;
    String formno;
   

    public SignupTwo(String formno) {
        this.formno = formno;
        setLayout(null);
        
        setTitle("NEW ACCOUNT APPLICATION FROM - PAGE 2");
        
        JLabel additionalDetails = new JLabel("ADDITIONAL DETAILS");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);
    
        JLabel dob = new JLabel("Income");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);
        
        String incomecategory[] = {"Null","< 4,000,000","< 10,000,000","Upto 20,000,000"};
        income = new JComboBox(incomecategory);
        income.setBounds(300, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);
     
        JLabel marital = new JLabel("Occupation");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 300, 200, 30);
        add(marital);
       
        String occupationValues[] = {"Salaried","Self-Employed","Bussiness","Student","Retired","Other"};
        occupation = new JComboBox(occupationValues);
        occupation.setBounds(300, 300, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);
       
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 600, 80, 30);
        next.addActionListener(this);
        add(next);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(850, 800);
        setLocation(350,10);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
       
      
        String sincome = (String) income.getSelectedItem();
       
        String soccupation = (String) occupation.getSelectedItem();
        
              
        try {
            
                Conn c = new Conn();
                String query = "insert into signuptwo values('"+formno+"', '"+sincome+"',  '"+soccupation+"')";
                c.s.executeUpdate(query);
            
                setVisible(false);
                new SignupThree(formno).setVisible(true);
                
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public static void main(String[] args) {
        new SignupTwo("");
    }

 }

