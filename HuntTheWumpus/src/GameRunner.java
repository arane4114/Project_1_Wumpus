// test pull
public class GameRunner {
	private Map gameMap;
	/*
	 * Default Constructor. Initializes a random map.
	 */
	public GameRunner(){
		this.gameMap = new Map();
		gameMap.generateWumpus();
		gameMap.generatePits();
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