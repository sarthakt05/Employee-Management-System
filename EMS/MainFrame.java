import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.*;
import java.sql.*;
import java.util.*;

class SimpleAudioPlayer
{
	public SimpleAudioPlayer(String filepath) throws UnsupportedAudioFileException, 
        IOException, LineUnavailableException  
    { 
		
        // create AudioInputStream object
        AudioInputStream audioInputStream =  
                AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
          
        // create clip reference 
        Clip clip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
          
        
		clip.start();	
    } 
}

class MainFrame extends JFrame {
	Container c;
	JButton btnAdd, btnView, btnUpdate, btnDelete, btnExit;

	public static void main(String args[]) {
		MainFrame mf = new MainFrame();

	}

	MainFrame() {
		c = getContentPane();
		setLayout(new FlowLayout());
		setSize(300, 300);

		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnExit = new JButton("Exit");

		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		c.add(btnExit);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("E.M.S.");
		setLocationRelativeTo(null);
		setVisible(true);

		ActionListener a1 = (ae) -> {
			try {
				SimpleAudioPlayer so = new SimpleAudioPlayer("addEmp.wav");
			} catch (Exception e) {
				System.out.println("Issue with playing sound");
				e.printStackTrace();
			}

			AddFrame af = new AddFrame();
			dispose();

		};
		btnAdd.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			try {
				SimpleAudioPlayer so = new SimpleAudioPlayer("viewEmp.wav");
			} catch (Exception e) {
				System.out.println("Issue with playing sound");
				e.printStackTrace();
			}

			ViewFrame vf = new ViewFrame();
			dispose();

		};
		btnView.addActionListener(a2);

		ActionListener a3 = (ae) -> {
			try {
				SimpleAudioPlayer so = new SimpleAudioPlayer("updEmp.wav");
			} catch (Exception e) {
				System.out.println("Issue with playing sound");
				e.printStackTrace();
			}
			UpdateFrame uf = new UpdateFrame();
			dispose();

		};
		btnUpdate.addActionListener(a3);

		ActionListener a4 = (ae) -> {
			try {
				SimpleAudioPlayer so = new SimpleAudioPlayer("delEmp.wav");
			} catch (Exception e) {
				System.out.println("Issue with playing sound");
				e.printStackTrace();
			}
			DeleteFrame af = new DeleteFrame();
			dispose();

		};
		btnDelete.addActionListener(a4);

		ActionListener a5 = (ae) -> {
			int dialogbtn = JOptionPane.showConfirmDialog(new JDialog(), "Are you Sure you want to  Exit?", "Exit",
					JOptionPane.YES_NO_OPTION);
			if (dialogbtn == JOptionPane.YES_OPTION) {
				try {
					SimpleAudioPlayer so = new SimpleAudioPlayer("Bye.wav");
				} catch (Exception e) {
					System.out.println("Issue with playing sound");
					e.printStackTrace();
				}
				dispose();

			}

		};
		btnExit.addActionListener(a5);
	}

}

class Validation
{
	public static void main(String args[])
	{
		MainFrame l=new MainFrame();
		
	}
	public boolean isInteger(String n)
	{
		try{
			int m=Integer.parseInt(n);
			
		}
		catch(NumberFormatException nfe){
			return false;
			
		}
		return true;
	}
	public boolean isName(String s)
	{
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='0'||s.charAt(i)=='1'||s.charAt(i)=='2'||s.charAt(i)=='3'||
			s.charAt(i)=='4'||s.charAt(i)=='5'||s.charAt(i)=='6'||s.charAt(i)=='7'
			||s.charAt(i)=='8'||s.charAt(i)=='9'
			)
			{
				return false;
			}	
			
		}	
		return true;
		
	}
	
}

