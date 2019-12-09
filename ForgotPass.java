import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class ForgotPass extends JFrame implements ActionListener{
	JLabel lmkey;
	JTextField ltf;
	JButton lsubmit;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String dbUser,dbPass;
	ForgotPass()
	{
		setLayout(new FlowLayout());
		lmkey=new JLabel("Enter your master key");
		add(lmkey);
		ltf=new JTextField(20);
		add(ltf);
		lsubmit=new JButton("Submit");
		lsubmit.addActionListener(this);
		add(lsubmit);
		setVisible(true);
		setTitle("Forgot Password");
		setSize(300,300);
		setLocation(280,170);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	
	try	{
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

}
	public void actionPerformed(ActionEvent ae)
	{
		String str=ltf.getText();
		if(str.equals("OSRTECH"))
		{
try{
			ps=con.prepareStatement("select username,password from login1");
			rs=ps.executeQuery();
			while(rs.next())
			{
				dbUser=rs.getString(1);
				dbPass=rs.getString(2);
			}

			JOptionPane.showMessageDialog(null,"Username  "+dbUser+"\nPassword  "+dbPass);
}
catch(Exception e)
{
}
		}
		else{
			JOptionPane.showMessageDialog(null,"MASTER KEY INVALID ");

		}
	}

	public static void main(String args[])
	{
		ForgotPass p=new ForgotPass();

	}

}
