package snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class SnakePanel extends JPanel implements KeyListener 
{
	public int x = 0 , y = 200, snakeDirection = 2, width = 10 , height = 10 , length = 100 ;
	
	
	public void keyPressed(KeyEvent e){
		int input = e.getKeyCode();
		switch(input){
		case KeyEvent.VK_UP:
			snakeDirection = 1;
			height = length;
			width = 10;
			break;
		case KeyEvent.VK_RIGHT:
			snakeDirection = 2;
			//draws left
			width = -length;
			height = 10;
			break;
		case KeyEvent.VK_DOWN:
			snakeDirection = 3;
			//draws up
			height = -length;
			width = 10;
			break;
		case KeyEvent.VK_LEFT:
			snakeDirection = 4;
			height = 10;
			width = length;
			break;
		case KeyEvent.VK_SPACE:
			pause();
			break;
		default:
		}
	} 
	
	public void pause() {	
	}
	
	public void keyReleased(KeyEvent e) {
	  }
	  
	public void keyTyped(KeyEvent e) {
	  }
	 
	
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		g.fillRect(x, y, width, height);
		
	}	
}