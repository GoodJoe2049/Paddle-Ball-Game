//import java.util.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.Timer;
import java.util.Random;
public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
	public static boolean play = false;
	private int score = 0;
	
	private int totalBricks = 1;
	
	private Timer timer;
	private int delay=4;
	
	public int playerX = 310;
	
	private int ballposX = 350;
	private int ballposY = 520;
	Random rand = new Random();
	private int ballXdir = rand.nextInt(4)-2;
	private int ballYdir = -2;
	public int times2 = rand.nextInt(20);
	private MapGenerator map;
	/////
	public static boolean devil = false;
	
	private int moveD=30;
	private int ballS=20;
	public static int lvl = 0;//0
	private boolean won=false;
	private int xBricks=2;
	private int yBricks=6;
	private int totalSouls=0;
	private int hitStreak=0;
	private int highestSoul=0;
	private int highestStreak=0;
	private int damned=0;
	private int fiveBrick=0;
	private int loss=0;
	private boolean isFive=false;
	private int bigBchance=2;
	private int bigBdur=0;
	private boolean bigBactive=false;
	public static int by2dur=5;
	public static boolean by2active=false;
	public int by2chance=1;
	private boolean curse=false;
	private int curseChance=rand.nextInt(100)+1;
	/////////
	private boolean fireBallActive=false;
	private int fireBallDur=5;
	private int fEffect = 0;
	//////
	public static boolean hitBoss=false;
	private int attackX=300;
	private int attackY=180;
	private int soulSteal=0;
	private int paddleHit=0;
	////
	private int playSign=0;
	private boolean isPaused=false;
	//////////
	public Gameplay()
	{		
		map = new MapGenerator(1, 1);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{    		
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		if(highestStreak<hitStreak){
			highestStreak=hitStreak;
		}
		if(totalSouls<0||(totalSouls+score)<0){
			ballXdir=0;
			ballYdir=0;
			ballposX=0;
			ballposY=0;
			g.setColor(Color.darkGray);
			g.fillRect(60, 370, 572, 180);
			g.setColor(Color.red);
			g.setFont(new Font("Times",Font.BOLD, 50));
			g.drawString("0\\   /0", 280, 120);
			g.drawString("^~~~^", 270, 190);
			g.setFont(new Font("Papyrus",Font.PLAIN, 40));
            g.drawString("Your Soul is Damned", 150,300);
			g.setColor(Color.ORANGE);
			g.setFont(new Font("serif",Font.PLAIN, 20));           
			g.drawString("You are the price the vessel could not pay", 170,335);
			g.setFont(new Font("serif",Font.BOLD, 25));
			g.drawString("Highest Soul Count : "+highestSoul, 70,400);
			g.setColor(Color.magenta);
			g.setFont(new Font("serif",Font.BOLD, 25));
			g.drawString("Highest Multiplier :: x"+highestStreak, 70,435);
			g.setColor(Color.red);
			g.drawString("Damned Souls :::::::: "+damned+" and you", 70,470);
			g.setColor(Color.green);
			g.drawString("5-Soul Bricks hit ::::: "+fiveBrick, 70,505);
			g.setColor(Color.cyan);
			g.drawString("Circle Reached ::::::: "+lvl, 70,540);
		}else{
		if(lvl==0){
			g.setColor(Color.red);
			g.setFont(new Font("Times",Font.BOLD, 50));
			g.drawString("0\\   /0", 280, 120);
			g.drawString("^~~~^", 270, 190);
		}
		// drawing map
		map.draw((Graphics2D) g);
		/////
		if(lvl==9){
			if(totalSouls+score>highestSoul){//
				highestSoul=totalSouls+score;
			}
			g.setColor(Color.darkGray);
			g.fillRect(102, 220, 500, 15);
			g.setColor(Color.red);
			g.setFont(new Font("Times",Font.BOLD, 50));
			g.drawString("^~~~^", 270, 190);
			g.fillRect(attackX, attackY, 30, 30);
			g.setColor(Color.darkGray);
			g.setFont(new Font("Times",Font.BOLD, 34));
			g.drawString("!", attackX+10, attackY+28);
			attackY+=4;
			if(attackY>600){
				attackX=rand.nextInt(460)+80;
				attackY=180;
			}
			g.setFont(new Font("Times",Font.BOLD, 50));

			if(hitBoss){
				g.setColor(Color.darkGray);
				g.fillRect(80, 50, 540, 150);
				g.setColor(Color.red);
				g.drawString("X\\   /X", 270, 120);
				hitBoss=false;
			}else{
				g.setColor(Color.red);
				g.drawString("0\\   /0", 280, 120);
			}
			g.setColor(Color.red);
			g.fillRect(102, 220, totalBricks/100, 15);
			if(paddleHit>0){
				if(paddleHit%3==0){
					g.setColor(Color.red);
				}else{
					g.setColor(Color.white);
				}
				if(soulSteal+totalBricks>50000){
					g.fillRect(102+(totalBricks/100)-(soulSteal/100), 220, (50000-totalBricks)/100, 15);
				}else if(totalBricks>0){
					g.fillRect(102+(totalBricks/100)-(soulSteal/100), 220, soulSteal/100, 15);
				}
			}
			g.setColor(Color.white);
			g.fillRect(100, 218, 504, 2);
			g.fillRect(100, 233, 504, 2);
			g.fillRect(100, 218, 2, 15);
			g.fillRect(602, 218, 2, 15);
			g.setFont(new Font("Times",Font.PLAIN, 10));
			g.drawString(totalBricks+"/50000", 310, 230);
			Rectangle attack = new Rectangle(attackX,attackY,30,30);
			if(attack.intersects(new Rectangle(playerX, 550, 100, 8))){
				attackY=180;
				paddleHit=50;
				soulSteal = rand.nextInt(3800)+201;
				if(totalBricks+soulSteal>50000){
					score-=(50000-totalBricks);
					damned+=50000-totalBricks;
					totalBricks=50000;
				}else{
					totalBricks+=soulSteal;
					score-=soulSteal;
					damned+=soulSteal;
				}
			}
		}
		// borders
		if(lvl<5){
			g.setColor(Color.CYAN);
		}else{
			g.setColor(Color.red);
		}
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(683, 0, 3, 592);
		/// the scores 		
		g.setColor(Color.ORANGE);
		g.setFont(new Font("serif",Font.BOLD, 25));
		if(paddleHit>0){
			if(paddleHit%3==0){
				g.setColor(Color.white);
			}else{
				g.setColor(Color.red);
			}
		}
		g.drawString("Souls: "+(totalSouls+score), 30,30);//+score
		//time
		
		//streak
		if(hitStreak>1){
			if(hitStreak<5){
				g.setColor(Color.white);
			}else if(hitStreak<10){
				g.setColor(Color.cyan);
			}else if(hitStreak<20){
				g.setColor(Color.green);
			}else if(hitStreak<50){
				g.setColor(Color.yellow);
			}else if(hitStreak<100){
				g.setColor(Color.red);
			}else{
				g.setColor(Color.magenta);
			}
			g.setFont(new Font("serif",Font.ITALIC, 25));
			g.drawString("x"+hitStreak, 420,30);
		}
		//5 soul block
		if(isFive){
			g.setColor(Color.green);
			g.setFont(new Font("serif",Font.BOLD, 25));
			g.drawString("+5", 370,30);
		}
		//difficulty
		switch(lvl){
			case 0: g.setColor(Color.white);
			break;
			case 1: g.setColor(Color.CYAN);
			break;
			case 2: g.setColor(Color.pink);
			break;
			case 3: g.setColor(Color.yellow);
			break;
			case 4: g.setColor(Color.orange);
			break;
			default: g.setColor(Color.red);
		}
		g.setFont(new Font("serif",Font.PLAIN, 20));
		g.drawString("Circle: "+lvl, 570,30);

		// the paddle
		switch(lvl){
			case 0: g.setColor(Color.white);
			break;
			case 1: g.setColor(Color.CYAN);
			break;
			case 2: g.setColor(Color.pink);
			break;
			case 3: g.setColor(Color.yellow);
			break;
			case 4: g.setColor(Color.orange);
			break;
			default: g.setColor(Color.red);
		}
		if(paddleHit>0){
			if(paddleHit%3==0){
				g.setColor(Color.red);
			}else{
				g.setColor(Color.white);
			}
			paddleHit--;
		}
		g.fillRect(playerX, 550, 100, 8);
		
		// the ball
		if(lvl<=4){
			g.setColor(Color.CYAN);
		}else if(lvl>4){
			ballS=16;
			g.setColor(Color.yellow);
		}else if(lvl>=5){
			ballS=10;
		}
		if(bigBdur>0&&bigBactive){
			g.setColor(Color.magenta);
			if(bigBdur==1){
				g.setColor(Color.pink);
			}
			g.fillOval(ballposX, ballposY, 20+(bigBdur*5), 20+(bigBdur*5));
		}else{
			g.fillOval(ballposX, ballposY, ballS, ballS);
			bigBactive=false;
		}
		
		if(fireBallActive&&fireBallDur>0){
			fEffect = rand.nextInt(3);
			g.setFont(new Font("serif",Font.PLAIN, 70));;
			if(fEffect==0){
				g.setColor(Color.red);				
			}else{
				g.setColor(Color.orange);				
			}
			g.drawString("*", ballposX-7, ballposY+40);
			if(fireBallDur<=1){
				if(fEffect==0){
					g.setColor(Color.blue);
				}else if(fEffect==1){
					g.setColor(Color.red);
				}else{
					g.setColor(Color.cyan);
				}
			}else{
				if(fEffect==0){
					g.setColor(Color.orange);
				}else if(fEffect==1){
					g.setColor(Color.pink);
				}else{
					g.setColor(Color.red);
				}
			}
			g.fillOval(ballposX, ballposY, ballS, ballS);
		}
		//play/pause
		if(playSign>0){
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.PLAIN, 50));;
			g.drawString(">", 330, 400);
			g.fillRect(330, 372, 2, 23);
			playSign--;
		}
		if(isPaused){
			g.setColor(Color.white);
			g.fillRect(330, 372, 5, 23);
			g.fillRect(342, 372, 5, 23);
			timer.stop();
		}
		// when you won the game
		if(totalBricks <= 0)
		{
			attackY=601;
			 play = false;
			 paddleHit=0;
			 hitStreak=0;
			 loss=0;
			 moveD=50;	
			 won=true;	
			 bigBactive=false;
			 by2active=false;
			 fireBallActive=false;
			 /////////
             ballXdir = 0;
     		 ballYdir = 0;
			  g.setColor(Color.white);
              g.setFont(new Font("serif",Font.BOLD, 30));
			  if(lvl>0){
             	g.drawString("You Lived", 260,300);
			  }else{
				g.drawString("Set their Souls free", 220,300);
			  }
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));
			 if(lvl==0){   
             	g.drawString("You have awakened the Speed Demon (Enter/click)", 130,350); 
			 }else if(lvl<4){
				g.drawString("You won't make it much further (Enter/click)", 140,350);
			 }else if(lvl==4){
				g.drawString("The Speed Demon Roars with Malice (Enter/click)", 130,350);
			 }else if(lvl<9){
				g.drawString("The 9th Circle awaits your demise (Enter/click)", 130,350);
			 }else if(lvl>=9){
				g.drawString("You have become one with the void (Enter/click)", 130,350);
			 }
			 devil=true;
		}
		
		// when you lose the game
		if(ballposY > 570||lvl==9&&(score+totalSouls)<0)
        {
			attackY=601;
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
			 paddleHit=0;
			 won=false;
			  hitStreak=0;
			  bigBactive=false;
			  by2active=false;
			  fireBallActive=false;
			  g.setColor(Color.black);
			  g.fillRect(playerX, 550, 100, 8);
             g.setColor(Color.red);
             g.setFont(new Font("Papyrus",Font.PLAIN, 50));
			 if(lvl==9&&(score+totalSouls)<0){
				 g.drawString("Souls Taken", 190,300);
				 g.setFont(new Font("serif",Font.PLAIN, 30)); 
				 g.drawString("You Fell to the Speed Demon", 150,360);
			 }else{
            	 g.drawString("Vessel Lost", 205,300);
				 if(curse||curseChance<=lvl){
					g.setFont(new Font("serif",Font.PLAIN, 30));           
            		g.drawString("YOU WERE CURSED", 195,355);
					g.drawString(((lvl*10)+(lvl*loss)+(lvl*50))+" Souls were damned", 190, 400);
				 }else{
             		g.setColor(Color.ORANGE);
            		g.setFont(new Font("serif",Font.PLAIN, 30));           
            		g.drawString(((lvl*10)+(lvl*loss))+" Souls were damned", 190,360);   
			 	 }
			}
        }

		g.dispose();
		}
	}	

	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			if(playerX >= 600-(2*lvl))
			{
				playerX = 600-(2*lvl);
			}
			else
			{
				moveRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(playerX < 10)
			{
				playerX = 2+(2*lvl);
			}
			else
			{
				moveLeft();
			}
        }		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				reset();
			}//
        }
		if(e.getKeyCode()==KeyEvent.VK_SPACE&&play)	{
			if(timer.isRunning()){
				isPaused=true;
			}else{
				timer.start();
				isPaused=false;
				playSign=20;
			}
		}	
	}

	public void reset(){
		play = true;
				bigBactive=false;
				totalSouls+=score;
				if(totalSouls>highestSoul){//
					highestSoul=totalSouls;
				}
				if(won){
					lvl++;
					if(lvl<9){
						xBricks++;
						yBricks++;
					}else{
						xBricks+=lvl;
						yBricks+=lvl;
					}
				}else{
					if(curse||curseChance<=lvl){
						totalSouls+=-(lvl*10)-(lvl*loss)-(lvl*50);
						if(totalSouls<0){
							damned+=totalSouls+(lvl*10)+(lvl*loss)+(lvl*50);
						}else{
							damned+=lvl*10+(lvl*loss)+(lvl*50);
						}
					}else{
						totalSouls-=(lvl*10)+(lvl*loss);
						if(totalSouls<0){
							damned+=totalSouls+(lvl*10)+(lvl*loss);
						}else{
							damned+=lvl*10+(lvl*loss);
						}
					}
					loss++;
				}
				if(devil==false){///
					ballXdir = -1;
					ballYdir = -2;
				}else{
					ballXdir = -(lvl*2)+rand.nextInt(lvl*4);//
					while(ballXdir==0){
						ballXdir = -(lvl*2)+rand.nextInt(lvl*4);
					}
					ballYdir = -2-(lvl*2);//
				}/////
				ballposX = 350;
				ballposY = 520;
				playerX = 310;
				score = 0;
				if(lvl!=9){
					totalBricks = xBricks*yBricks;
					map = new MapGenerator(xBricks, yBricks);
				}else{
					totalBricks = 50000;
					map = new MapGenerator(1, 1);
				}
				
				
				repaint();
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void moveRight()
	{
		play = true;
		playerX+=moveD+(lvl*3);	
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=moveD+(lvl*3);	 	
	}

	public void setPlayerX(int x){
		playerX=x-50;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{			
			Rectangle bola = new Rectangle(ballposX, ballposY, ballS, ballS);
			if(bigBactive&&bigBdur>0){
				bola = new Rectangle(ballposX, ballposY, 20+(bigBdur*5), 20+(bigBdur*5));
			}
			if(bola.intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
				hitStreak=0;
				isFive=false;
				if(bigBdur>0){
					bigBdur--;
				}
				if(by2dur>0){
					by2dur--;
				}
				if(fireBallDur>0){
					fireBallDur--;
				}
			}
			else if(bola.intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
				hitStreak=0;
				isFive=false;
				if(bigBdur>0){
					bigBdur--;
				}
				if(by2dur>0){
					by2dur--;
				}
				if(fireBallDur>0){
					fireBallDur--;
				}
			}
			else if(bola.intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYdir = -ballYdir;
				hitStreak=0;
				isFive=false;
				if(bigBdur>0){
					bigBdur--;
				}
				if(by2dur>0){
					by2dur--;
				}
				if(fireBallDur>0){
					fireBallDur--;
				}
			}
			
			// check map collision with the ball		
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						//scores++;
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, ballS, ballS);//change to ballS
						if(bigBactive&&bigBdur>0){
							ballRect = new Rectangle(ballposX, ballposY, 20+(bigBdur*5), 20+(bigBdur*5));
						}
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{	
							if(lvl!=9){				
								map.setBrickValue(0, i, j);
							}
							hitStreak++;
							if(lvl!=9){
								times2 = rand.nextInt(15);
							}
							curseChance= rand.nextInt(100)+1;//100
							if(curseChance<=lvl){
								curse=true;
							}else{
								curse=false;
							}

							if(!bigBactive&&!fireBallActive){
								bigBchance=rand.nextInt(25);
							}
							if(by2active==false&&lvl!=9){
								by2chance=rand.nextInt(25);
							}
							if(times2==0&&lvl!=9||(by2active&&by2dur>0)){
								score+=5*hitStreak;
								fiveBrick++;
								isFive=true;
							}else{
								score+=1*hitStreak;
								isFive=false;
							}	
							if(bigBchance==0&&bigBactive==false){//
								bigBdur=5;
								bigBactive=true;
							}else if(bigBactive==true&&bigBdur<=0){
								bigBdur=5;
								bigBactive=false;
							}
							if(lvl==9&&bigBactive){
								score+=bigBdur;
							}
							if(by2chance==0&&by2active==false&&lvl!=9){
								by2dur=5;
								by2active=true;
							}else if(by2active==true&&by2dur<=0){
								by2dur=5;
								by2active=false;
							}
							if(bigBchance==1&&!fireBallActive){
								fireBallDur=5;
								fireBallActive=true;
							}else if(fireBallActive&&fireBallDur<=0){
								fireBallActive=false;
								fireBallDur=5;
							}
							if(lvl!=9){
								totalBricks--;
							}else{
								hitBoss=true;
								totalBricks-=hitStreak+bigBdur;
							}
							
							// when ball hit right or left of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								if(!fireBallActive)
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								if(!fireBallActive)
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 672-(ballS/2))
			{
				ballXdir = -ballXdir;
			}
			if(bigBactive&&bigBdur>0&&ballposX>672-((20+(bigBdur*5))/2)){
				ballXdir = -ballXdir;
			}

			if(lvl==9){
				/////
			}
			repaint();		
		}
	}

	
}
