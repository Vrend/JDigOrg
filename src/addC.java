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
import javax.swing.*;

import java.awt.Container;
import java.awt.event.*;
import java.io.*;


public class addC extends JFrame implements ActionListener, KeyListener
{
	private static String course;

	private JTextField field = new JTextField(14);

	private JButton cancel = new JButton("Cancel");

	private addC()
	{
		super("Add a Class");
		setSize(500, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel addpnl = new JPanel();
		add(addpnl);
		JPanel btns = new JPanel();
		add(btns);
		JLabel desc = new JLabel("Please enter the name of your class");
		addpnl.add(desc);
		addpnl.add(field);
		JButton accept = new JButton("Done");
		btns.add(accept);
		btns.add(cancel);
		accept.addActionListener(this);
		cancel.addActionListener(this);
		Container c = getContentPane();
		c.add("North", addpnl);
		c.add("South", btns);
		
		field.addKeyListener(this);
		
		setVisible(true);
	}
	
	static void createClass()
	{
		new addC();
	}
	
	
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == cancel)
		{
			dispose();
		}
		
		
		if(field.getText().trim().length() > 0 && field.getText().trim().length() < 31)
		{
			course = field.getText().trim();
			try
			{
				FileWriter fw = new FileWriter("classes.dat", true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.append(course);
				bw.newLine();
				bw.close();
				fw.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			
			try 
			{
				Digital_Organizer.updateClass();
			} 
			
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			dispose();
		}
	}

	
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			if(field.getText().trim().length() > 0 && field.getText().trim().length() < 31)
			{
				course = field.getText().trim();
				try
				{
					FileWriter fw = new FileWriter("classes.dat", true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.append(course);
					bw.newLine();
					bw.close();
					fw.close();
				}
				
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
				
				
				try 
				{
					Digital_Organizer.updateClass();
				} 
				
				catch (IOException ioe) 
				{
					ioe.printStackTrace();
				}
				
				dispose();
			}
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
