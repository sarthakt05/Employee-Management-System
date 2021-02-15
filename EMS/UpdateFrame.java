import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



class UpdateFrame extends JFrame
{
	Container c;
	JButton btnSave,btnBack;
	JLabel lblId, lblName,lblSalary;
	JTextField txtName,txtId,txtSalary;
	
	
	UpdateFrame()
	{
		c=getContentPane();
		setLayout(new FlowLayout());
		lblName=new JLabel("Enter NAME: ");
		txtName=new JTextField(15);
		lblId=new JLabel("Enter ID: ");
		lblSalary=new JLabel("Enter SALARY: ");
		txtId=new JTextField(15);
		txtSalary=new JTextField(15);
		btnSave=new JButton("SAVE");
		btnBack=new JButton("BACK");
		c.add(lblId);
		c.add(txtId);
		c.add(lblName);
		c.add(txtName);
		c.add(lblSalary);
		c.add(txtSalary);
		c.add(btnSave);
		c.add(btnBack);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		setTitle("UPDATE");
		setLocationRelativeTo(null);
		setVisible(true);
		Validation v =new Validation();
		
		ActionListener a1=(ae) ->{
			try {
				SimpleAudioPlayer so = new SimpleAudioPlayer("back.wav");
			} catch (Exception e) {
				System.out.println("Issue with playing sound");
				e.printStackTrace();
			}
			MainFrame mf=new MainFrame();
			dispose();
		
		};
		btnBack.addActionListener(a1);
		
		ActionListener a2=(ae) ->{
			String r=txtId.getText();
			String name=txtName.getText();
			String m=txtSalary.getText();
			if(v.isInteger(r))
			{
				if(v.isInteger(m) )
				{
					int id=Integer.parseInt(r);
					double salary=Double.parseDouble(m);
					if(id > 0 )
						
					{
						if(v.isName(name) && !(name.equals("")))
						{
							if(salary>=8000 && salary>=0)
							{
								

								Employee s=new Employee(id,name,salary);
								DbHandler db=new DbHandler();
								db.updateEmployee(s);
								txtSalary.setText("");
								txtId.setText("");
								txtName.setText("");
								try {
									SimpleAudioPlayer so = new SimpleAudioPlayer("alert3.wav");
								} catch (Exception e) {
									System.out.println("Issue with playing sound");
									e.printStackTrace();
								}
								
							}
							else
							{
								try {
									SimpleAudioPlayer s = new SimpleAudioPlayer("alert.wav");
								} catch (Exception e) {
									System.out.println("Issue with playing sound");
									e.printStackTrace();
								}

								JOptionPane.showMessageDialog(new JDialog(),"Salary cannot be less than 8000 and less than 0");
								txtSalary.setText("");
								txtSalary.requestFocus();
	
							}
							
						}	
						else
						{
							try {
								SimpleAudioPlayer s = new SimpleAudioPlayer("alert.wav");
							} catch (Exception e) {
								System.out.println("Issue with playing sound");
								e.printStackTrace();
							}

							JOptionPane.showMessageDialog(new JDialog(),"Name cannot be empty or contain integers");
							txtName.setText("");
							txtName.requestFocus();
	
						}	
						
					}	
					else{
						try {
							SimpleAudioPlayer s = new SimpleAudioPlayer("alert.wav");
						} catch (Exception e) {
							System.out.println("Issue with playing sound");
							e.printStackTrace();
						}

						JOptionPane.showMessageDialog(new JDialog(),"ID cannot be negative");
						txtId.setText("");
						txtId.requestFocus();
	
					}
					
					
				}
				else
				{
					try {
						SimpleAudioPlayer s = new SimpleAudioPlayer("alert.wav");
					} catch (Exception e) {
						System.out.println("Issue with playing sound");
						e.printStackTrace();
					}

					JOptionPane.showMessageDialog(new JDialog(),"Salary have to be a Double");
					txtSalary.setText("");
					txtSalary.requestFocus();
				}
			
				
			}
			else{
				JOptionPane.showMessageDialog(new JDialog(),"ID has to be an integer");
				txtId.setText("");
				txtId.requestFocus();
			}
			
			
			
			
		};
		btnSave.addActionListener(a2);
		
	 
	
	
	}
}