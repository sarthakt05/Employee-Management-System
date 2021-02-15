import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame
{
	Container c;
	JButton btnBack,btnDelete;
	JTextField txtId;
	JLabel lblId;
	DeleteFrame()
	{
		c=getContentPane();
		setLayout(new FlowLayout());
		btnDelete =new JButton("DELETE");
		btnBack =new JButton("BACK");
		txtId = new JTextField(15);
		lblId =new JLabel("Enter ID: ");
		c.add(lblId);
		c.add(txtId);
		c.add(btnBack);
		c.add(btnDelete);
		setTitle("DELETE EMP");
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		ActionListener a2=(ae) -> {
			String s1=txtId.getText();
			if(!s1.isEmpty())
			{
				if(v.isInteger(s1))
				{
				
				int id=Integer.parseInt(s1);
				if(id > 0)
				{
					Employee s=new Employee();
					s.setId(id);
					DbHandler db=new DbHandler();
					db.deleteEmployee(s);
					txtId.setText("");
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

				JOptionPane.showMessageDialog(new JDialog(),"ID has to be an integer");
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

			JOptionPane.showMessageDialog(new JDialog(),"ID cannot be empty");
				txtId.setText("");
				txtId.requestFocus();
		}	
		};
		btnDelete.addActionListener(a2);
		
	}

}