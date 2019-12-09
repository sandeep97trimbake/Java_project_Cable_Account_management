import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import javax.swing.event.*;
import java.util.Date;

class CustUpdate extends JFrame implements ActionListener,ItemListener {
	JLabel lcid,lcid1,lcname,lphno,laddr,lplan,cost,notice,addpacks,date;
	JTextField tcid2,tcname1,tphno1,taddr1,tplan1;
	JTextField tcid;
	JButton bsearch,bupdate,bdelete,breset,bback,ytotal;
	JComboBox bjcb;
	JList ls;
	JScrollPane jp;
	String base[]={" "};
	Connection con;
	Statement st;
	PreparedStatement ps,ps1,ps2;
	ResultSet rs, rs1,rs2,rs3;
	String AddN[];
	String Addid[];
	String size;
	String cid;
	ImageIcon isub;
	JPanel p;
	ImageIcon iup,ires,idelete,iback,icsear;
	Image img;
	int mcost=0;
	int mcost2;
	int amt=0;
	Vector selected;
	LinkedList ls1;
	String str;
	PopCustUpdate popo;
	CustUpdate()
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

			ps=con.prepareStatement("SELECT count(*) FROM plan WHERE ptype='Add On'");
			rs2=ps.executeQuery();

			while(rs2.next())
			{		
				size=rs2.getString(1);
			}
			int sizei=Integer.parseInt(size);
			AddN=new String[sizei+1];
			Addid=new String[sizei+1];


			ps=con.prepareStatement("SELECT pid FROM plan WHERE ptype='Add On'");
			rs2=ps.executeQuery();
			int i=0;
			while(rs2.next())
			{
				Addid[i]=rs2.getString(1);
				i++;
			}

