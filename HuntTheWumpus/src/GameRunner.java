import java.util.Scanner;

// test pull
public class GameRunner {
	private Map gameMap;
	private Scanner kb;

	/*
	 * Default Constructor. Initializes a random map.
	 */
	public GameRunner() {
		this.gameMap = new Map();
		gameMap.generateWumpus();
		gameMap.generatePits();
		gameMap.generateHunter();
		kb = new Scanner(System.in);
	}

	/*
	 * Primary run loop for the game.
	 */
	public void run() {
		System.out.println("Welcome to the cave. To move enter in w,a,s,d.\n"
				+ "To shoot your arrow enter in W,A,S,D.\n"
				+ "You only get one arrow and one life.\n" + "Good Luck.");
		while (gameMap.isAlive()) {
			System.out.println();
			System.out.println(gameMap);
			System.out.print("What will you do?: ");
			String userInput = kb.nextLine();
			char move = userInput.charAt(0);
			if (move == 'w' || move == 'a' || move == 's' || move == 'd') {
				gameMap.hunterMove(move);
				System.out.println(gameMap.getCurrentState());
			} else if (move == 'W' || move == 'A' || move == 'S' || move == 'D') {
				if(gameMap.shootArrow(move)){
					System.out.println("You aim and fire you weapon of choice. Your trusty bow.\n"
							+ "The arrow whistles through the air. Your aim is true.\n"
							+ "The beast is dead.\n"
							+ "You return home a hero.");
				}else{
					System.out.println("You aim and fire you weapon of choice. Your trusty bow.\n"
							+ "The arrow whistles through the air. Alas your target is not in that direction.\n"
							+ "As you are about to turn away a portal appears infront of the arrow. The arrow enters the portal\n"
							+ "You hear a sound behind you. Somehow another portal has opened up behind you. The arrow is flying towards you.\n"
							+ "The arrow hits you in the ending your carrer as an explorer. Unable to walk and loosing blood you die alone.");
				}
			}
		}
	}

	public static void main(String[] args) {
		GameRunner game = new GameRunner();
		game.run();
	}
}