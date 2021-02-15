import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame
{
	Container c;
	TextArea txtView;
	JButton btnBack;
	ViewFrame()
	{
		c=getContentPane();
		setLayout(new FlowLayout());
		btnBack =new JButton("BACK");
		txtView = new TextArea(13,30);
		c.add(txtView);
		c.add(btnBack);
	
		setTitle("VIEW");
		setSize(300,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DbHandler db=new DbHandler();
		String s=db.viewEmployee();
		txtView.setText(s);
		txtView.setEditable(false);
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
		
	
	}
	
	
	
}
