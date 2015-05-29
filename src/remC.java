/**
 *     This file is part of JDigOrg.

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


public class remC extends JFrame implements ActionListener, ItemListener, KeyListener
{

	String rclass;
	
	Container co = getContentPane();
	
	JPanel top = new JPanel();
	
	JPanel bot = new JPanel();
	
	JComboBox cb = new JComboBox(Digital_Organizer.classlist);
	
	JButton a = new JButton("Accept");
	
	JButton c = new JButton("Cancel");
	
	public remC()
	{
		super("Remove a class");
		setSize(500, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(top);
		add(bot);
		top.add(cb);
		bot.add(a);
		bot.add(c);
		co.add("North", top);
		co.add("South", bot);
		cb.setSelectedItem(null);
		a.addActionListener(this);
		c.addActionListener(this);
		cb.addItemListener(this);
		cb.addKeyListener(this);
		top.addKeyListener(this);
		
		setVisible(true);
		
		top.setFocusable(true);
		
		
		
		
	}
	
	public static void remClass()
	{
		File cl = new File("classes.dat");
		
		if(cl.length() > 0)
		{
			remC gui = new remC();
		}	
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		String place;
		int count = 0;
		
		if(e.getSource() == c)
		{
			dispose();
		}
		
		if(e.getSource() == a)
		{
			try
			{
				File tmp = new File("tclasses.tmp");
				File old = new File("classes.dat");
				
				tmp.createNewFile();
				
				FileWriter filew = new FileWriter(tmp, true);
				BufferedWriter buffw = new BufferedWriter(filew);
				
				FileReader filer = new FileReader(old);
				BufferedReader buffr = new BufferedReader(filer);
				
				while((place = buffr.readLine()) != null)
				{
					if(place.equals(rclass))
					{
						
					}
					
					else
					{
						buffw.write(place);
						buffw.newLine();
					}
				}
				
				buffw.close();
				filew.close();
				buffr.close();
				filer.close();
				
				
				old.setWritable(true);
				old.delete();
				tmp.setWritable(true);
				tmp.renameTo(old);
			}
			
			catch(IOException exc)
			{
				exc.printStackTrace();
			}
			
			try 
			{
				Digital_Organizer.updateClass();
			} 
			
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			
			dispose();
		}
		
	}
	
	
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getItemSelectable() == cb)
		{
			rclass = (String) e.getItem();
		}
	}

	
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			String place;
			int count = 0;
			
			
			try
			{
				File tmp = new File("tclasses.tmp");
				File old = new File("classes.dat");
				
				tmp.createNewFile();
				
				FileWriter filew = new FileWriter(tmp, true);
				BufferedWriter buffw = new BufferedWriter(filew);
				
				FileReader filer = new FileReader(old);
				BufferedReader buffr = new BufferedReader(filer);
				
				while((place = buffr.readLine()) != null)
				{
					if(place.equals(rclass))
					{
						
					}
					
					else
					{
						buffw.write(place);
						buffw.newLine();
					}
				}
				
				buffw.close();
				filew.close();
				buffr.close();
				filer.close();
				
				
				old.setWritable(true);
				old.delete();
				tmp.setWritable(true);
				tmp.renameTo(old);
			}
			
			catch(IOException exc)
			{
				exc.printStackTrace();
			}
			
			try 
			{
				Digital_Organizer.updateClass();
			} 
			
			catch (IOException e1) 
			{
				e1.printStackTrace();
			}
			
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
