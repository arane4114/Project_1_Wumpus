/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
import java.util.Scanner;

/*
 * This class is the entry point for the application.
 * It also contains the run loop and an input method for the game.
 */
public class ConsoleRunner {
	private Model gameMap;
	private Scanner kb;

	/*
	 * Default Constructor. Initializes a random map and sets up user input.
	 */
	public ConsoleRunner() {
		this.gameMap = new Model();
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
				+ "You only get one arrow and one life.\n" + "May the hunt be fruitful.");
		while (gameMap.isRunning()) {
			System.out.println();
			System.out.println(gameMap);
			System.out.print("What will you do?: ");
			String userInput = kb.nextLine();
			if (!userInput.isEmpty()) {
				char move = userInput.charAt(0);
				if (move == 'w' || move == 'a' || move == 's' || move == 'd') {
					gameMap.hunterMove(move);
					System.out.println(gameMap.getCurrentState());
				} else if (move == 'W' || move == 'A' || move == 'S'
						|| move == 'D') {
					gameMap.shootArrow(move);
					System.out.println(gameMap.getCurrentState());
				} else { // Invalid input
					System.out
							.println("You seem to be confused as to what you can to do. You can only move or shoot.");
				}
			} else {// Empty input
				System.out
						.println("Whew! All that hunting has left you tired. You take a breather.");
			}
		}
		System.out.println("\n" + gameMap.toStringShowAllRooms());
	}

	/*
	 * Entry point for the program. Creates a game and then gives starts the run
	 * loop.
	 */
	public static void main(String[] args) {
		ConsoleRunner game = new ConsoleRunner();
		game.run();
	}

}