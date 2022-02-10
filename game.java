
import javax.swing.*;
import java.awt.event.*;
public class game{
	public static void main(String[] args) {
		new MyFrame();
	}
}
class MyFrame extends JFrame implements KeyListener,MouseListener,MouseMotionListener{
	Gameplay gamePlay = new Gameplay();
	MyFrame() {
		gamePlay.play=true;
		JFrame obj=new JFrame();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("The 9 Circles of Paddle Ball");		
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
        obj.setVisible(true);
		JLabel label = new JLabel();
		label.setBounds(0,0,700,600);
		label.addMouseListener(this);
		label.addMouseMotionListener(this);
		obj.add(label);
	}

	
	
	public void mouseClicked(MouseEvent e) {
		if(!gamePlay.play){
			gamePlay.reset();
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	/////////////////
	
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyPressed(KeyEvent e) {
		
	}


	@Override
	public void keyReleased(KeyEvent e) {}



	@Override
	public void mouseDragged(MouseEvent e) {
		
		
	}

	public void mouseMoved(MouseEvent e) {
		gamePlay.setPlayerX(e.getX());
	}


}
