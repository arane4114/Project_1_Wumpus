import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUIRunner extends JFrame{
	private Model gameModel;
	private MoveButtonPanel moveButtons;
	private ShootButtonPanel shootButtons;
	private TextVersionPanel textVersion;
	
	public GUIRunner(){
		this.gameModel = new Model();
		gameModel.generateWumpus();
		gameModel.generatePits();
		gameModel.generateHunter();
		setTitle("Hunt The Wumpus");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel bottomLeft = new JPanel();
		JLabel moveLabel = new JLabel("Move");
		moveButtons = new MoveButtonPanel(gameModel);
		bottomLeft.add(moveLabel, BorderLayout.EAST);
		bottomLeft.add(moveButtons, BorderLayout.WEST);
		
		JPanel bottomRight = new JPanel();
		JLabel shootLabel = new JLabel("Shoot");
		shootButtons = new ShootButtonPanel(gameModel);
		bottomRight.add(shootLabel, BorderLayout.EAST);
		bottomRight.add(shootButtons, BorderLayout.WEST);
		
		JPanel bottom = new JPanel();
		bottom.add(bottomLeft, BorderLayout.EAST);
		bottom.add(bottomRight, BorderLayout.WEST);
		this.add(bottom, BorderLayout.SOUTH);
		
		this.textVersion = new TextVersionPanel();
		this.gameModel.addObserver(textVersion);
		this.add(textVersion, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new GUIRunner();
	}
}
