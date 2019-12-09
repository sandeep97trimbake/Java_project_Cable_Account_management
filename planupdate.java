import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.sql.*;
import java.util.Date;




class planupdate extends JFrame implements ActionListener{
	JLabel lpid,lpnamel,lpnamer,lamt,date,ptype,ptype1;
	JTextField tpid,tamt;
	JButton bupdate,bdelete,bback,bsearch;
	Image img;
	JPanel p;
	Connection con;
	ResultSet rs,rs1;
	Statement st;
	PreparedStatement ps,ps2;
	String pid;
	ImageIcon iback,iup,idelete,ipsear;
	planupdate()
	{

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

		setLayout(null);



                iback=new ImageIcon(getClass().getResource("back.jpg"));
                iup=new ImageIcon(getClass().getResource("update.jpg"));
                iback=new ImageIcon(getClass().getResource("back.jpg"));
                idelete=new ImageIcon(getClass().getResource("delete.jpg"));
                ipsear=new ImageIcon(getClass().getResource("psearch.jpg"));


		Date d=new Date();

		date=new JLabel("DATE :"+d);
		date.setSize(250,30); date.setLocation(550,600); add(date);





		lpid=new JLabel("PLAN ID :");
		lpid.setSize(100,30);
		lpid.setLocation(290,200);
		add(lpid);

		tpid=new JTextField("");
		tpid.setSize(150,40);
		tpid.setLocation(400,200);
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


		bsearch=new JButton("1",ipsear);//search
		bsearch.setSize(170,47);
		bsearch.setLocation(600,200);
		add(bsearch);
		bsearch.addActionListener(this);

		

		ptype=new JLabel("PLAN  TYPE :");
                ptype.setSize(100,30);
                ptype.setLocation(320,280);
                add(ptype);

		ptype1=new JLabel("");
                ptype1.setSize(100,30);
                ptype1.setLocation(450,280);
                add(ptype1);


		lpnamel=new JLabel("PLAN  NAME :");
		lpnamel.setSize(100,30);
		lpnamel.setLocation(320,330);
		add(lpnamel);

		lpnamer=new JLabel("");
		lpnamer.setSize(100,30);
		lpnamer.setLocation(450,330);
		add(lpnamer);


		lamt=new JLabel("AMOUNT :");
		lamt.setSize(100,30);
		lamt.setLocation(320,400);
		add(lamt);


		tamt=new JTextField("");
		tamt.setSize(40,40);
		tamt.setLocation(450,400);
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

		bupdate=new JButton("2",iup);//update
		bupdate.setSize(170,47);
		bupdate.setLocation(200,500);
		add(bupdate);
		bupdate.addActionListener(this);

		bdelete=new JButton("3",idelete);//delete
		bdelete.setSize(170,47);
		bdelete.setLocation(400,500);
		add(bdelete);
		bdelete.addActionListener(this);
		
		bback=new JButton("4",iback);//back
		bback.addActionListener(this);
		bback.setSize(170,47);
		bback.setLocation(600,500);
		add(bback);

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


		setTitle("Update plan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1224,765);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String ac=ae.getActionCommand();
		if(ac.equals("4"))//back
		{
			dispose();
			

		}
		if(ac.equals("1"))//search
		{
			
			try{
				st=con.createStatement();
				

				rs1=st.executeQuery("SELECT * FROM plan where pid="+tpid.getText());
		
			
				if(rs1.next())
				{ 
					String name=rs1.getString(2);
					String amount=rs1.getString(5);
					String ptype=rs1.getString(4);
					lpnamer.setText(name);
					tamt.setText(amount);
					ptype1.setText(ptype);
					
				
				}

				else
				{

			JOptionPane.showMessageDialog(null,"NO RESULT FOUND");	


				}

		
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	if(ac.equals("2"))//update
{

try{
ps=con.prepareStatement("UPDATE plan SET amount=? WHERE pid=?");
ps.setString(1,tamt.getText());
ps.setString(2,tpid.getText());
ps.executeUpdate();
JOptionPane.showMessageDialog(null,"Updated!");
}
catch(Exception e)
{
System.out.println(e);
}
}

        if(ac.equals("3"))//delete
{

try{
 ps2=con.prepareStatement("Delete from subs where pid="+tpid.getText());
 ps2.executeUpdate();
 ps2=con.prepareStatement("Delete from plan where pid="+tpid.getText());
 ps2.executeUpdate();
 JOptionPane.showMessageDialog(null,"Deleted!");


}
catch(Exception e)
{
System.out.println(e);
}
}




	}
	public static void main(String args[])
	{
		planupdate pu=new planupdate();
	}
}









