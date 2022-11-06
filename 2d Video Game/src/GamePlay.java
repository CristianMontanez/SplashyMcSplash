import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener , ActionListener{

	private boolean play = false ;  
	private int score = 0 ;  // score tracker for game 
	
	private int totalBricks = 21 ;  // total of bricks to break in game 
	
	private Timer time ;  // timer for our game 
	private int delay = -30 ;  // time delay 
	
	
	private int playerX = 310;  // x axis postion of the player
	private int ballPositionX= 120;  // x axis postion of the ball 
	private int ballPositionY = 350; // y axis postion of the ball 
	private int  ballDirectionX = -1 ; //x axis direction of the ball 
	private int ballDirectionY = -2 ; // y axis direction of the ball 
	
	private GameMap gm ; 
	
	// Constructor is needed to add the values we just initalized above.
	
	public GamePlay () 
	{
		gm = new GameMap (3, 7);
		
		addKeyListener(this); // adds the KeyListener we implemented 
		setFocusable(true); // allows our JPanel to be focused 
		setFocusTraversalKeysEnabled(false); // this decides whether or not traversal keys are allowed to be used in J Component for example (tab, shift , & etc..) 
		
		time = new Timer(delay , this); // this parameter take javax.swing.Timer  not javax.utill.timer
		time.start();
	}
	
	public void paint (Graphics g) //this class  draws our shapes to the Jframe 
	{
		// paint used for background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		// Paint for our GameMap class/object 
		 
		gm.draw((Graphics2D) g);
		
		// paint used for the inner borders of the window
		g.setColor(Color.pink);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// Paint used for paddel 
		g.setColor(Color.ORANGE);
		g.fillRect(playerX, 550, 100, 8);
		
		
		// Paint for Ball 
		g.setColor(Color.magenta);
		g.fillOval(ballPositionX, ballPositionY, 20, 20);
		
		g.dispose();
		
		
		
	}

	
	
	//Unimplemented methods from KeyListener & ActionListener interfaces 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		if (play) { // if play is true 
		
		
		if (new Rectangle (ballPositionX , ballPositionY,20,20).intersects(new Rectangle(playerX,550,100,8)))// detects whether the ball hits our paddle or Player x , since the ball is a oval & the paddle is a rectangle they need to be the same so we use a if statement crate a new rectangle & fill it with the same parameters we did in the paint() method then we use .intersects with playerX & this will allow or ball to detect when it hits the padle. 
				{
			
			         ballDirectionY = - ballDirectionY; // minus cause ball is coming down so the opposite or minus of down is up . 
			
				}
		
		
	A:	for (int i = 0 ; i< gm.map.length; i++)  // the A:  is a break lable thats all the way on the botoom .
		{
			for (int j=0; j<gm.map[0].length; j++) 
			{
				if(gm.map[i][j] > 0) 
				{
					int brickX = j * gm.brickWidth + 80; 
					int brickY = i * gm.brickHeight + 50 ;
					int brickWidth = gm.brickWidth;
					int brickHeight= gm.brickHeight;
					
					// we are making the following so the bricks that are to be broken are able to be intersect with our ball so wwe make rectangle object for all the upper bricks & ball as well.
					Rectangle rect = new Rectangle (brickX, brickY , brickWidth , brickHeight);
					Rectangle ballRect = new Rectangle (ballPositionX, ballPositionY , 20 , 20 );
					Rectangle brickRect = rect ;
					
					// does the above objects intersect or not ? 
					
					if (ballRect.intersects(brickRect)) 
					{
						// then we change value to 0 so the individual  brick disappears when intersected or touched by our ball object. 
						// we use our setBrickValue from the GameMap class to set that individual brick to 0 .
						
						gm.setBrickValue(0, i, j);
						
						totalBricks -= 1; // We subtract 1 brick from total bricks value 
						score += 5; // score increases by 5 
						
						if (ballPositionX +19 <=  brickRect.x || ballPositionX + 1 >= brickRect.x + brickRect.width ) 
						{
							ballDirectionX =  -ballDirectionX; // when ball hits left or right border the intersection is deteced & ball goes opposite way since we assign value to the opposite (-)
							// ballDirectionX is for left or right , ballDirectionY  is for up or down
						}else {
							ballDirectionY = -ballDirectionY;
						}
						
						break A;
						
					}
					
					
				}
			}
		}
		
		
		
		
			// Detects whether the ball is hitting any of the borders of the JFrame window 
			ballPositionX += ballDirectionX;
			ballPositionY += ballDirectionY;
			
			if (ballPositionX <  0 ) // if ball postion x or y is less then 0 then balldirection = -  balldirectionxory
			{
				ballDirectionX = - ballDirectionX;
			}
			
			if (ballPositionY < 0 ) 
			{
				ballDirectionY = - ballDirectionY;
			}
			
			if (ballPositionX > 670) 
			{
				ballDirectionX = - ballDirectionX;
			}
			
			// call repaint again to allow our ball move again 
			
			repaint();
			
		
		
		repaint(); // recalls the paint function & paints all of the inner objects again in the void paint() method , this allows our objects to move cause when they moved left or right by user input, the object is essetnially being drawn again by our program giving the transtion effect. 
	}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) // system detects if right directional arrow is used 
		{
			if(playerX >= 600)  // keeps player within the window of Jframe sets borders so object doesnt keep going pass our window
			{
				playerX = 600;
			}else 
			{
				moveRight();
			}
			
		}
	
		if (e.getKeyCode() == KeyEvent.VK_LEFT ||  e.getKeyCode() == KeyEvent.VK_A) 
		{
			if(playerX < 10) 
			{
				playerX =10;
			}else 
			{
				moveLeft();
			}
		}
		
	}
	
	
	public void moveRight() 
	{
		play = true ; // inital value is false so it needs to be changed to true since someone is playing 
		playerX += 20 ;  // moves player object 20 px to the right 
	}
	
	public void moveLeft() 
	{
		play = true ; // inital value is false so it needs to be changed to true since someone is playing 
		playerX -= 20 ;  // moves player objcet 20 px to the left ( minus makes things go left , plus makes things go right. d
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}