package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameSelect extends JFrame implements ActionListener
{
	private volatile boolean working = true;
	private volatile Board board;
	private static final String[] gamelist={
			"Elevens",
			"Thirteens"
	};
	public GameSelect(String[] args)
	{
		super("Game Select");
		working = true;
		if(args.length>0)
			getArgs(args);
		else
		{
			JPanel panel = new JPanel();
			
			for(String gn : gamelist)
			{
				JButton newg = new JButton(gn);
				newg.addActionListener(this);
				newg.setActionCommand(gn);
				panel.add(newg);
			}
			
			this.add(panel);
			this.pack();
			this.setVisible(true);
		}
	}
	private void getArgs(String[] args)
	{
		String[] startargs = {"-start", "-play", "-P", "-S", "-p", "-s"};
		System.out.println(java.util.Arrays.deepToString(args));
		if(equalsAny(args, startargs))
		{
			try
			{
				startGame(args[gi(args, startargs)+1]);
			}
			catch(ArrayIndexOutOfBoundsException aioobe)
			{
				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		System.out.println(arg0.getActionCommand());
		startGame(arg0.getActionCommand());
	}
	public void startGame(String gamename)
	{
		try {
			//Tries to start the game
			board = (Board) Class.forName("game.games."+gamename.toLowerCase()+"."+gamename+"Board").newInstance();
			System.out.println("Started game: "+ gamename);
		} catch (InstantiationException e) {
			System.out.println("Error: could not start game: "+gamename);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: game not found: "+gamename);
		}
		working = false;
	}
	private static boolean equalsAny(Object[] a, Object[] b)
	{
		for(int i1=0;i1<a.length;++i1)
			for(int i2=0;i2<b.length;++i2)
				if(a[i1].equals(b[i2]))
					return true;
		return false;
	}
	private static int gi(Object[] a, Object[] b)
	{
		for(int i1=0;i1<a.length;++i1)
			for(int i2=0;i2<b.length;++i2)
				if(a[i1].equals(b[i2]))
					return i2;
		return -1;
	}
	public void waitfor()
	{
		try
		{
			while(working)
				Thread.sleep(10);
		}
		catch(InterruptedException e){}
	}
	public Board getSelection()
	{
		return board;
	}
	public static Board query(String[] args)
	{
		GameSelect selector = new GameSelect(args);
		selector.waitfor();
		Board response = selector.getSelection();
		selector.dispose();
		return response;
	}
}
