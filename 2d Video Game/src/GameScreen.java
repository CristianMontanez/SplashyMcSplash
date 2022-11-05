import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class GameScreen extends JFrame {

	JFrame frame = new JFrame ("Brick Break");
	gameObject o ; 
	
	
	
	

	
	public void paint (Graphics g) 
	{
		super.paint(g);
		
		g.drawRect(120, 90, 180, 140);
	}
	
		public GameScreen() 
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(400, 40);
		frame.getContentPane();
		frame.setVisible(true);
		
	}
	
	
}
