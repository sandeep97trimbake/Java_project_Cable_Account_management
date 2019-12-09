import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import javax.imageio.*;
import java.util.Date;


class AddPlan extends JFrame implements ActionListener,ItemListener
{
Connection con;
PreparedStatement ps;
JLabel lpid,lpname,ldetails,lptype,lamt,date;
JTextField tpid,tpname,tdetails,tamt;
JComboBox jptp;
JPanel p;
Image img;
JButton bback,bsave;
String type[]={"Base Pack","Add On"};
String ptype=type[0];
ImageIcon isave,iback;

AddPlan()
{
setLayout(null);


		isave=new ImageIcon(getClass().getResource("save.jpg"));
                iback=new ImageIcon(getClass().getResource("back.jpg"));


Date d=new Date();

date=new JLabel("DATE :"+d);
date.setSize(250,30); date.setLocation(700,20); add(date);


lpid=new JLabel("PLAN ID :");
lpid.setSize(100,30);
lpid.setLocation(400,140);
add(lpid);


tpid=new JTextField(" ");
tpid.setSize(200,30);
tpid.setLocation(550,140);
tpid.addKeyListener(new KeyAdapter()
{
public void keyTyped(KeyEvent ke)
{
char ch=ke.getKeyChar();
if(!Character.isDigit(ch) && ch!='\b')
{
ke.setKeyChar('\b');
return;
}
}
});
add(tpid);
lpname=new JLabel("PLAN NAME :");
lpname.setSize(100,30);
lpname.setLocation(400,200);
add(lpname);

tpname=new JTextField(" ");
tpname.setSize(200,30);
tpname.setLocation(550,200);
tpname.addKeyListener(new KeyAdapter()
                                {
                                public void keyTyped(KeyEvent ke)
                                {
                                char ch=ke.getKeyChar();
                                if(!Character.isLetter(ch) && ch!='\b' )
                                {
                                if(Character.isSpace(ch))
                                {
                                }
                                else
                                {
                                ke.setKeyChar('\b');
                                return;
                                }
                                }
                                }
                                });

add(tpname);


ldetails=new JLabel("PLAN DETAILS :");
ldetails.setSize(200,30);
ldetails.setLocation(400,270);
add(ldetails);


tdetails=new JTextField(" ");
tdetails.setSize(200,60);
tdetails.setLocation(550,270);
tdetails.addKeyListener(new KeyAdapter()
                                {
                                public void keyTyped(KeyEvent ke)
                                {
                                char ch=ke.getKeyChar();
                                if(!Character.isLetter(ch)&&  ch!=','  && ch!='\b' )
                                {
                                if(Character.isSpace(ch))
                                {
                                }
                                else
                                {
                                ke.setKeyChar('\b');
                                return;
                                }
                                }
                                }
                                });

add(tdetails);
lptype=new JLabel("PLAN TYPE :");
lptype.setSize(200,30);
lptype.setLocation(400,370);
add(lptype);


jptp=new JComboBox(type);
jptp.setSize(100,30);
jptp.setLocation(550,370);
add(jptp);
jptp.addItemListener(this);

lamt=new JLabel("AMOUNT :");
lamt.setSize(100,30);
lamt.setLocation(400,450);
add(lamt);


tamt=new JTextField(" ");
tamt.setSize(100,30);
tamt.setLocation(550,450);
tamt.addKeyListener(new KeyAdapter()
{
public void keyTyped(KeyEvent ke)
{
char ch=ke.getKeyChar();
if(!Character.isDigit(ch) && ch!='\b'&& ch!='.')
{
ke.setKeyChar('\b');
return;
}
String s=tamt.getText();
if(s.contains(".") && ch=='.')
{
ke.setKeyChar('\b');
}
}
});
add(tamt);

bback=new JButton("2",iback);//back
bback.addActionListener(this);
bback.setSize(170,47);
bback.setLocation(600,550);
add(bback);

bsave=new JButton("1",isave);//save
bsave.addActionListener(this);
bsave.setSize(170,47);
bsave.setLocation(300,550);
add(bsave);

try
{

Class.forName("org.postgresql.Driver");

                        con=DriverManager.getConnection("jdbc:postgresql://localhost/cms","postgres","redhat");

                        if(con==null)
                        {
                                System.out.println("coonection faild");
                                System.exit(0);
                        }
                }
                catch(Exception e)
                {
                        System.out.println(e);
                }
        


p=new JPanel()
                {
                        public void paint(Graphics g)
                        {

                                try
                                {
                                        File f=new File("screen2.jpg");
                                        img=ImageIO.read(f);
                                        g.drawImage(img,0,0,1224,765,this);
                                }
                                catch(Exception e)
                                {
                                        JOptionPane.showMessageDialog(null,e);
                                }
                        }

                };

                p.setSize(1224,765);
                p.setLocation(0,0);
                add(p);

setTitle("Add Plan");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(1224,765);
setVisible(true);


}
public void actionPerformed(ActionEvent ae)
{
String ac=ae.getActionCommand();
if(ac.equals("2"))//back
{
dispose();
}

if(ac.equals("1"))//save
{
String pid=tpid.getText();
String name=tpname.getText();
String pckgdtls=tdetails.getText();
String ptype1=ptype;
String amount=tamt.getText();

try
{
ps=con.prepareStatement("insert into plan values(?,?,?,?,?)");
ps.setString(1,pid);
ps.setString(2,name);
ps.setString(3,pckgdtls);
ps.setString(4,ptype);
ps.setString(5,amount);

int i=ps.executeUpdate();
if(i==0)
{
JOptionPane.showMessageDialog(null,"Record Insertion fail");
}

else 
{
JOptionPane.showMessageDialog(null,"Record Add successfully");
}
}

catch(Exception e)
{
System.out.println(e);
}
}
}
public void itemStateChanged(ItemEvent ie)
{
ptype=(String)ie.getItem();
}


public static void main(String args[]) 
{
AddPlan p=new AddPlan();
}
}


