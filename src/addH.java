import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;

import javax.swing.*;


public class addH extends JFrame implements ActionListener, ItemListener, KeyListener
{
	
	String sname;
	String sdesc;
	String sdate;
	String sclass;
	String sudate;
	
	boolean changed = false;
	boolean test = true;
	static String days = null;
	
	JPanel top = new JPanel();
	
	JPanel mid = new JPanel();
	
	JPanel bot = new JPanel();
	
	Container cont = getContentPane();
	
	JTextField name = new JTextField(20);
	
	JTextField desc = new JTextField(40);
	
	JTextField date = new JTextField(15);
	
	JButton ok = new JButton("OK");
	
	JButton cancel = new JButton("Cancel");
	
	JLabel lb2  = new JLabel("Leave the date blank if due tomorrow");
	
	JLabel dt  = new JLabel("Date:");
	
	JLabel ld  = new JLabel("Description:");
	
	JLabel ln  = new JLabel("Name:");
	
	JLabel lc = new JLabel("Class:");
	
	JComboBox cc = new JComboBox(Digital_Organizer.classlist);
	
	Color grey = new Color(161, 161, 161);
	
	Color black = new Color(0, 0 , 0);
	
	
	public addH()
	{
		super("Add an Assignment");
		setFocusable(true);
		setSize(500, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(top);
		add(mid);
		add(bot);
		top.add(ln);
		top.add(name);
		mid.add(ld);
		mid.add(desc);
		mid.add(lc);
		mid.add(cc);
		mid.add(dt);
		mid.add(date);
		bot.add(ok);
		bot.add(cancel);
		mid.add(lb2);
		date.setText("MM/DD/YYYY or a weekday");
		date.setForeground(grey);
		
		date.addFocusListener(new FocusListener() {
		
			
			public void focusGained(FocusEvent fe) 
			{
				date.setText("");
				date.setForeground(black);
				
			}

			
			public void focusLost(FocusEvent fe) 
			{
				//do nothing
			}

		});
		
		
		cancel.addActionListener(this);
		ok.addActionListener(this);
		cc.addItemListener(this);
		cc.setSelectedItem(null);
		cont.add("North", top);
		cont.add("Center", mid);
		cont.add("South", bot);
		
		name.addKeyListener(this);
		desc.addKeyListener(this);
		date.addKeyListener(this);
		
		setVisible(true);
		
		name.requestFocusInWindow();
		
	}
	
	
	public static void createHW()
	{
		File c = new File("classes.dat");
		if(c.length() > 0)
		{
			addH gu = new addH();
		}
	}
	
	
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == cancel)
		{	
			dispose();
		}
		