class DbHandler
{
	public void addEmployee(Employee s)
	{
		Connection con=null;
		
		try
		{	
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			
					
			String sql="insert into employee values(?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,s.getId());
			pst.setString(2,s.getName());
			pst.setDouble(3,s.getSalary());
			int dialogbtn=JOptionPane.showConfirmDialog(new JDialog(),"Are you Sure you want to insert record","INSERT RECORD",JOptionPane.YES_NO_OPTION);
			if(dialogbtn==JOptionPane.YES_OPTION)
			{
				AddFrame af =new AddFrame();
			}	
			int result=pst.executeUpdate();
			System.out.println(result+ " records inserted ");
			
			JOptionPane.showMessageDialog(new JDialog(),result+ " records inserted ");
			pst.close();
					
					
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
		
				}	
				System.out.println("connection closed");
				
					
			}
			catch(SQLException exx)
			{
				System.out.println("Erro occured "+exx);
				JOptionPane.showMessageDialog(new JDialog(),"Error occured "+exx);	
			}
		}
		
		
	
		
	}
	
	
	public String viewEmployee()
	{
		Connection con=null;
		StringBuffer sb=new StringBuffer();
		try
		{
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			Statement stmt=con.createStatement();
					
					
			String sql="select * from employee";
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next())
			{
				int id=rs.getInt(1);
				String name=rs.getString(2);
				int salary=rs.getInt(3);
				System.out.println("ID  : "+id+"  NAME : "+name+"  SALARY = "+salary);
				sb.append("ID : "+id+"  NAME : "+name+"   SALARY = "+salary+"\n");
			}	
			
		
			rs.close();
			stmt.close();
			
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
			
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
						
				}	
				System.out.println("connection closed");
				
			
			}
			catch(SQLException e)
			{
				System.out.println("ISSUE "+e);
				JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
			}
					
		}
				
		return sb.toString();
	}

	public void updateEmployee(Employee s)
	{
		Connection con=null;
		
		try
		{	
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			
					
			String sql="update employee  set name=?, salary=? where id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(3,s.getId());
			pst.setString(1,s.getName());
			pst.setDouble(2,s.getSalary());
			int result=pst.executeUpdate();
			int dialogbtn=JOptionPane.showConfirmDialog(new JDialog(),"Are you Sure you want to update record","UPDATE RECORD",JOptionPane.YES_NO_OPTION);
			if(dialogbtn==JOptionPane.YES_OPTION)
			{
				UpdateFrame uf =new UpdateFrame();
			}	
			System.out.println(result+ " records inserted ");
			JOptionPane.showMessageDialog(new JDialog(),result+ " records updated ");
			pst.close();
					
					
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
		
				}	
				System.out.println("connection closed");
				
					
			}
			catch(SQLException e)
			{
				System.out.println("ISSUE "+e);
				JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);	
			}
		}
	}
	public void deleteEmployee(Employee s)
	{
		Connection con=null;
		
		try
		{	
			System.out.println("Loading the driver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			System.out.println("Driver loaded");
					
					
			System.out.println("Trying to connect");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
			System.out.println("connected");
					
			
					
			String sql="delete from employee where id=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,s.getId());
			
			int result=pst.executeUpdate();
			int dialogbtn=JOptionPane.showConfirmDialog(new JDialog(),"Are you Sure you want to delete record","DELETE RECORD",JOptionPane.YES_NO_OPTION);
			if(dialogbtn==JOptionPane.YES_OPTION)
			{
				DeleteFrame df =new DeleteFrame();
			}	
			System.out.println(result+ " records deleted");
			JOptionPane.showMessageDialog(new JDialog(),result+ " records deleted ");
			pst.close();
					
					
		}
		catch(SQLException e)
		{
			System.out.println("ISSUE "+e);
			JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);
		}
		finally
		{
			try
			{
				System.out.println("Trying to close the connection");
				if(con!=null)
				{
					con.close();
		
				}	
				System.out.println("connection closed");
				
					
			}
			catch(SQLException e)
			{
				System.out.println("ISSUE "+e);
				JOptionPane.showMessageDialog(new JDialog(),"ISSUE "+e);	
			}
		}
	}
	
}