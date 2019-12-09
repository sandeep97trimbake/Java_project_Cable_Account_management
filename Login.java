import java.io.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;



class Login extends JFrame  implements ActionListener{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	JPasswordField tpass;
	JPanel p;
	Image img;
	ImageIcon ilog,ifpass,ires;
	JLabel llog,lpass,lname;
	JTextField tlog;
	JButton blog,bforgot,breset;

	Login()
	{

		setLayout(null);




		ilog=new ImageIcon(getClass().getResource("login.jpg"));
		ifpass=new ImageIcon(getClass().getResource("fpass.jpg"));
		ires=new ImageIcon(getClass().getResource("reset.jpg"));

		llog=new JLabel("USERNAME : "); 
		llog.setSize(100,30); llog.setLocation(260,200); add(llog);
		lpass=new JLabel("PASSWIRD : ");
		lpass.setSize(100,30); lpass.setLocation(260,250); add(lpass);
		tlog=new JTextField("");
		tlog.setSize(200,30); tlog.setLocation(350,200); 
tlog.addKeyListener(new KeyAdapter()
{
public void keyTyped(KeyEvent ke)
{
char ch=ke.getKeyChar();
if(!Character.isLetter(ch) && ch!='\b')
{
ke.setKeyChar('\b');
return;
}
}
});
add(tlog);
		tpass=new JPasswordField("");
		tpass.setSize(200,30); tpass.setLocation(350,250); add(tpass);

		blog=new JButton("1",ilog);//login
		blog.addActionListener(this);
		blog.setSize(170,47); blog.setLocation(280,330); add(blog);

		bforgot=new JButton("2",ifpass);//forget passwrd
		bforgot.addActionListener(this);
		bforgot.setSize(170,47); bforgot.setLocation(400,400); add(bforgot);

		breset=new JButton("3",ires);//reset
		breset.addActionListener(this);
		breset.setSize(170,47); breset.setLocation(500,330); add(breset);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1224,765);
		setVisible(true);

	
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


		try 
		{
			Class.forName("org.postgresql.Driver");

			con=DriverManager.getConnection("jdbc:postgresql://localhost/cms","postgres","redhat");

			if(con==null)
			{
				System.out.println("connection faild");
				System.exit(0); 
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		String act=ae.getActionCommand();
		try
		{
                       
if(act.equals("2"))
{
new ForgotPass();
}

			if(act.equals("1"))
			{
				String txtUser=tlog.getText();
				String txtPass=tpass.getText();

				ps=con.prepareStatement("select username,password from login1");
				rs=ps.executeQuery();
				while(rs.next())
				{
					String dbUser=rs.getString(1);
					String dbPass=rs.getString(2);


					if(dbUser.equals(txtUser) && dbPass.equals(txtPass))
					{


						
						
						
new Menu();

dispose();

				
	}
else{
JOptionPane.showMessageDialog(null,"Login Failed");
}
				}
			}}
			catch(Exception e)
			{
				System.out.println(e);
			}

			if(act.equals("3"))
			{
				tlog.setText("");
				tpass.setText("");
			}
}


	public static void main(String args[])
	{
		Login l=new Login();
	}
}

