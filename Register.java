import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.imageio.*;
import java.io.*;

class Register extends JFrame{
static Connection con;
static PreparedStatement ps;
static ResultSet rs;
Vector rows;
Vector cols;
Vector row;
JTable t;
JScrollPane jsp;
Image img;
JPanel p;

Register()
{
try{
    

Class.forName("org.postgresql.Driver");
 con=DriverManager.getConnection("jdbc:postgresql://localhost/cms","postgres","redhat");
ps=con.prepareStatement("SELECT * FROM subbill");
 rs=ps.executeQuery();
row=new Vector();
while(rs.next())
{

rows=new Vector();
rows.add(rs.getString(1));
rows.add(rs.getString(2));
rows.add(rs.getString(3));
//rows.add(rs.getString(4));
rows.add(rs.getString(4));
rows.add(rs.getString(5));
rows.add(rs.getString(6));
row.add(rows);

}
cols=new Vector();
cols.add("CUSTOMER ID");
cols.add(" NAME");
cols.add("PHON No.");
//cols.add("PLAN");
cols.add("START DATE");
cols.add("END DATE");
cols.add("AMOUNT");


t=new JTable(row,cols);
jsp=new JScrollPane(t);


add(jsp);
setTitle("Register");
setVisible(true);
setSize(1224,765);
setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
catch(Exception e)
{

System.out.println(e);
}
}
public static void main(String arf[])
{
new Register();
}
}



