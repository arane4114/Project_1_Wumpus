import java.awt.BorderLayout;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
public class GUIRunner extends JFrame{
	private Model gameModel;
	private MoveButtonPanel moveButtons;
	private ShootButtonPanel shootButtons;
	private TextVersionPanel textVersion;
	
	private JTabbedPane tabbedPanels;
	private JPanel textPanel = new TextVersionPanel();
	private JPanel picturePanel = new PictureVersionPanel();
	
	public GUIRunner(){
		this.gameModel = new Model();
		gameModel.generateWumpus();
		gameModel.generatePits();
		gameModel.generateHunter();
		setTitle("Hunt The Wumpus");
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPanels = new JTabbedPane();
		tabbedPanels.add(picturePanel, "Graphical Veiw");
		tabbedPanels.add(textPanel, "Text Veiw");
		this.add(tabbedPanels, BorderLayout.CENTER);
	    
		
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
		
//		this.textVersion = new TextVersionPanel();
//		this.gameModel.addObserver(textVersion);
//		textPanel.add(textVersion);
		
		gameModel.addObserver((Observer) textPanel);
		gameModel.addObserver((Observer) picturePanel);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new GUIRunner();
	}
}
