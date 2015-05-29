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

import javax.swing.*;

import java.io.*;

public class remH extends JFrame implements ActionListener, ItemListener, KeyListener
{
	
	String iname;
	
	Container c = getContentPane();
	
	JPanel top = new JPanel();
	JPanel bot = new JPanel();
	
	JButton accept = new JButton("Yes");
	JButton cancel = new JButton("No");
	JComboBox cb = new JComboBox(Digital_Organizer.anames);
	
	public remH()
	{
		super("Remove an assignment");
		setSize(500, 150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(top);
		add(bot);
		top.add(cb);
		bot.add(accept);
		bot.add(cancel);
		c.add("North", top);
		c.add("South", bot);
		
		cb.setSelectedItem(null);
		
		accept.addActionListener(this);
		cancel.addActionListener(this);
		cb.addItemListener(this);
		top.addKeyListener(this);
		bot.addKeyListener(this);
		
		cb.addKeyListener(this);
		setVisible(true);
		
		top.setFocusable(true);
		bot.setFocusable(true);
		
	}
	
	
	public static void removeAssignment()
	{
		File c = new File("hw.dat");
		if(c.length() > 0)
		{
			remH r = new remH();
		}	
	}
	
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == cancel)
		{
			dispose();
		}
		
		if(e.getSource() == accept)
		{
			String place;
			int point = 0;
			
			try
			{
				File old = new File("hw.dat");
				File tmp = new File("temporary.tmp");
				tmp.createNewFile();
				
				FileReader fr = new FileReader(old);
				BufferedReader br = new BufferedReader(fr);
				
				FileWriter fw = new FileWriter(tmp, true);
				BufferedWriter bw = new BufferedWriter(fw);
				
				while((place = br.readLine()) != null)
				{
					if(place.equals(iname))
					{
						point = 1;
					}
					
					else if(point > 0 && point < 4)
					{
						point++;
					}
					
					else
					{
						bw.write(place);
						bw.newLine();
					}
					
				}
				
					bw.close();
					fw.close();
					br.close();
					fr.close();
					
					old.setWritable(true);
					old.delete();
					tmp.setWritable(true);
					tmp.renameTo(old);
					
			}
			catch(IOException except)
			{
				except.printStackTrace();
			}
			
			Digital_Organizer.dat1.setText(null);
			Digital_Organizer.dat2.setText(null);
			Digital_Organizer.dat3.setText(null);
			Digital_Organizer.dat4.setText(null);
			
			try 
			{
				Digital_Organizer.updateHW();
			} 
			catch (IOException e1) 
			{
				
				e1.printStackTrace();
			}
			
			int arraylength = Digital_Organizer.anames.length;
			
			for(int n = 0; n < arraylength; n++)
			{
				Digital_Organizer.dat1.append(Digital_Organizer.anames[n] + "\n");
			}
			
			for(int de = 0; de < arraylength; de++)
			{
				Digital_Organizer.dat2.append(Digital_Organizer.adescs[de] + "\n");
			}
			for(int c = 0; c < arraylength; c++)
			{
				Digital_Organizer.dat3.append(Digital_Organizer.aclasses[c] + "\n");
			}
			Digital_Organizer.timeRemove();
			for(int da = 0; da < arraylength; da++)
			{
				Digital_Organizer.dat4.append(Digital_Organizer.adates[da] + "\n");	
			}
			
			dispose();
			
		}
		
	}


	
	public void itemStateChanged(ItemEvent i) 
	{
		if(i.getItemSelectable() == cb)
		{
			iname = (String) i.getItem();
		}
	}


	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			String place;
			int point = 0;
			
			try
			{
				File old = new File("hw.dat");
				File tmp = new File("temporary.tmp");
				tmp.createNewFile();
				
				FileReader fr = new FileReader(old);
				BufferedReader br = new BufferedReader(fr);
				
				FileWriter fw = new FileWriter(tmp, true);
				BufferedWriter bw = new BufferedWriter(fw);
				
				while((place = br.readLine()) != null)
				{
					if(place.equals(iname))
					{
						point = 1;
					}
					
					else if(point > 0 && point < 4)
					{
						point++;
					}
					
					else
					{
						bw.write(place);
						bw.newLine();
					}
					
				}
				
					bw.close();
					fw.close();
					br.close();
					fr.close();
					
					old.setWritable(true);
					old.delete();
					tmp.setWritable(true);
					tmp.renameTo(old);
					
			}
			catch(IOException except)
			{
				except.printStackTrace();
			}
			
			Digital_Organizer.dat1.setText(null);
			Digital_Organizer.dat2.setText(null);
			Digital_Organizer.dat3.setText(null);
			Digital_Organizer.dat4.setText(null);
			
			try 
			{
				Digital_Organizer.updateHW();
			} 
			catch (IOException e1) 
			{
				
				e1.printStackTrace();
			}
			
			int arraylength = Digital_Organizer.anames.length;
			
			for(int n = 0; n < arraylength; n++)
			{
				Digital_Organizer.dat1.append(Digital_Organizer.anames[n] + "\n");
			}
			
			for(int de = 0; de < arraylength; de++)
			{
				Digital_Organizer.dat2.append(Digital_Organizer.adescs[de] + "\n");
			}
			for(int c = 0; c < arraylength; c++)
			{
				Digital_Organizer.dat3.append(Digital_Organizer.aclasses[c] + "\n");
			}
			Digital_Organizer.timeRemove();
			for(int da = 0; da < arraylength; da++)
			{
				Digital_Organizer.dat4.append(Digital_Organizer.adates[da] + "\n");	
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
