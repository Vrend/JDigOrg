import java.awt.Container;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class remA extends JFrame implements ActionListener
{

	Container c = getContentPane();
	
	JPanel top = new JPanel();
	
	JPanel bot = new JPanel();
	
	JButton yes = new JButton("Yes");
	
	JButton no = new JButton("No");
	
	JLabel lbl = new JLabel("Are you sure you want to delete ALL of your classes and assignments?");
	
	public remA()
	{
		super("Are you sure?");
		setSize(500, 125);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(top);
		add(bot);
		top.add(lbl);
		bot.add(yes);
		bot.add(no);
		
		c.add("North", top);
		c.add("South", bot);
		
		yes.addActionListener(this);
		no.addActionListener(this);
		setVisible(true);
	}
	
	
	public static void removeA()
	{
		remA guii = new remA();
	}
	
	
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource() == yes)
		{
			File classes = new File("classes.dat");
			File hw = new File("hw.dat");
			
			hw.setWritable(true);
			hw.delete();

			classes.setWritable(true);
			classes.delete();
			
			Digital_Organizer.dat1.setText("");
			Digital_Organizer.dat2.setText("");
			Digital_Organizer.dat3.setText("");
			Digital_Organizer.dat4.setText("");

			dispose();
		}
		
		if(event.getSource() == no)
		{
			dispose();
		}
		
	}

	
}
