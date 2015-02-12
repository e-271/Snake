package snake;
import javax.swing.JFrame;

public class Snake {
	public static void main( String[] args )
	throws InterruptedException {
		SnakePanel panel = new SnakePanel();
		JFrame application = new JFrame();
		
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(400 , 400);
		application.addKeyListener(panel);
		application.setVisible(true);
	
		boolean gameover = false;
		while( !gameover ) {
			switch( panel.snakeDirection ) {
			case 1:
				panel.y -= 10;
				break;
			case 2:
				panel.x += 10;
				break;
			case 3:
				panel.y += 10;
				break;
			case 4:
				panel.x -= 10;
				break;
			default:
			}
			if( panel.x < 0 || panel.y < 0 || panel.x > 400 || panel.y > 400 )
				gameover = true;
			panel.repaint();
			Thread.sleep(500);
		}
	}
}