			ps=con.prepareStatement("SELECT name FROM plan WHERE ptype='Add On'");
			rs2=ps.executeQuery();
			i=0;
			while(rs2.next())
			{
				AddN[i]=rs2.getString(1);
				i++;
			}
		}

		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"--"+e);
			return;
		}


		setLayout(null);
		
		iup=new ImageIcon(getClass().getResource("update.jpg"));
                iback=new ImageIcon(getClass().getResource("back.jpg"));
                ires=new ImageIcon(getClass().getResource("reset.jpg"));
		idelete=new ImageIcon(getClass().getResource("delete.jpg"));
		icsear=new ImageIcon(getClass().getResource("csearch.jpg"));		
	
		lcid=new JLabel("CUSTOMER ID :");
		lcid.setSize(130,30);
		lcid.setLocation(240,20);
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


		bsearch=new JButton("1",icsear);//search
		bsearch.setSize(170,47);
		bsearch.setLocation(600,20);
		bsearch.addActionListener(this);
		add(bsearch);

		lcid1=new JLabel("CUSTOMER ID :");
		lcid1.setSize(130,30);
		lcid1.setLocation(240,100);
		add(lcid1);

		tcid2=new JTextField("");
		tcid2.setSize(100,30);
		tcid2.setLocation(400,100);
		tcid2.addKeyListener(new KeyAdapter()
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

		add(tcid2);


		lcname=new JLabel("CUSTOMER NAME :");
		lcname.setSize(200,30);
		lcname.setLocation(240,150);
		add(lcname);

		tcname1=new JTextField("");
		tcname1.setSize(200,30);
		tcname1.setLocation(400,150);
		tcname1.addKeyListener(new KeyAdapter()
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


		add(tcname1);



		lphno=new JLabel("PHONE No. :");
		lphno.setSize(100,30);
		lphno.setLocation(240,200);
		add(lphno);

		tphno1=new JTextField("");
		tphno1.setSize(100,30);
		tphno1.setLocation(400,200);
		tphno1.addKeyListener(new KeyAdapter()
				{
				public void keyTyped(KeyEvent ke)
				{
				char ch=ke.getKeyChar();
				if(!Character.isDigit(ch) && ch!='\b')
				{
				ke.setKeyChar('\b');
				return;
				}
				String s=tphno1.getText();
				int n=s.length();
				if(ch!='\b')
				{
				n++;
				if(n>10)
				ke.setKeyChar('\b');
				}
				}
				});

		add(tphno1);


		laddr=new JLabel("ADDRESS:");
		laddr.setSize(100,30);
		laddr.setLocation(240,250);
		add(laddr);

		taddr1=new JTextField("");
		taddr1.setSize(200,30);
		taddr1.setLocation(400,250);
		add(taddr1);

		lplan=new JLabel("PLAN :");
		lplan.setSize(100,30);
		lplan.setLocation(240,300);
		add(lplan);

		bjcb=new JComboBox(base);
		bjcb.setSize(100,30);
		bjcb.setLocation(400,300); add(bjcb);
		bjcb.addItemListener(this);

		notice=new JLabel(base[0]);
		notice.setSize(100,30);
		notice.setLocation(500,300);
		add(notice);


		notice=new JLabel("MONTHLY COST : $");
                notice.setSize(200,30);
                notice.setLocation(670,360);
                add(notice);

		ytotal=new JButton("YEARLY:$");//ytotal
                ytotal.setSize(100,20);
                ytotal.setLocation(670,410);
                add(ytotal);
		ytotal.addActionListener(this);


		cost=new JLabel(""+ amt);
		cost.setSize(200,30);
		cost.setLocation(870,370);
		add(cost);

		Date d=new Date();

		date=new JLabel("DATE :"+d);
		date.setSize(250,30); date.setLocation(550,600); add(date);



		addpacks=new JLabel("ADDON:");
		addpacks.setSize(200,30); addpacks.setLocation(240,370);
		add(addpacks);
		ls=new JList(AddN);
		ls.setSize(100,40); ls.setLocation(400,370);
		ListSelectionListener lsl=new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent lse){
				

				int disp[]=ls.getSelectedIndices();
				int mcost1=0;
				for(int i=0;i<disp.length;i++)
				{
				
					try{
						ps=con.prepareStatement("SELECT amount from plan where pid=?");
						ps.setString(1,Addid[disp[i]]);
						ResultSet rs =ps.executeQuery();
						rs.next();
						mcost1=Integer.parseInt(rs.getString(1))+mcost1;
					}
					catch(Exception ee)
					{
						System.out.println(ee);
					}

				}
				
				 mcost2=mcost1+mcost;
				
				String str=String.valueOf(mcost2);
				cost.setText(str);
			}

		};
		ls.addListSelectionListener(lsl);



		JScrollPane jp=new JScrollPane(ls);
		jp.setSize(200,60); jp.setLocation(400,370);add(jp);


		bupdate=new JButton("2",iup);//update
		bupdate.setSize(170,47);
		bupdate.setLocation(50,500);
		add(bupdate);
		bupdate.addActionListener(this);

		breset=new JButton("3",ires);//save
		breset.setSize(170,47);
		breset.setLocation(250,500);
		add(breset);
		breset.addActionListener(this);

		bdelete=new JButton("4",idelete);//delete
		bdelete.setSize(170,47);
		bdelete.setLocation(450,500);
		add(bdelete);
		bdelete.addActionListener(this);

		bback=new JButton("5",iback);//back
		bback.setSize(170,47);
		bback.setLocation(650,500);
		add(bback);
		bback.addActionListener(this);


		
		
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



		setTitle("Update Customer");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(1224,765);
		setLocation(0,0);
		setVisible(true);
	}





	public void itemStateChanged(ItemEvent ie)
	{
		try{
			if(ie.getStateChange()==ItemEvent.SELECTED)
			{

				ps=con.prepareStatement("select amount from plan where name=?");
				ps.setString(1,(String)bjcb.getSelectedItem());
				ResultSet rs3=ps.executeQuery();
				while(rs3.next())
				{
					cost.setText(rs3.getString(1));
					mcost=Integer.parseInt(rs3.getString(1));

				}
			}

		}
		catch(Exception e)
		{
			System.out.println("Exc"+e);
		}

	}


	public void actionPerformed(ActionEvent ae)
	{
		String ac=ae.getActionCommand();
		
		if(ac.equals("5"))//back
		{
			dispose();
			new Menu();

		}
			if(ac.equals("1"))//search
			{
				
				try{
					st=con.createStatement();
					rs=st.executeQuery("SELECT * FROM cust where cid="+tcid.getText());
					while(rs.next())
					{
						cid=rs.getString(1);
						String name=rs.getString(2);
						String addr=rs.getString(4);
						String phno=rs.getString(3);
						//String email=rs.getString(5);

						tcid2.setText(cid);
						tcname1.setText(name);
						taddr1.setText(addr);
						tphno1.setText(phno);
						popo=new PopCustUpdate(con,rs.getString(1));
					}
				}
				catch(Exception e)
				{
					System.out.println (e);
				}
			}


			
			 if(ac.equals("3"))//reset
                        {

			int n=0;
                        cost.setText(""+n);

				
			try{
                               tcid.setText("");
                                tcname1.setText("");
                                taddr1.setText("");
                                tphno1.setText("");
                                tcid2.setText("");
                                tplan1.setText("");
				cost.setText(""+n);
				
				}

			catch(Exception e)
			{		
        			System.out.println(""+e);
			}
                        }


		if(ac.equals("YEARLY:$"))//ytotal
		{

		 int mcost3;
		 int y=12;	
		 mcost3=y*mcost2;
                 String str1=String.valueOf(mcost3);
                 cost.setText(str1);
		int mcost4;
		mcost4=y*mcost;
		

		}






			if(ac.equals("2"))//update
			{
				try{
					ps2=con.prepareStatement("update cust set cname=?,phno=?,add=? where cid=?");
					ps2.setString(4,tcid.getText());                        	
					ps2.setString(1,tcname1.getText());
					ps2.setString(2,tphno1.getText());
					ps2.setString(3,taddr1.getText());
					int j=ps2.executeUpdate();
					
					if(j==0)
                                        {
                                               JOptionPane.showMessageDialog(null,"UNABLE TO UPADTE");
                                         }
                                        else{
                                               JOptionPane.showMessageDialog(null,"THIS RECORD UPDATED SUCCESFULLY");
                                        }
			                                 
					ps2=con.prepareStatement("delete from subs where cid=?");
					ps2.setString(1,tcid.getText());
					ps2.executeUpdate();

					
					ps=con.prepareStatement("Select pid from plan where name=?");
					ps.setString(1,(String)bjcb.getSelectedItem());
					rs3=ps.executeQuery();
					rs3.next();
					String baseid=rs3.getString(1);

					
					ps=con.prepareStatement("insert into subs values(?,?)");
					ps.setString(1,cid);
					ps.setString(2,baseid);
						
				

					int disp[]=ls.getSelectedIndices();
				      	int i;  	
					for( j=0;j<disp.length;j++)
					{
						
						PreparedStatement psad=ps=con.prepareStatement("insert into subs values(?,?)");
						psad.setString(1,cid);
						psad.setString(2,Addid[disp[j]]);
						i=psad.executeUpdate();


					}
					    
                                        


				popo.dispose();
			}
				
				catch(Exception e)
				{

					System.out.println(e);
				}
			}

			if(ac.equals("4"))//delete
			{
				String cid=tcid.getText();
				try{
					ps2=con.prepareStatement("Delete from subs where cid=?");
					ps2.setString(1,cid);	
					ps2.executeUpdate();
					ps2=con.prepareStatement("Delete from cust where cid=?");
					ps2.setString(1,cid);
					ps2.executeUpdate();
					JOptionPane.showMessageDialog(null,"Deleted!");
					popo.dispose();
				}
				catch(Exception e)
				{

					System.out.println(e);
				}

			}


		}


		public static void main(String args[]) throws Exception
		{
			CustUpdate u=new CustUpdate();
		}
	}







	class PopCustUpdate extends JFrame{
		JList jtb;
		ResultSet rs;
		PreparedStatement ps;
		PopCustUpdate(Connection con,String cid)
		{
			try{
		ps=con.prepareStatement("select plan.name from plan,cust,subs where cust.cid=subs.cid and plan.pid=subs.pid and cust.cid=? and ptype='Base Pack'");
				ps.setString(1,cid);
				rs=ps.executeQuery();
				Vector v=new Vector();
				v.add("Current Selected Packs:");
				v.add("------------");
				v.add("BASE PACK");
				while(rs.next())
				{
					String str=rs.getString(1);
					v.add("* "+str);

				}

				v.add("------------");

		ps=con.prepareStatement("select plan.name from plan,cust,subs where cust.cid=subs.cid and plan.pid=subs.pid and cust.cid=? and ptype='Add On'");
				ps.setString(1,cid);
				rs=ps.executeQuery();
				
				v.add("ADDOn PACKs");
				while(rs.next())
				{
					String str=rs.getString(1);
					v.add("* "+str);

				}

				v.add("------------");



				jtb=new JList(v);
				add(jtb);
				setVisible(true);
				setSize(250,250);
				setLocation(800,150);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
			catch(Exception e)
			{
				System.out.println(e);

			}

		}
		public static void main(String arg[])
		{
		}
	}






