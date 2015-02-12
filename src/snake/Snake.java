package snake;
import javax.swing.JFrame;

public class Snake {
	
	public char headDirection = 'e' , tailDirection = 'e', previousDirection = 'e';
	public int headM, headN, cooldown;
	public int tailM, tailN;
	public boolean loser;
	public static boolean gameover;
	public static int foodM, foodN;
	public static boolean[][] board = new boolean[100][100];
	public static Snake player = new Snake();
	public static Snake AI0 = new Snake();
	public static Snake AI1 = new Snake();


	
	public static void main( String[] args )
	throws InterruptedException {
		SnakePanel panel = new SnakePanel();
		JFrame application = new JFrame();
		

		AI0.cooldown = 0; AI0.loser = false;
		AI1.cooldown = 0; AI1.loser = false;
		player.cooldown = 0; player.loser = false;
		
		application.addKeyListener(panel);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(520 , 540);
		application.setVisible(true);

		drawBoard(board);
		drawSnake(30 , 50 , AI0);
		//drawSnake(50 , 50 , AI1);
		popFood();
		
		while( !gameover ) {
			
			takeTurn(AI0, true);
			//takeTurn(AI1, true);

			panel.repaint();
			Thread.sleep(20);
		}
	}
	
	private static void takeTurn(Snake snake, boolean AI){
		upkeep(snake);
		if(AI) { chooseDirection(snake); }
		moveHead(snake);
		moveTail(snake);
	}
	
	private static void drawBoard( boolean[][] board ) {
		for(int m = 0 ; m < board.length ; m++) {
			for(int n = 0; n < board[m].length ; n++)
				board[m][n] = false;			
		}	
	}
	
	private static void drawSnake( int m , int n , Snake newSnake ){
		newSnake.headM = m;
		newSnake.headN = n;
		newSnake.tailM = m;
		newSnake.tailN = n - 4;
		
		for( int i = n ; i >= newSnake.tailN ; i--){
			board[m][i] = true;
		}
	}
	
	private static void upkeep(Snake snake){
		if(snake.cooldown > 0)
			snake.cooldown--;
		if( snake.headM == foodM && snake.headN == foodN ) {
			snake.cooldown += 3;
			popFood();
		}
	}
	
	private static void chooseDirection(Snake snake){
		int deltaM = foodM - snake.headM;
		int deltaN = foodN - snake.headN;
		int headM = snake.headM;
		int headN = snake.headN;
		
		if(headM + 1 < board.length ){
			if(!board[headM + 1][headN]){
				if( deltaM > 0 ){
					snake.headDirection = 's';
					return;
				}
				//U-turn logic
				else if( (deltaM == 0) && ((snake.headDirection == 'e' && deltaN < 0) || (snake.headDirection == 'w' && deltaN > 0)) ){
					snake.headDirection = 's';
					return;
				}
			}
		}
		
		if(headM - 1 >= 0){
			if(!board[headM - 1][headN]){
				if(deltaM < 0 ){
					snake.headDirection = 'n';
					return;
			}
				else if( (deltaM == 0) && ((snake.headDirection == 'e' && deltaN < 0) || (snake.headDirection == 'w' && deltaN > 0)) ){
					snake.headDirection = 'n';
					return;
			}
			}
		}
		
		if(headN + 1 < board[0].length ){
			if( !board[headM][headN + 1] ){
				if(deltaN > 0 ){
					snake.headDirection = 'e';
					return;
				}
				else if( (deltaN == 0  && ((snake.headDirection == 'n' && deltaM > 0) || (snake.headDirection == 's' && deltaN < 0)) ) ){
					snake.headDirection = 'e';
					return;
				}
			}
		}
		
		if(headN - 1 > 0){
			if( !board[headM][headN - 1] ){
				if(deltaN <= 0 ){
					snake.headDirection = 'w';
					return;
				}
				else if( (deltaN == 0 ) && ((snake.headDirection == 'n' && deltaM > 0) || (snake.headDirection == 's' && deltaN < 0)) ){
					snake.headDirection = 'w';
					return;
				}
			}
		}
		
		else {
			if(headM + 1 < 100 && !board[headM + 1][headN]) {
				snake.headDirection = 's';
				return;
			}
			if(headM - 1 >= 0 && !board[headM - 1][headN]){
				snake.headDirection = 'n';
				return;
			}
			if( headN + 1 < 100 && !board[headM][headN + 1] ){
				snake.headDirection = 'e';
				return;
			}
			if( headN - 1 >= 0 && !board[headM][headN - 1] ){
				snake.headDirection = 'w';
				return;
			}
		}
	}
	
