import java.awt.Graphics;

import javax.swing.JFrame;

public class BrickBreakerMainClass {
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame jf = new JFrame(); // makes our frame / screen for the game 
		GamePlay gameplay = new GamePlay();
		jf.setBounds(10, 10, 700, 600);
		jf.setTitle("BrickBreakerCM");
		jf.setResizable(false ); // cannot be resized 
		jf.setVisible(true); // allows the JFrame to become visable 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Needed for Jframe , allows to Close the window 
		jf.add(gameplay); // adds our GamePlay object/Class to the panel 
		
	}

}
