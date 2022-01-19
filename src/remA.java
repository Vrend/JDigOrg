/*
    This file is part of JDigOrg.

    JDigOrg is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 2 of the License

    JDigOrg is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with JDigOrg.  If not, see <http://www.gnu.org/licenses/>.
*/
import java.awt.Container;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class remA extends JFrame implements ActionListener, KeyListener
{

	private JButton yes = new JButton("Yes");
	
	private JButton no = new JButton("No");

	private remA()
	{
		super("Are you sure?");
		setSize(500, 125);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel top = new JPanel();
		add(top);
		JPanel bot = new JPanel();
		add(bot);
		JLabel lbl = new JLabel("Are you sure you want to delete ALL of your classes and assignments?");
		top.add(lbl);
		bot.add(yes);
		bot.add(no);

		Container c = getContentPane();
		c.add("North", top);
		c.add("South", bot);
		
		yes.addActionListener(this);
		no.addActionListener(this);
		
		yes.addKeyListener(this);
		no.addKeyListener(this);
		top.addKeyListener(this);
		bot.addKeyListener(this);
		
		setVisible(true);
		
		top.setFocusable(true);
		bot.setFocusable(true);
		
	}
	
	
	static void removeA()
	{
		new remA();
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


	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
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
		
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE)
		{
			dispose();
		}
	}

	public void keyReleased(KeyEvent e) 
	{	
	}

	public void keyTyped(KeyEvent e) 
	{	
	}

	
}
