import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
import java.util.Date;


class FinalBill extends JFrame implements ActionListener{ 
JLabel lcid,lcname,lphno,lsplan,laddr,lsdate,ledate,lamt;
JLabel lcid1,lcid2,lcname1,lphno1,lsplan1,laddr1,lsdate1,lamt1,date;
JTextField tcid,tedate1;
JButton bprint,bsearch,bsave;
ImageIcon isave,iprint,icsear;
JPanel p;
Image img;
Connection con;
Statement st,st1;
PreparedStatement ps,ps1;
ResultSet rs,rs1;
FinalBill()
{
setLayout(null);

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
			JOptionPane.showMessageDialog(null,"--"+e);
			return;
		}











isave=new ImageIcon(getClass().getResource("save.jpg"));
iprint=new ImageIcon(getClass().getResource("print.jpg"));
icsear=new ImageIcon(getClass().getResource("csearch.jpg"));

lcid=new JLabel("CUSTOMER ID:");
lcid.setSize(100,30);
lcid.setLocation(220,20);
add(lcid);

tcid=new JTextField("");
tcid.setSize(200,30);
tcid.setLocation(320,20);
add(tcid);


bsearch=new JButton("1",icsear);//search
bsearch.setSize(170,47);
bsearch.setLocation(550,20);
add(bsearch);
bsearch.addActionListener(this);

JLabel split=new JLabel("-------------------------------------------------------------------------DETAILS-------------------------------------------------------------------------------");
split.setSize(1024,20);
split.setLocation(0,70);
add(split);

lcid1=new JLabel("CUSTOMER ID:");
lcid1.setSize(100,30);
lcid1.setLocation(240,100);
add(lcid1);

lcid2=new JLabel("");
lcid2.setSize(100,30);
lcid2.setLocation(400,100);
add(lcid2);


lcname=new JLabel("CUSTOMER NAME:");
lcname.setSize(200,30);
lcname.setLocation(240,150);
add(lcname);

lcname1=new JLabel("");
lcname1.setSize(200,30);
lcname1.setLocation(400,150);
add(lcname1);



lphno=new JLabel("PHONE No.:");
lphno.setSize(100,30);
lphno.setLocation(240,200);
add(lphno);

lphno1=new JLabel("");
lphno1.setSize(100,30);
lphno1.setLocation(360,200);
add(lphno1);


lsplan=new JLabel("PLAN:");
lsplan.setSize(100,30);
lsplan.setLocation(240,250);
add(lsplan);

lsplan1=new JLabel("HD vala plan");
lsplan1.setSize(100,30);
lsplan1.setLocation(360,250);
add(lsplan1);


laddr=new JLabel("ADDRESS:");
laddr.setSize(100,30);
laddr.setLocation(240,300);
add(laddr);

laddr1=new JLabel("");
laddr1.setSize(300,30);
laddr1.setLocation(360,300);
add(laddr1);


lsdate=new JLabel("START DATE:");
lsdate.setSize(100,30);
lsdate.setLocation(240,350);
add(lsdate);

lsdate1=new JLabel("");
lsdate1.setSize(250,30);
lsdate1.setLocation(360,350);
add(lsdate1);


ledate=new JLabel("END DATE:");
ledate.setSize(100,30);
ledate.setLocation(240,400);
add(ledate);

tedate1=new JTextField("");
tedate1.setSize(100,30);
tedate1.setLocation(360,400);


			tedate1.addKeyListener(new KeyAdapter()
                                {
                                public void keyTyped(KeyEvent ke)
                                {
                                char ch=ke.getKeyChar();
                                if(!Character.isDigit(ch) && ch!='-' && ch!='\b')
                                {
                                ke.setKeyChar('\b');
                                return;
                                }
                                String s=tedate1.getText();
                                int n=s.length();
                                if(ch!='\b')
                                {
                                n++;
                                if(n>10)
                                ke.setKeyChar('\b');
                                }
                                }
                                });











add(tedate1);


lamt=new JLabel("AMOUNT $:");
lamt.setSize(100,30);
lamt.setLocation(240,450);
add(lamt);

lamt1=new JLabel("");
lamt1.setSize(100,30);
lamt1.setLocation(360,450);
add(lamt1);


bsave=new JButton("2",isave);//save
bsave.setSize(170,47);
bsave.setLocation(180,500);
add(bsave);
bsave.addActionListener(this);

bprint=new JButton("3",iprint);//print
bprint.setSize(170,47);
bprint.setLocation(400,500);
add(bprint);
bprint.addActionListener(this);



Date d=new Date();

date=new JLabel("DATE :"+d);
date.setSize(250,30); date.setLocation(550,600); add(date);






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
                                        System.out.println("noim"+e);
                                }
                        }

                };

                p.setSize(1224,765);
                p.setLocation(0,0);
                add(p);





setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(1224,765);
setVisible(true);


}

  public void actionPerformed(ActionEvent ae)
        {
                String ac=ae.getActionCommand();
 


 if(ac.equals("1"))//search
                        {

                                try{
                                        st=con.createStatement();
                                        rs=st.executeQuery("SELECT * FROM cust where cid="+tcid.getText());
                                        while(rs.next())
                                        {
                                               String cid=rs.getString(1);
                                                String name=rs.getString(2);
                                                String addr=rs.getString(4);
                                                String phno=rs.getString(3);
       

                                               	lcid2.setText(cid);
                                                lcname1.setText(name);
                                                laddr1.setText(addr);
                                                lphno1.setText(phno);
                                                
                                        }
                                }
                                catch(Exception e)
                                {
                                        System.out.println (e);
                                }

			try{
				st1=con.createStatement();
				rs1=st1.executeQuery("select * from bill1 where bid="+tcid.getText());
				while(rs1.next())
			{
				String date=rs1.getString(2);
				String amt=rs1.getString(3);
				
				lsdate1.setText(date);
				lamt1.setText(amt);

			}
}
			catch(Exception e)
                                {
                                        System.out.println (e);
                                }

                        }

if(ac.equals("3"))//print
{
new Menu();
dispose();

}

if(ac.equals("2"))//save
		{
			String cid=lcid2.getText();
			String name=lcname1.getText();
			String ph=lphno1.getText();
			String plan=lsplan1.getText();
			String sdate=lsdate1.getText();
			String edate=tedate1.getText();
			String amt=lamt1.getText();
			try
			{
				ps1=con.prepareStatement("insert into subbill values(?,?,?,?,?,?,?)");
				ps1.setString(1,cid);
				ps1.setString(2,name);
				ps1.setString(3,ph);
				ps1.setString(4,plan);
				ps1.setString(5,sdate);
				ps1.setString(6,edate);
				ps1.setString(7,amt);



				int i=ps1.executeUpdate();
				if(i==0)
				{
					JOptionPane.showMessageDialog(null,"Unable to Insert record");
				}
				else{
					JOptionPane.showMessageDialog(null,"THANKS FOR SUBSCRIPION");
				}

			System.exit(0);
				

			}
		catch(Exception e)
			{
				System.out.println(e);
			}
					
			

}
}
public static void main(String args[])
{
FinalBill fb=new FinalBill();
}
}