	private static void popFood(){
		foodM = (int)(Math.random() * 100);
		foodN = (int)(Math.random() * 100);
		//Tests if food location overlaps snake location
		for( int m = 0 ; m < board.length ; m++) {
 			for( int n = 0 ; n < board[m].length ; n++ ){
				if( board[foodM][foodN] ){
					foodM = (int)(Math.random() * 100);
					foodN = (int)(Math.random() * 100); 
					m = 0; n = 0;
				}
			}	
		}
	}

	private static void moveHead(Snake snake){
		int headM = snake.headM;
		int headN = snake.headN;
		
		switch( snake.headDirection ) {
		case 'n':
			snake.headM--; headM--;
			//Theoretically will not throw an exception
			if( headM < 0 || board[headM][headN]) {
				gameover = true; 
				snake.loser = true;
				break;
			}
			else
				board[headM][headN] = true;
			break;
			
		case 'e':
			snake.headN++; headN++;
			if( headN >= board[0].length || board[headM][headN] ) {
				gameover = true; 
				snake.loser = true;
				break;
			}
			else
				board[headM][headN] = true;
			break;
			
		case 's':
			snake.headM++; headM++;
			if( headM > board.length || board[headM][headN] ){
				gameover = true;
				snake.loser = true;
				break;
			}
			else
				board[headM][headN] = true;
			break;
			
		case 'w':
			snake.headN--; headN--;
			if( headN < 0 || board[headM][headN] ) {
				gameover = true;
				snake.loser = true;
				break;
			}
			else
				board[headM][headN] = true;
			break;
			
		} 
		
		snake.previousDirection = snake.headDirection;
	}
	
	private static void moveTail(Snake snake) {
		if(snake.cooldown > 0)
			return;
		int tailM = snake.tailM;
		int tailN = snake.tailN;
		
		switch( snake.tailDirection ) {
			case 'n':
		   		board[tailM][tailN] = false;
		   		
		   		if( tailM - 1 >= 0 ){
		   			snake.tailM--; tailM--;
		   		}
		   		else{ System.out.println("error"); break; }
		   		
				if( tailM - 1 < 0 || !board[tailM - 1][tailN]  ) {
					
					if( tailN + 1 < 100) {
						if(board[tailM][tailN + 1])
							snake.tailDirection = 'e';
					}
					if( tailN - 1 >= 0) {
						if(board[tailM][tailN - 1])
							snake.tailDirection = 'w';
					}
						
				}
				break;
				
			case 'e':
		   		board[tailM][tailN] = false;
		   		
		   		if( tailN + 1 < 100) {
		   			snake.tailN++; tailN++;
		   		}
		   		else{ System.out.println("error"); break; }
		   		
				if( tailN + 1 > 99 || !board[tailM][tailN + 1]  ) {
					if( tailM - 1 >= 0) {
						if( board[tailM - 1][tailN])
							snake.tailDirection = 'n';
					}
					if( tailM + 1 < 100) {
						if( board[tailM + 1][tailN])
							snake.tailDirection = 's';
					}
				}
				break;
				
			case 's':
		   		board[tailM][tailN] = false;
		   		
		   		if( tailM + 1 < 100 ){
		   			snake.tailM ++; tailM++;
		   		}
		   		else{ System.out.println("error"); break; }
		   		
				if( tailM + 1 > 99 || !board[tailM + 1][tailN]  ) {
					if( tailN + 1 < 99){
						if( board[tailM][tailN + 1])
							snake.tailDirection = 'e';
					}
					if( tailN - 1 >= 0) {
						if( board[tailM][tailN - 1])
							snake.tailDirection = 'w';
					}
				}
				break;
				
			case 'w':
		   		board[tailM][tailN] = false;
		   		if( tailN - 1 >= 0){
		   			snake.tailN--; tailN--;
		   		}
		   		else{ System.out.println("error"); break; }
		   		
				if( tailN - 1 < 0 || !board[tailM][tailN - 1]  ) {
					if( tailM - 1 >= 0 ) {
						if( board[tailM - 1][tailN])
							snake.tailDirection = 'n';
					}
					if( tailM + 1 < 100) {
						if( board[tailM + 1][tailN])
							snake.tailDirection = 's';
					}
				}
				break;
				default:
			}
	}
}
