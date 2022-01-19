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

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Digital_Organizer extends JFrame implements ActionListener
{
	static String[] classlist;
	
	static String[] anames;
	
	static String[] adescs;
	
	static String[] aclasses;
	
	static String[] adates;

	private JButton addClass = new JButton("Add a class");
	
	private JButton addHW = new JButton("Add an assignment");
	
	private JButton remClass = new JButton("Remove a class");
	
	private JButton remall = new JButton("Remove EVERYTHING");
	
	private JButton remHW = new JButton("Remove an assignment");


	static JTextArea dat1 = new JTextArea(18, 20);
	
	static JTextArea dat2 = new JTextArea(18, 20);
	
	static JTextArea dat3 = new JTextArea(18, 20);
	
	static JTextArea dat4 = new JTextArea(18, 20);
	
	private static JScrollPane dat = new JScrollPane(dat2);


	private Digital_Organizer(int size1, int size2, String name)
	{
		super(name);
		setSize(size1, size2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel image = new JPanel();
		add(image);
		JPanel data = new JPanel();
		add(data);
		JPanel pnl = new JPanel();
		add(pnl);
		ClassLoader ldr = this.getClass().getClassLoader();
		ImageIcon title = new ImageIcon(Objects.requireNonNull(ldr.getResource("banner.png")));
		JLabel banner = new JLabel(title);
		image.add(banner);
		pnl.add(addClass);
		pnl.add(addHW);
		pnl.add(remClass);
		pnl.add(remHW);
		pnl.add(remall);
		JTextArea num = new JTextArea(18, 2);
		data.add(num);
		data.add(dat1);
		data.add(dat);
		data.add(dat3);
		data.add(dat4);
		addClass.addActionListener(this);
		addHW.addActionListener(this);
		remClass.addActionListener(this);
		remall.addActionListener(this);
		remHW.addActionListener(this);
		Container cp = getContentPane();
		cp.add("North", image);
		cp.add("Center", data);
		cp.add("South", pnl);
		
				//Keybinding for adding a class
				KeyStroke addckey = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK);
				
				Action addcaction = new AbstractAction()
				{
					public void actionPerformed(ActionEvent e)
					{
						addC.createClass();
					}
				};
				
				//keybinding for adding an assignment
				KeyStroke addhwkey = KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_DOWN_MASK);
				
				Action addhwaction = new AbstractAction()
				{
					public void actionPerformed(ActionEvent e)
					{
						addH.createHW();
					}
				};
				
				//keybinding for removing a class
				
				KeyStroke remckey = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_DOWN_MASK);
				
				Action remcaction = new AbstractAction()
				{
					public void actionPerformed(ActionEvent e)
					{
						remC.remClass();
					}
				};
				
				
				//Keybinding for removing an assignment
				
				KeyStroke remhwkey = KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_DOWN_MASK);
				
				Action remhwaction = new AbstractAction()
				{
					public void actionPerformed(ActionEvent e)
					{
						remH.removeAssignment();
					}
				};
				
				
				//Keybind for removal of all assignments
				
				KeyStroke remallkey = KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK);
				
				Action remallaction = new AbstractAction()
				{
					public void actionPerformed(ActionEvent e)
					{
						remA.removeA();
					}
				};
		
		
		pnl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(addckey, "ADDC");
		
		pnl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(remallkey, "REMALL");
		
		pnl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(remhwkey, "REMHW");
		
		pnl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(addhwkey, "ADDHW");
		
		pnl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(remckey, "REMC");
		
		pnl.getActionMap().put("ADDC", addcaction);
		
		pnl.getActionMap().put("REMHW", remhwaction);
		
		pnl.getActionMap().put("REMALL", remallaction);
		
		pnl.getActionMap().put("REMC", remcaction);
		
		pnl.getActionMap().put("ADDHW", addhwaction);
		
		
		
		num.setText("01.\n02.\n03.\n04.\n05.\n06.\n07.\n08.\n09.\n10.\n11.\n12.\n13.\n14.\n15.\n16.\n17.\n18.");
		num.setEditable(false);

		int arraylength = anames.length;
		for (String aname : anames) {
			dat1.append(aname + "\n");
		}
		
		for(int de = 0; de < arraylength; de++)
		{
			dat2.append(adescs[de] + "\n");
		}
		for(int c = 0; c < arraylength; c++)
		{
			dat3.append(aclasses[c] + "\n");
		}
		for(int da = 0; da < arraylength; da++)
		{
			dat4.append(adates[da] + "\n");	
		}

		Color cname = new Color(0, 0, 255);
		dat1.setForeground(cname);
		Color cclas = new Color(25, 180, 40);
		dat3.setForeground(cclas);
		Color cdate = new Color(255, 0, 0);
		dat4.setForeground(cdate);
		dat1.setEditable(false);
		dat2.setEditable(false);
		dat3.setEditable(false);
		dat4.setEditable(false);
		
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException
	{		
		updateClass();
		updateHW();
		timeRemove();
		
		new Digital_Organizer(1100, 500, "Digital Organizer");
	}


	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == addClass)
		{
			addC.createClass();
		}
		
		if(e.getSource() == addHW)
		{
			addH.createHW();
		}
		
		if(e.getSource() == remClass)
		{
			remC.remClass();
		}
		
		if(e.getSource() == remall)
		{
			remA.removeA();
		}
		
		if(e.getSource() == remHW)
		{
			remH.removeAssignment();
		}
		
			
	}
	
	
	static void updateClass() throws IOException
	{
		ArrayList<String> list = new ArrayList<>();
		String placeholder;
		
		File classes = new File("classes.dat");
		File hw = new File("hw.dat");
		
		if(classes.exists())
		{
			if(!hw.exists())
			{
				hw.createNewFile();
			}
			
			FileReader fr = new FileReader("classes.dat");
			BufferedReader bf = new BufferedReader(fr);
	
			while((placeholder = bf.readLine()) != null)
			{
				list.add(placeholder);
			}
			
			classlist = list.toArray(new String[0]);
			fr.close();
			bf.close();
		}
		
		else
		{
			classes.createNewFile();
			hw.createNewFile();
		}
	}
	
	static void updateHW() throws IOException
	{
		ArrayList<String> lnames = new ArrayList<>();
		ArrayList<String> ldescs = new ArrayList<>();
		ArrayList<String> lclasses = new ArrayList<>();
		ArrayList<String> ldates = new ArrayList<>();
		
		
		String temp;
		int currentval = 0;
		
		FileReader f = new FileReader("hw.dat");
		BufferedReader b = new BufferedReader(f);
		
		while((temp = b.readLine()) != null)
		{		
				if(currentval == 0)
				{
					currentval++;
					lnames.add(temp);
				}
				
				else if(currentval == 1)
				{
					currentval++;
					ldescs.add(temp);
				}
				
				else if(currentval == 2)
				{
					currentval++;
					lclasses.add(temp);
				}
				
				else if(currentval == 3)
				{
					currentval = 0;
					if(temp.contentEquals("none"))
					{
						temp = "Tomorrow";
					}
					
					ldates.add(temp);
				}
				
				else
				{
					System.out.println("ERROR!");
				}
				
	}
		b.close();
		f.close();
		
		anames = lnames.toArray(new String[0]);
		adescs = ldescs.toArray(new String[0]);
		aclasses = lclasses.toArray(new String[0]);
		adates = ldates.toArray(new String[0]);
			
	}
	
	static void timeRemove()
	{
		
		Calendar cal = Calendar.getInstance();
		
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int temp;
		int time;
		String stime;
		
		int count = adates.length;
		
		for(int x = 0; x < count; x++)
		{
			temp = 0;
			
			switch(adates[x])
			{
				case "Monday" : temp = 2; break;
				case "Tuesday" : temp = 3; break;
				case "Wednesday" : temp = 4; break;
				case "Thursday" : temp = 5; break;
				case "Friday" : temp = 6; break;
				case "Saturday" : temp = 7; break; 
				case "Sunday" : temp = 1; break;
			}
			
			if(temp > 0)
			{
				if(temp < day)
				{
					time = 7 - day + (temp);
				}
			
				else 
				{
					time = temp - day;	
				}	
			
				
				
				if(time == 0)
				{
					adates[x] = "Due Today";
				}
				
				else if(time == 1)
				{
					adates[x] = "Due Tomorrow";
				}
				
				else
				{
					stime = Integer.toString(time);
					adates[x] = "Due in " + stime + " days";
				}
			}
			
			else if(adates[x].contains("/"))
			{
				int amount;
				
				Calendar ca = Calendar.getInstance();
				
				int cmonth = ca.get(Calendar.MONTH);
				int cday = ca.get(Calendar.DAY_OF_MONTH);
				int cyear = ca.get(Calendar.YEAR);
				cmonth++;
				
				String original = adates[x];
				String[] split = original.split("/");
				
				int umonth = Integer.parseInt(split[0]);
				int uday = Integer.parseInt(split[1]);
				int uyear = Integer.parseInt(split[2]);
				
				if(umonth == cmonth && uyear == cyear)
				{
					amount = uday - cday;
					
					if(amount == 1)
					{
						adates[x] = "Due Tomorrow";
					}
					
					else if(amount == 0)
					{
						adates[x] = "Due Today";
					}
					
					else
					{
						adates[x] = "Due in " + amount + " days";
					}
				}
				
				else
				{
					adates[x] = "Due on " + original;
				}
			}
			
		}
		
		
	}

	

}
