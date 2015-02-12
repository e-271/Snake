package snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class SnakePanel extends JPanel implements KeyListener
{
	
	public void keyPressed(KeyEvent e){
		int input = e.getKeyCode();
		switch(input){
		case KeyEvent.VK_UP:
			if(Snake.player.previousDirection == 's')
				break;
			Snake.player.headDirection = 'n';
			break;
		case KeyEvent.VK_RIGHT:
			if(Snake.player.previousDirection == 'w')
				break;
			Snake.player.headDirection = 'e';
			break;
		case KeyEvent.VK_DOWN:
			if(Snake.player.previousDirection == 'n')
				break;
			Snake.player.headDirection = 's';
			break;
		case KeyEvent.VK_LEFT:
			if(Snake.player.previousDirection == 'e')
				break;
			Snake.player.headDirection = 'w';
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
		g.drawLine(0, 0, 500, 0);
		g.drawLine(0, 0, 0, 500);
		g.drawLine(0, 500, 500, 500);
		g.drawLine(500, 0, 500, 500);
		for( int m = 0 ; m < 100 ; m++ ) {
			for(int n = 0 ; n < 100 ; n++) {
				if( Snake.board[m][n])
					g.fillRect( n*5 , m*5 , 5 , 5 );
			}
		g.fillRect(Snake.foodN*5, Snake.foodM*5, 5, 5);
		}
	}
}
