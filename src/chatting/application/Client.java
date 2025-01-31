/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//author- kamakshi-srivastava
package chatting.application;
import static chatting.application.Server.dout;
import static chatting.application.Server.f;
import static chatting.application.Server.formatLabel;
import static chatting.application.Server.vertical;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.border.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener{
    
    JTextField text;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f = new JFrame();
    
    Client(){
        f.setLayout(null);
        
        JPanel p1 = new JPanel();
        p1.setBackground(Color.pink.darker());
        p1.setBounds(0,0,450,70);
        p1.setLayout(null);
        f.add(p1); 
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
        Image i2 =i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
        ImageIcon i3 =new ImageIcon(i2);
        
        JLabel back = new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/user2.png"));
        Image i5 =i4.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
        ImageIcon i6 =new ImageIcon(i5);
        
        JLabel profilepic = new JLabel(i6);
        profilepic.setBounds(40,10,50,50);
        p1.add(profilepic);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 =i7.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
        ImageIcon i9 =new ImageIcon(i8);
        
        JLabel video = new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 =i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12 =new ImageIcon(i11);
        
        JLabel phone = new JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/option.png"));
        Image i14 =i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i15 =new ImageIcon(i14);
        
        JLabel option = new JLabel(i15);
        option.setBounds(420,20,10,25);
        p1.add(option);
        
        
        
        JLabel name = new JLabel("User B");
        name.setBounds(110,15,100,20);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD,16));
        p1.add(name);
        
        JLabel status = new JLabel("Active Now");
        status.setBounds(110,40,100,10);
        status.setForeground(Color.white);
        status.setFont(new Font("SAN_SERIF", Font.BOLD,12));
        p1.add(status);
        
        
        a1= new JPanel();
        a1.setBounds(5,75,440,570);
        f.add(a1);
        
        text=new JTextField();
        text.setBounds(5,555,310,40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN,16));
        f.add(text);
        
        JButton send = new JButton("Send");
        send.setBounds(320, 555, 80, 40);
        send.setBackground(Color.pink.darker());
        send.setForeground(Color.WHITE);
        send .addActionListener(this);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN,14));
        f.add(send);
        
        f.setSize(450,600);
        f.setLocation(800,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        try{
        String out = text.getText();
        
        
        JPanel p2 = formatLabel(out);
        
        a1.setLayout(new BorderLayout());
        
        JPanel right = new JPanel(new BorderLayout());
        right.add(p2, BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        
        a1.add(vertical, BorderLayout.PAGE_START);
        
        dout.writeUTF(out);
        text.setText("");
        
        f.repaint();
        f.invalidate();
        f.validate();
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel(out);
        output.setFont(new Font("Tahoma",Font.PLAIN,16));
        output.setBackground(Color.pink.darker());
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
        panel.add(output);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }
    public static void main(String[]args){
        new Client();
        
        try{
            Socket s = new Socket("127.0.0.1", 6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
                
                
                while(true){
                    a1.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical, BorderLayout.PAGE_START);
                    
                    f.validate();
                   
                }
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
