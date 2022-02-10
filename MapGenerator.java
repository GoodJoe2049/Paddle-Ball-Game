import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator 
{
	public  int map[][];
	public  int brickWidth;
	public  int brickHeight;
	
	public MapGenerator (int row, int col)
	{
		map = new int[row][col];		
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				map[i][j] = 1;
			}			
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}	
	
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<map.length; i++)
		{
			for(int j =0; j<map[0].length; j++)
			{
				if(map[i][j] > 0)
				{
					if(Gameplay.devil==false){
						g.setColor(Color.WHITE);
					}else if(Gameplay.by2active&&Gameplay.by2dur>0){
						g.setColor(Color.green);
					}else{
						g.setColor(Color.orange);
					}
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					if(Gameplay.lvl<9){
						g.setStroke(new BasicStroke(3));
					}else if(Gameplay.lvl<50){
						g.setStroke(new BasicStroke(2));
					}else{
						g.setStroke(new BasicStroke(1));
					}
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);				
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col)
	{
		map[row][col] = value;
	}
	
}
