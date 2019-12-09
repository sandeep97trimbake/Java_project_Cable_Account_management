import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.swing.event.*;


class GetRecords extends JFrame implements ActionListener{
Connection con;
JLabel lcid;
JTextField tcid;
JButton bsearch;

ImageIcon isub;
	JPanel p;
	Image img;
	PreparedStatement ps;
	ResultSet rs;
	GetRecords()
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
			JOptionPane.showMessageDialog(null,"--"+e);
			return;
		}

		setLayout(null);

		lcid=new JLabel("Customer Id :");
		lcid.setSize(100,30);
		lcid.setLocation(260,20);
		add(lcid);

		tcid=new JTextField(" ");
		tcid.setSize(200,30);
		tcid.setLocation(380,20);
		tcid.addKeyListener(new KeyAdapter()
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

		add(tcid);


		bsearch=new JButton("Search");
		bsearch.setSize(100,30);
		bsearch.setLocation(600,20);
		bsearch.addActionListener(this);
		add(bsearch);

setTitle("Update");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,768);
		//		setVisible(true);
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

		p.setSize(1366,768);
		p.setLocation(0,0);
		add(p);
		setVisible(true);
	}







public void actionPerformed(ActionEvent ae)
{
try{
                                        ps=con.prepareStatement("SELECT * FROM cust where cid="+tcid.getText());
rs=ps.executeQuery();
                                        while(rs.next())
                                        {
                                              String cid=rs.getString(1);
                                                String name=rs.getString(2);
                                                String addr=rs.getString(4);
                                                String phno=rs.getString(3);
                                                String email=rs.getString(5);
System.out.println(cid+"  "+name+"  "+addr+"  "+phno+"  "+email);
}
new tbl(con,tcid.getText());
}
catch(Exception e)
{
System.out.println(e);
}
}


public static void main(String args[])
{
new GetRecords();
}
}

class tbl extends JFrame{
JTable j;
tbl(Connection con,String cidt)
{
setLayout(new FlowLayout());
setSize(1024,768);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
Vector row=new Vector();
Vector col=new Vector();
Vector rows=new Vector();
col.add("Attribute");
col.add("Value");
try{
                                        PreparedStatement ps=con.prepareStatement("SELECT * FROM cust where cid="+cidt);
ResultSet rs=ps.executeQuery();
 rs.next();

                                        
                                              String cid=rs.getString(1);
                                                String name=rs.getString(2);
                                                String addr=rs.getString(4);
                                                String phno=rs.getString(3);
                                                String email=rs.getString(5);
row.add("Customer Id");
row.add(cid);
rows.add(row);
row.clear();
//row.removeElementAt(0);
//System.out.println(rows);
row.add("Customer Name");
row.add(name);
rows.add(row);


row.add("Address");
row.add(addr);


row.add("Phone Number");
row.add(phno);

row.add("Email");
row.add(email);

System.out.println("!!!!!!!!!"+rows);
System.out.println("!!!!!!!!!"+col);

//System.out.println(cid+"  "+name+"  "+addr+"  "+phno+"  "+email);
}

catch(Exception e)
{
System.out.println(e);
}
j=new JTable(rows,col);
j.setSize(1024,768);
add(j);
setLocation(20,300);
setVisible(true);
}
}