		if(e.getSource() == ok)
		{		
			
			int ndays = 0;
			int today = 0;
			int nday = 0;
			
			Calendar cal = Calendar.getInstance();
			today = cal.get(Calendar.DAY_OF_WEEK);
			
			if(name.getText().trim().length() > 0 && name.getText().trim().length() < 31 && changed == false)
			{
				if(desc.getText().trim().length() > 0)
				{
					if(date.getText().trim().length() > 0)
					{
						sdate = date.getText().trim();
						sname = name.getText().trim();
						sdesc = desc.getText().trim();
					}
					
					else
					{
						sname = name.getText().trim();
						sdate = "none";
						sdesc = desc.getText().trim();
					}
				}
				
				else
				{
					
					if(date.getText().trim().length() > 0)
					{
						sdate = date.getText().trim();
						sname = name.getText().trim();
						sdesc = "none";
					}
					
					else
					{
						sname = name.getText().trim();
						sdate = "none";
						sdesc = "none";
					}
						
				}
				
				if(sdate.equals("none"))
				{
					test = true;
					int newtoday = today + 1 ;
					
					if(newtoday > 7)
					{
						newtoday = 1;
					}
					
					switch(newtoday)
					{
					case 1 : sdate = "Sunday"; break;
					case 2 : sdate = "Monday"; break;
					case 3 : sdate = "Tuesday"; break;
					case 4 : sdate = "Wednesday"; break;
					case 5 : sdate = "Thursday"; break;
					case 6 : sdate = "Friday"; break;
					case 7 : sdate = "Saturday"; break;
					}
					
					days = "Due Tomorrow";
				}
				
				else if(sdate.equals("Sunday") || sdate.equals("Monday") || sdate.equals("Tuesday") || sdate.equals("Wednesday") || sdate.equals("Thursday") || sdate.equals("Friday") || sdate.equals("Saturday"))
				{
					test = true;
					
					switch(sdate)
					{
					case "Sunday" : nday = 1; break;
					case "Monday" : nday = 2; break;
					case "Tuesday" : nday = 3; break;
					case "Wednesday" : nday = 4; break;
					case "Thursday" : nday = 5; break;
					case "Friday" : nday = 6; break;
					case "Saturday" : nday = 7; break;
					}
					
					if(today > nday)
					{
						ndays = 7 - today + nday;
						days = "Due in " + Integer.toString(ndays) + " days";
					}
					else if((today+1) == nday)
					{
						days = "Due Tomorrow";
					}
					else if(today == nday)
					{
						days = "Due Today";
					}
					else
					{
						ndays = nday - today;
						days = "Due in " + Integer.toString(ndays) + " days";
					}
				}
				
				else if(sdate.contains("/"))
				{
					int amount = 0;
					test = true;
					Calendar cal1 = Calendar.getInstance();
					
					int cmonth = cal1.get(Calendar.MONTH);
					int cday = cal1.get(Calendar.DAY_OF_MONTH);
					int cyear = cal1.get(Calendar.YEAR);
					cmonth++;
					
					String original = sdate;
					String[] split = original.split("/");
					
					int umonth = Integer.parseInt(split[0]);
					int uday = Integer.parseInt(split[1]);
					int uyear = Integer.parseInt(split[2]);
					
					if(umonth == cmonth && uyear == cyear)
					{
						amount = uday - cday;
						days = "Due in " + Integer.toString(amount) + " days";
					}
					
					else
					{
						days = "Due on " + original;
					}
				}
				
				else
				{
					test = false;
				}
				
				if(test == true)
				{
					
				try
				{
					FileWriter fw = new FileWriter("hw.dat", true);
					BufferedWriter bw = new BufferedWriter(fw);
					
					bw.append(sname);
					bw.newLine();
					bw.append(sdesc);
					bw.newLine();
					bw.append(sclass);
					bw.newLine();
					bw.append(sdate);
					bw.newLine();
					bw.close();
					fw.close();
				}
				
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				
				try 
				{
					Digital_Organizer.updateHW();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				Digital_Organizer.dat1.append(sname + "\n");
				Digital_Organizer.dat2.append(sdesc + "\n");
				Digital_Organizer.dat3.append(sclass + "\n");
				Digital_Organizer.dat4.append(days + "\n");
				dispose();
				
				}
				
			}
					
		}
	}


	
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getItemSelectable() == cc)
		{
			sclass = (String) e.getItem();
			
			if(cc.getSelectedItem() == null)
			{
				changed = true;
			}
			else
			{
				changed = false;
			}
		}
	}


	
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
		{
			int ndays = 0;
			int today = 0;
			int nday = 0;
			
			Calendar cal = Calendar.getInstance();
			today = cal.get(Calendar.DAY_OF_WEEK);
			
			if(name.getText().trim().length() > 0 && name.getText().trim().length() < 31 && changed == false)
			{
				if(desc.getText().trim().length() > 0)
				{
					if(date.getText().trim().length() > 0)
					{
						sdate = date.getText().trim();
						sname = name.getText().trim();
						sdesc = desc.getText().trim();
					}
					
					else
					{
						sname = name.getText().trim();
						sdate = "none";
						sdesc = desc.getText().trim();
					}
				}
				
				else
				{
					
					if(date.getText().trim().length() > 0)
					{
						sdate = date.getText().trim();
						sname = name.getText().trim();
						sdesc = "none";
					}
					
					else
					{
						sname = name.getText().trim();
						sdate = "none";
						sdesc = "none";
					}
						
				}
				
				if(sdate.equals("none"))
				{
					test = true;
					int newtoday = today + 1 ;
					
					if(newtoday > 7)
					{
						newtoday = 1;
					}
					
					switch(newtoday)
					{
					case 1 : sdate = "Sunday"; break;
					case 2 : sdate = "Monday"; break;
					case 3 : sdate = "Tuesday"; break;
					case 4 : sdate = "Wednesday"; break;
					case 5 : sdate = "Thursday"; break;
					case 6 : sdate = "Friday"; break;
					case 7 : sdate = "Saturday"; break;
					}
					
					days = "Due Tomorrow";
				}
				
				else if(sdate.equals("Sunday") || sdate.equals("Monday") || sdate.equals("Tuesday") || sdate.equals("Wednesday") || sdate.equals("Thursday") || sdate.equals("Friday") || sdate.equals("Saturday"))
				{
					test = true;
					
					switch(sdate)
					{
					case "Sunday" : nday = 1; break;
					case "Monday" : nday = 2; break;
					case "Tuesday" : nday = 3; break;
					case "Wednesday" : nday = 4; break;
					case "Thursday" : nday = 5; break;
					case "Friday" : nday = 6; break;
					case "Saturday" : nday = 7; break;
					}
					
					if(today > nday)
					{
						ndays = 7 - today + nday;
						days = "Due in " + Integer.toString(ndays) + " days";
					}
					else if((today+1) == nday)
					{
						days = "Due Tomorrow";
					}
					else if(today == nday)
					{
						days = "Due Today";
					}
					else
					{
						ndays = nday - today;
						days = "Due in " + Integer.toString(ndays) + " days";
					}
				}
				
				else if(sdate.contains("/"))
				{
					int amount = 0;
					test = true;
					Calendar cal1 = Calendar.getInstance();
					
					int cmonth = cal1.get(Calendar.MONTH);
					int cday = cal1.get(Calendar.DAY_OF_MONTH);
					int cyear = cal1.get(Calendar.YEAR);
					cmonth++;
					
					String original = sdate;
					String[] split = original.split("/");
					
					int umonth = Integer.parseInt(split[0]);
					int uday = Integer.parseInt(split[1]);
					int uyear = Integer.parseInt(split[2]);
					
					if(umonth == cmonth && uyear == cyear)
					{
						amount = uday - cday;
						days = "Due in " + Integer.toString(amount) + " days";
					}
					
					else
					{
						days = "Due on " + original;
					}
				}
				
				else
				{
					test = false;
				}
				
				if(test == true)
				{
					
				try
				{
					FileWriter fw = new FileWriter("hw.dat", true);
					BufferedWriter bw = new BufferedWriter(fw);
					
					bw.append(sname);
					bw.newLine();
					bw.append(sdesc);
					bw.newLine();
					bw.append(sclass);
					bw.newLine();
					bw.append(sdate);
					bw.newLine();
					bw.close();
					fw.close();
				}
				
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				
				try 
				{
					Digital_Organizer.updateHW();
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				
				Digital_Organizer.dat1.append(sname + "\n");
				Digital_Organizer.dat2.append(sdesc + "\n");
				Digital_Organizer.dat3.append(sclass + "\n");
				Digital_Organizer.dat4.append(days + "\n");
				dispose();
				
				}
				
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
