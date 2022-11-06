import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class GameMap {
	
	public int map [][]; // array list for our grouping of bricks at the top of the screen
	public int brickWidth ;
	public int brickHeight;
	
	
	public GameMap(int row , int colum) 
	
	{
		map = new int [row][colum]; // row & colum of our bricks 
		for(int i =0 ; i < map.length; i++) 
		{
			for (int j= 0 ; j<map[0].length; j++) {
				map[i][j]= 1; // we add 1 because , 1 will detect that this particular brick CANNOT be intersected or in otherwords TOUCHED by our ball object cause it has be to be showing in the JFrame since we assigned it to number 1 
			}
				
		}
		// we can change values but it is not recommended 
		brickWidth = 540/colum ;
		brickHeight= 150 /row; 
		
				
	}
	
	// this function is needed to actually create the row of bricks 
	public void draw (Graphics2D g) // when this method is called ,it will draw a brick for each value "1" in our above GameMap constructor 
	{
		
		for(int i =0 ; i < map.length; i++) 
		{
			for (int j= 0 ; j<map[0].length; j++) {
				if (map[i][j] > 0 ) // checking if the value of the array postion is 1 or not 
				{
					g.setColor(Color.ORANGE);
					g.fillRect(j*brickWidth + 80 , i * brickHeight *50,brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3)); // creates a border for our bricks at the top of the screen
					g.setColor(Color.black);// sets border color to black. 
					g.drawRect(j*brickWidth + 80 , i * brickHeight *50,brickWidth, brickHeight);
				}
			}
				
		}
		
	}
	
	public void setBrickValue (int value , int row , int colum) // function to change the value of our bricks , so when the ball intersects with it we can make the brick dissappear .
	{
		map[row][colum] = value;
	}
	
	
	
			

}