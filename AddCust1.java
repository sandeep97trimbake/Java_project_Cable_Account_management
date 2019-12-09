//ADD CUSTOMER - under devlopment
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
class AddCust extends JFrame implements ItemListener, ActionListener{
	JLabel notice,notice1,custid,name,addrs,phno,email_id,plans,addpacks,cost;
	JTextField tcustid,tname,taddrs,tphno,temail_id,tplans;
	JButton back,save,reset;
	JComboBox bjcb;
	ImageIcon isub;
	JPanel p;
	Image img;
	String base[]={" "};
	Vector  selected=new Vector();
	JCheckBox cb1,cb2,cb3;
	int mcost=600;
	String str;
	Connection con;
	PreparedStatement ps,ps1;
	ResultSet rs,rs1;
	LinkedList ls=new LinkedList();
	AddCust()
	{
		setTitle("Add Customer");
		setLayout(null);


		try
		{

			Class.forName("org.postgresql.Driver");

			con=DriverManager.getConnection("jdbc:postgresql://localhost/cms","postgres","redhat");

			if(con==null)
			{
				System.out.println("connection faild");
				System.exit(0);
			}
			int n=0;
			ps=con.prepareStatement("SELECT COUNT(*) FROM plan where ptype='Base Pack'");
			rs=ps.executeQuery();
			while(rs.next())
			{
				n=Integer.parseInt(rs.getString(1));
			}
			base=new String[n+1];
			ps1=con.prepareStatement("SELECT name from plan where ptype='Base Pack'");
			rs1=ps1.executeQuery();	    
			int j=1;
			while(rs1.next())
			{
				base[j]=rs1.getString(1);
				j++;
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"--"+e);
		}






		custid=new JLabel("Customer Id : ");
		custid.setSize(100,30); custid.setLocation(20,20); add(custid);
		name=new JLabel("Name : ");
		name.setSize(100,30); name.setLocation(20,70); add(name);
		addrs=new JLabel("Address : ");
		addrs.setSize(100,30); addrs.setLocation(20,130); add(addrs);
		phno=new JLabel("Phone No  : ");
		phno.setSize(100,30); phno.setLocation(20,180); add(phno);
		email_id=new JLabel("Email Id : ");
		email_id.setSize(100,30); email_id.setLocation(20,230); add(email_id);
		plans=new JLabel("Plans : ");
		plans.setSize(100,30); plans.setLocation(20,270); add(plans);


		bjcb=new JComboBox(base);
		bjcb.setSize(100,30); 
		bjcb.setLocation(150,270); add(bjcb);
		isub=new ImageIcon(getClass().getResource("submit.jpg"));

		bjcb.addItemListener(this);

		notice=new JLabel(base[0]);
		notice.setSize(100,30);
		notice.setLocation(300,270);
		add(notice);

		cost=new JLabel("Monthly Cost       "+ mcost);
		cost.setSize(200,30);
		cost.setLocation(20,370);
		add(cost);

		tcustid=new JTextField("");
		tcustid.setSize(200,30); tcustid.setLocation(140,20);
		tcustid.addKeyListener(new KeyAdapter()
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


		add(tcustid);
		tname=new JTextField("");
		tname.setSize(200,30); tname.setLocation(140,70); 


		tname.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				//String strv=(String)ke.getKeyChar();
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


		add(tname);
		taddrs=new JTextField("");
		taddrs.setSize(200,30); taddrs.setLocation(140,130); add(taddrs);
		tphno=new JTextField("");
		tphno.setSize(200,30); tphno.setLocation(140,180); 


		tphno.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				if(!Character.isDigit(ch) && ch!='\b')
				{
				ke.setKeyChar('\b');
				return;
				}
				String s=tphno.getText();
				int n=s.length();
				if(ch!='\b')
				{
				n++;
				if(n>10)
				ke.setKeyChar('\b');
				}
				}
				});

		add(tphno);
		temail_id=new JTextField("");
		temail_id.setSize(200,30); temail_id.setLocation(140,230); add(temail_id);


		save=new JButton("Save",isub);
		save.addActionListener(this);
		save.setSize(150,50); save.setLocation(20,420); add(save);
		reset=new JButton("Reset");


		back=new JButton("Back");
		back.addActionListener(this);
		back.setSize(160,50); back.setLocation(200,420); add(back);
		reset.addActionListener(this);
		reset.setSize(150,50); reset.setLocation(400,420); add(reset);
		notice1=new JLabel("Note : In case there`s a change, Please hit the Reset Button and Start Over");
		notice1.setSize(1024,30); notice1.setLocation(20,500); add(notice1);

		addpacks=new JLabel("AddOns");
		addpacks.setSize(200,30); addpacks.setLocation(20,320);
		add(addpacks);
		cb1=new JCheckBox("Sports");
		cb1.setSize(100,30); cb1.setLocation(140,320); add(cb1);
		cb1.addItemListener(this);
		cb2=new JCheckBox("LifeStyle");
		cb2.setSize(100,30); cb2.setLocation(250,320); add(cb2);
		cb2.addItemListener(this);
		cb3=new JCheckBox("Kids");
		cb3.setSize(100,30); cb3.setLocation(360,320); add(cb3);

		cb3.addItemListener(this);

		p=new JPanel()
		{
			public void paint(Graphics g)
			{

				try
				{
					File f=new File("a.jpg");
					img=ImageIO.read(f);
					g.drawImage(img,0,0,1366,768,this);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}

		};


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,768);
		setVisible(true);
	}
	public void itemStateChanged(ItemEvent ae)
	{
		
		try
		{
			str=(String)bjcb.getSelectedItem();
		}
		catch(Exception e)
		{
			return;
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!"+str);		
		if(str.equals("Regular Pack "))
		{
			notice.setText(str);

			mcost=100;
				


			selected.add("103");	
		}
		if(str.equals("Gold Pack "))
		{
			notice.setText(str);
			selected.add("101");    

			mcost=500;

		}
		if(str.equals("Silver Pack "))
		{
			notice.setText(str);

			mcost=200;
			selected.add("102");    


		}

		if(cb1.isSelected())
		{
			mcost=mcost+50;
			selected.add("201");


		}
		if(cb2.isSelected())
		{
			mcost=mcost+50;
			selected.add("202");


		}
		if(cb3.isSelected())
		{
			mcost=mcost+50;
			selected.add("203");


		}
		cost.setText("Monthly Cost      "+mcost);


		notice.setText(str);
		ListIterator lst=selected.listIterator();
		while(lst.hasNext())
		{
			String sel=(String)lst.next();
			if(ls.contains(sel))
			{
			}
			else
			{
				ls.add(sel);
			}
		}
		ListIterator lst1=ls.listIterator();
		System.out.println("SELECT PACKS - ");
		while(lst1.hasNext())
		{
			System.out.println(lst1.next());
		}
		//System.out.println("PACK!!!!!!!!!!!!"+selected);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String str=ae.getActionCommand();

		if(str.equals("Save"))

		{
			try
			{
				ps=con.prepareStatement("insert into cust values(?,?,?,?,?)");
				ps.setString(1,(tcustid.getText()));
				ps.setString(2,(tname.getText()));
				ps.setString(3,(tphno.getText()));
				ps.setString(4,(taddrs.getText()));
				ps.setString(5,(temail_id.getText()));
				ps.executeUpdate();
				ListIterator lst1=ls.listIterator();
				PreparedStatement ps1;
				System.out.println("Subs entries");
				while(lst1.hasNext())
				{
					//S//ystem.out.println("Subs entries");

					String stri=(String)lst1.next();
					System.out.println(stri);
					ps1=con.prepareStatement("insert into subs values(?,?)");
					ps1.setString(1,stri);
					ps1.setString(2,tcustid.getText());

					ps1.executeUpdate();

				}
				JOptionPane.showMessageDialog(null,"Saved Succesfully..!");
				new FinalBill(tcustid.getText(),tname.getText(),tphno.getText(),notice.getText(),taddrs.getText(),mcost);
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);


			}
		}
		if(str.equals("Back"))
		{
			dispose();
		}
		if(str.equals("Reset"))	
		{
			dispose();
			new AddCust();
		}
	}
	public static void main(String args[])
	{
		AddCust ad=new AddCust();
	}
}
