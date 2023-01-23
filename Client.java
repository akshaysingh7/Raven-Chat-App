package tatakae.chat;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;
public class Client  implements ActionListener {
    
    JTextField text;
   static JPanel t1;
    static Box vertical= Box.createVerticalBox();
   static JFrame f=new JFrame();
    static DataOutputStream dout;
    Client(){
          
        f.setLayout(null);
         JPanel p1= new JPanel();
         p1.setBackground(new Color(14 ,94,84));
         p1.setBounds(0,0,450,70);
         p1.setLayout(null);
         f.add(p1); 
         
         
         ImageIcon i1= new ImageIcon("C:\\Users\\aksha\\Documents\\NetBeansProjects\\Tatakae Chat\\src\\tatakae\\icons\\340.png");
         Image i2=i1.getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel back=new JLabel(i3);
         back.setBounds(7,20,30,30);    
         p1.add(back);
          
         back.addMouseListener(new MouseAdapter(){
             public void mouseClicked(MouseEvent ae){
               f.setVisible(false);  
             }
         })      ;
          ImageIcon i4= new ImageIcon("C:\\Users\\aksha\\Documents\\NetBeansProjects\\Tatakae Chat\\src\\tatakae\\icons\\subroza.png");
         Image i5=i4.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
         ImageIcon i6=new ImageIcon(i5);
         JLabel profile=new JLabel(i6);
         profile.setBounds(50,15,45,40);    
         p1.add(profile);
         
         ImageIcon i7= new ImageIcon("C:\\Users\\aksha\\Documents\\NetBeansProjects\\Tatakae Chat\\src\\tatakae\\icons\\yolo.png");
         Image i8=i7.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
         ImageIcon i9=new ImageIcon(i8);
         JLabel video=new JLabel(i9);
         video.setBounds(300,20,30,30);    
         p1.add(video);
         
         ImageIcon i10= new ImageIcon("C:\\Users\\aksha\\Documents\\NetBeansProjects\\Tatakae Chat\\src\\tatakae\\icons\\call.png");
         Image i11=i10.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
         ImageIcon i12=new ImageIcon(i11);
         JLabel call=new JLabel(i12);
         call.setBounds(345,20,30,30);    
         p1.add(call);
             
         ImageIcon i13= new ImageIcon("C:\\Users\\aksha\\Documents\\NetBeansProjects\\Tatakae Chat\\src\\tatakae\\icons\\3dot.png");
         Image i14=i13.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
         ImageIcon i15=new ImageIcon(i14);
         JLabel tdot=new JLabel(i15);
         tdot.setBounds(390,20,30,30);    
         p1.add(tdot);
         
         JLabel name =new JLabel("Subroza");
         name.setBounds(100,12,100,22);
         name.setForeground(Color.WHITE);
         name.setFont(new Font("SANS_SERIF",Font.BOLD,18));
         p1.add(name);
         
         JLabel status =new JLabel("Active Now");
         status.setBounds(100,30,100,22);
         status.setForeground(Color.WHITE);
         status.setFont(new Font("SANS_SERIF",Font.BOLD,12));
      p1.add(status);
      
      t1=new JPanel();
      t1.setBounds(5,75,440,570);
      f.add(t1);
      
       text=new JTextField();
      text.setBounds(5,655,310,40);
      text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
      f.add(text);
 
      JButton send=new JButton("Send");
      send.setBounds(320,655,123,40);
      send.setBackground(new Color(7,94,84));
      send.setForeground(Color.white);
      send.addActionListener(this);
      send.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
     f. add(send);
       
       
                
      
       
        f.setSize(450,700);
        f.setLocation(800,50);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE); 
        f.setVisible(true);
        
         
 
    }
    
    public void actionPerformed(ActionEvent ae){
       try{ 
           String out =text.getText();
        JPanel p2=formatLabel(out);
       
        t1.setLayout(new BorderLayout());
        JPanel right=new JPanel(new BorderLayout());
        right.add(p2,BorderLayout.LINE_END);
        vertical.add(right);
        vertical.add(Box.createVerticalStrut(15));
        t1.add(vertical,BorderLayout.PAGE_START);
   
        text.setText("");
         f.repaint();
         f.invalidate();
         f.validate();
    
    }
    catch (Exception e){
    e.printStackTrace();
}
    }
    public static JPanel formatLabel(String out){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output=new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN,16));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        
         panel.add(output);
        
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        
        return panel;
        
        
    }
        
   
    public static void main(String []args){
        new Client();
        
        try{
            Socket s=new Socket("127.0.0.1",6001);
             DataInputStream din=new DataInputStream(s.getInputStream());
              dout= new DataOutput.Stream(s.getOutputStream());
              
               while(true){
                  t1.setLayout(new BorderLayout());
               
                   String msg=din.readUTF();
                   JPanel panel=formatLabel(msg);
                   JPanel left= new JPanel(new BorderLayout());
                   left.add(panel,BorderLayout.LINE_START);
                   vertical.add(left);
                   
                   vertical.add(Box.createVerticalStrut(15));
                   t1.add(vertical,BorderLayout.LINE_END);
                   f.validate();
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

