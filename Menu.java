import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

class Menu extends JFrame implements ActionListener
{
JMenuBar mbr;
JMenu customer,plans,help;
JButton b1,b2,b3,b4,b7,b6,b8,b9,b5;
Image img;
JPanel p;
ImageIcon iaddc,iaddp,iupc,iupp,iabout,ilogout,iexit,ifbill,itable;

Menu()
{

setLayout(null);

iaddc=new ImageIcon(getClass().getResource("addcust.jpg"));
iaddp=new ImageIcon(getClass().getResource("addp.jpg"));
iupc=new ImageIcon(getClass().getResource("updatecust.jpg"));
iupp=new ImageIcon(getClass().getResource("upplan.jpg"));
iabout=new ImageIcon(getClass().getResource("about.jpg"));
ilogout=new ImageIcon(getClass().getResource("logout.jpg"));
iexit=new ImageIcon(getClass().getResource("exit.jpg"));

ifbill=new ImageIcon(getClass().getResource("finalbill.jpg"));
itable=new ImageIcon(getClass().getResource("register.jpg"));



b1=new JButton("1",iaddc);//addcust
b1.addActionListener(this);
b1.setSize(270,148);b1.setLocation(50,50); add(b1);

b5=new JButton("9",itable);//register
b5.addActionListener(this);
b5.setSize(270,148); b5.setLocation(650,50); add(b5);


b2=new JButton("2",iupc);//updatecustomer
b2.addActionListener(this);
b2.setSize(270,148); b2.setLocation(350,50); add(b2);

b3=new JButton("3",iaddp);//addplan
b3.addActionListener(this);
b3.setSize(270,148); b3.setLocation(50,250);add(b3);

b4=new JButton("4",iupp);//updateplan
b4.addActionListener(this);
b4.setSize(270,148); b4.setLocation(350,250); add(b4);

b9=new JButton("8",ifbill);//finalbill
b9.addActionListener(this);
b9.setSize(270,148); b9.setLocation(650,250); add(b9);

b7=new JButton("5",ilogout);//logout
b7.addActionListener(this);
b7.setSize(270,148); b7.setLocation(50,450); add(b7);

b6=new JButton("6",iabout);//about
b6.addActionListener(this);
b6.setSize(270,148); b6.setLocation(350,450); add(b6);

b7=new JButton("7",iexit);//exit
b7.addActionListener(this);
b7.setSize(270,148); b7.setLocation(650,450); add(b7);

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


setTitle("Menu");
setSize(1224,765);
setVisible(true);
setLocation(100,100);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//new Popup();

}

public void actionPerformed(ActionEvent ae)
{
	String act=ae.getActionCommand();
	try
	{
		if(act.equals("1"))//addcust
		{
			new AddCust();
		}
		else if(act.equals("2"))//updatecust
		{
			new CustUpdate();
		}
		else if(act.equals("3"))//addplan
		{
			new AddPlan();
		}
		else if(act.equals("4"))//upplan
		{
			new planupdate();
		}

		else if(act.equals("7"))//exit
                {
                        System.exit(0);
                }

		else if(act.equals("5"))//logout
                {
                        dispose();
                        new Login();
                }

		 else if(act.equals("8"))
                {
                        dispose();
                        new FinalBill();
                }
		 else if(act.equals("9"))
                {
                        dispose();
                        new Register();
                }
	

		else if(act.equals("6"))//about
                {
JOptionPane.showMessageDialog(null,"Networks Pvt Ltd\nDevelopers \nOmkar Rao\nSandeep Trimbake\n Rohan Salunke \n Version   1.0","About",JOptionPane.INFORMATION_MESSAGE);

 
             }


}
catch (Exception e)
{
System.out.println(e);
}
}
public static void main(String args[])  
{
Menu m=new Menu();
}
}

















