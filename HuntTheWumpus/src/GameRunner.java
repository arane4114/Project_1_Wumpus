/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
public class GameRunner {
	private Map gameMap;
	/*
	 * Default Constructor. Initializes a random map.
	 */
	public GameRunner(){
		this.gameMap = new Map();
		gameMap.generateWumpus();
		gameMap.generatePits();
		gameMap.generateHunter();
	}
	
	/*
	 * Primary run loop for the game. 
	 */
	public void run(){
		System.out.println(gameMap.toStringDebug());
	}
	
	
	public static void main(String[] args){
		GameRunner game = new GameRunner();
		game.run();
	}
}